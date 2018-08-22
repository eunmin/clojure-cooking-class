# Leiningen 프로젝트를 실행 할 수 있는 jar로 패키징 하기

by Eunmin Kim

## 이럴 때 쓰세요

Leiningen 프로젝트를 실행 할 수 있는 jar로 패키징 하고 싶습니다.

## 어떻게 하나요?

### 1. 코드 수정하기

`-main` 함수가 있는 네임스페이스를 클래스로 생성하기 위해 `:gen-class` 옵션을 줍니다.

```clojure
(ns hello.core
  (:gen-class))

(defn -main []
  (println "안녕 세상!"))
```

### 2. 프로젝트 설정

* `project.clj` 파일을 열어 프로젝트에 있는 모든 클로저 소스를 컴파일 하기 위해 `uberjar` 프로필에
`:aot :all` 옵션을 줍니다.
* `-main` 함수가 있는 네임스페이스는 `aot` 컴파일에서 제외시키 위해(자동으로 클래스 생성)
`:skip-aot` 옵션을 줍니다.

```clojure
(defproject hello "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot hello.core
  :profiles {:uberjar {:aot :all}}
  :dependencies [[org.clojure/clojure "1.8.0"]])
```

### 3. 패키징

`project.clj`가 있는 디렉토리로 가서 아래 명령어를 실행합니다.

```bash
lein uberjar
```

### 4. 실행하기

`target` 디렉토리 아래 생성된 `-standalone.jar`로 끝나는 `jar` 파일을 `java`로 실행해 봅니다.

```bash
java -jar target/hello-0.1.0-SNAPSHOT-standalone.jar
안녕 세상!
```
