# Clojure 안녕 세상!

by Eunmin Kim

## 이럴 때 쓰세요

Clojure로 Hello World 프로그램을 만들어보고 싶습니다.

## 어떻게 하나요?

### 1. 프로젝트 만들기

[Leiningen 프로젝트 만들기](2_Creating_leiningen_project.md)를 보고 프로젝트를 만듭니다.

### 2. 코드 작성하기

`src/hello/core.clj` 파일을 열어 아래 처럼 고칩니다.

```clojure
(ns hello.core)

(defn -main []
  (println "안녕 세상!"))
```

### 3. 실행하기

`project.clj` 파일이 있는 프로젝트 디렉토리로 가서 아래 명령어를 실행합니다.

```bash
lein run -m hello.core        
안녕 세상!
```
