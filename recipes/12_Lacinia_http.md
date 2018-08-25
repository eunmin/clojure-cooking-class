# Lacinia 라이브러리를 이용한 GraphQL HTTP 서버 만들기

by Eunmin Kim

## 이럴 때 쓰세요

GraphQL HTTP 서버를 만들고 싶습니다.

## 어떻게 하나요?

### 1. 의존성 추가하기

* [Lacinia 라이브러리로 GraphQL 쿼리 처리하기](recipes/11_Lacinia.md)레시피에 있는 의존성 추가하기
  를 참고해서 Lacinia 의존성을 추가합니다.
* HTTP 서버를 만들기 위해 [Ring](https://github.com/ring-clojure/ring) 의존성을 추가합니다.
* `/graphql` 앤드 포인트를 처리하기 위해 [Compojure](https://github.com/weavejester/compojure)
  라우팅 라이브러리 의존성을 추가합니다.
* JSON 요청과 응답을 쓰기 위해 [ring-json](https://github.com/ring-clojure/ring-json)
  라이브러리 의존성을 추가합니다.

```clojure
(defproject graphqlapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot graphqlapp.core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.walmartlabs/lacinia "0.29.0-rc-1"]
                 [ring "1.7.0-RC1"]
                 [compojure "1.6.1"]
                 [ring/ring-json "0.4.0"]])
```

### 2. GraphQL 라우팅 만들기

* GraphQL 요청을 처리할 라우팅을 만듭니다. `GET /graphql`과 `POST /graphql`을 지원하도록 만듭니다.

```clojure
(require '[compojure.core :refer [context defroutes GET POST wrap-routes]])
(require '[ring.util.response :refer [response]])

(defroutes app
  (context "/graphql" []
    (GET "/" {:keys [params]}
      (response (lacinia/execute schema (get params "query") nil nil)))
    (POST "/" {{:keys [query variables operationName]} :body}
      (response (lacinia/execute schema query variables nil {:operation-name operationName})))))
```

### 3. JSON 요청과 응답 처리

* 위에서 만든 라우터에 JSON 요청과 응답을 처리하기 위한 `wrap-json-response` 미들웨어와
  `wrap-json-body`를 추가합니다.
* `GET` 요청은 보통 쿼리 스트링에 GraphQL 쿼리를 보내기 때문에 쿼리 스트링을 쉽게 사용할 `wrap-params`
  미들웨어를 추가합니다.

```clojure
(require '[ring.middleware.json :refer [wrap-json-body wrap-json-response]])
(require '[ring.middleware.params :refer [wrap-params]])

(def handler
  (-> app
      (wrap-routes wrap-json-response)
      (wrap-routes wrap-json-body {:keywords? true :bigdecimals? true})
      (wrap-routes wrap-params)))
```

### 4. 웹 서버 실행하기

`-main` 함수에 `ring-jetty-adapter` 를 이용해 웹 서버 실행하는 코드를 작성합니다.

```clojure
(defn -main []
  (run-jetty handler {:port 3000}))
```

## 이런 것도 보세요

예제 코드는 [여기](https://github.com/eunmin/clojure-cooking-class/tree/master/examples/12_Lacinia_http/graphqlapp)에 있습니다.
