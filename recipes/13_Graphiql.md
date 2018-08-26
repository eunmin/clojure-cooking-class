# GraphQL HTTP 서버에 Graphiql 적용하기

by Eunmin

## 이럴 때 쓰세요

GraphQL HTTP 서버에 GraphQL in-browser IDE인 [Graphiql]()을 사용하고 싶습니다.

## 어떻게 하나요?

### 1. ring-graphql-ui 의존성 추가하기

Graphiql Ring 미들웨어을 지원하는 [ring-graphql-ui](https://github.com/threatgrid/ring-graphql-ui)
의존성을 추가합니다.

```clojrue
[threatgrid/ring-graphql-ui "0.1.1"]
```

### 2. wrap-graphiql 미들웨어 적용하기

```clojure
(require '[ring-graphql-ui.core :refer [wrap-graphiql]])

(def app
  (wrap-graphiql handler {:path "/graphiql"
                          :endpoint "/graphql"}))
```

### 3. 웹 브라우저에서 확인하기

웹 브라우저에서 `/graphiql/index.html`로 접근해 봅니다.

## 이런 것도 보세요

* [Graphiql](https://github.com/graphql/graphiql)
* [GraphQL Voyager](https://github.com/APIs-guru/graphql-voyager)
