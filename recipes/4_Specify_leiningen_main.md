# Leiningen 프로젝트 엔트리 포인트 지정하기

by Eunmin Kim

## 이럴 때 쓰세요

`lein run`으로 프로젝트를 실행하고 싶습니다.

## 어떻게 하나요?

### 1. 프로젝트 설정

`project.clj` 파일을 열어 `:main` 키에 `-main` 함수가 있는 네임스페이를 적어줍니다.

```clojure
(defproject hello "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main hello.core
  :dependencies [[org.clojure/clojure "1.8.0"]])
```

### 2. 실행

```bash
lein run
안녕 세상!
```
