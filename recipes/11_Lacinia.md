# Lacinia 라이브러리로 GraphQL 쿼리 처리하기

by Eunmin Kim

## 이럴 때 쓰세요

GraphQL 서버 어플리케이션을 만들고 싶습니다.

## 어떻게 하나요?

### 1. Lacinia 의존성을 추가

프로젝트에 GraphQL 서버 라이브러리인 [Lacinia](https://github.com/walmartlabs/lacinia)
의존성을 추가합니다. Lacinia 라이브러리는 `clojure.spec`과 `data.json` 의존성이 필요합니다.
`clojure.spec`은 `clojure 1.9.0` 이상을 사용하면 되고 `data.json`은
`[org.clojure/data.json "0.2.6"]`를 추가하면 됩니다.

```clojure
(defproject graphqlapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot graphqlapp.core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.walmartlabs/lacinia "0.29.0-rc-1"]])
```

### 2. GraphQL 스키마 만들기

GraphQL 서비스를 만드려면 제공하려는 리소스에 대한 스키마를 정의해야합니다. GraphQL 스키마 문법은
[여기](https://graphql-kr.github.io/learn/schema/#type-system)를 참고해서 작성합니다.
아래는 간단한 스키마 예제입니다. 읽어오기 쉽게 `resources` 디렉토리 아래 적당한 파일명으로 저장합니다.
예제에서는 `resources/schema.txt`로 저장했습니다.

```graphql
{
  enum Episode {
    NEWHOPE
    EMPIRE
    JEDI
  }

  type Character {
    name: String!
    appearsIn: [Episode]!
  }

  type Query {
    hero: Character
  }

  schema {
    query: Query
  }
}
```

### 3. 스키마 resolver 함수 작성하기

스키마 resolver는 쿼리 요청에 응답할 함수로 `context`, `args`, `value` 인자 3개를 받아서 쿼리의
응답 타입을 리턴해주면 됩니다. 위에서 정의한 `hero: Character` 쿼리에 응답할 resolver를 만들어
보겠습니다.

```clojure
(defn hero [context args value]
  {:name "R2-D2"
   :appearsIn ["NEWHOPE" "EMPIRE" "JEDI"]})
```

`hero` resolver는 정적인 `Character` 타입 데이터를 리턴합니다. 나중에는 데이터베이스 같은 곳에서
가져와서 리턴해주도록 만들면 됩니다.

### 4. 스키마 컴파일

2,3에서 준비한 스키마 파일과 resolver 함수를 연결해 lacinia 스키마로 컴파일 합니다.

```clojure
(require '[com.walmartlabs.lacinia.schema :as schema])
(require '[com.walmartlabs.lacinia.parser.schema :refer [parse-schema]])

(defn load-schema []
  (schema/compile (parse-schema (slurp (clojure.java.io/resource "schema.txt"))
                                {:resolvers {:Query {:hero hero}}})))
```

### 5. 쿼리 실행하기

GraphQL 쿼리는 HTTP 프로토콜이나 다른 통신 프로토콜로 받을 수 있지만 예제에서는 애플리케이션 파라미터로
받아서 실행해보겠습니다.

```clojure
(require '[com.walmartlabs.lacinia :as lacinia])

(defn -main [& [query]]
  (let [schema (load-schema)]
    (println (lacinia/execute schema query nil nil))))
```

```bash
$ lein run '{ hero { name } }'
{:data #ordered/map ([:hero #ordered/map ([:name R2-D2])])}
```

GraphQL 쿼리는 [여기](https://graphql-kr.github.io/learn/queries/)를 참고하세요.

## 이런 것도 보세요

* [GraphQL 한글 번역 사이트](https://graphql-kr.github.io/)
* [다른 Clojure GraphQL 라이브러리 graphql-clj](https://github.com/tendant/graphql-clj)
