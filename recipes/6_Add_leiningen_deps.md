# Leiningen 프로젝트 의존성 추가하기

by Eunmin Kim

## 이럴 때 쓰세요

Leiningen 프로젝트에 의존성을 추가하고 싶습니다.

## 어떻게 하나요?

### 1. 의존성 찾기

Leiningen은 기본으로 [Maven repository](https://mvnrepository.com/)와
[Clojars repository](https://clojars.org/)에 있는 의존성을 추가할 수 있습니다.

[Clojars](https://clojars.org/)에서 `ring`을 검색해봅니다.

검색 결과에 있는 [`ring`](https://clojars.org/ring)을 눌러봅니다.

`Leiningen/Boot` 아래 있는 형태를 복사합니다.

```
[ring "1.7.0-RC1"]
```

### 2. 프로젝트에 추가하기

`project.clj` 파일을 열어 `:dependencies` 키 벡터 안에 추가합니다.

```clojure
(defproject hello "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.7.0-RC1"]])
```

## 이런 것도 보세요

[`Maven Repository`](https://mvnrepository.com/)도 Leiningen 의존성 형태를 지원합니다.

https://mvnrepository.com/artifact/redis.clients/jedis/2.9.0
