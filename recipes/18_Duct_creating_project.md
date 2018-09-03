# Duct 웹 프로젝트 만들기

by Eunmin

## 이럴 때 쓰세요

[Duct](https://github.com/duct-framework/duct/wiki/Getting-Started) 프레임워크로
웹 프로젝트를 만들고 싶습니다.

## 어떻게 하나요?

### 1. 프로젝트 만들기

```bash
lein new duct hello +site +example

cd hello

lein duct setup
```

### 2. 웹서버 실행하기

```bash
lein repl
user=> (dev)
:loaded
dev=> (go)
:duct.server.http.jetty/starting-server {:port 3000}
:initiated
```

### 3. 브라우저에서 예제 페이지 확인하기

브라우저 주조 http://localhost:3000/example 를 입력합니다.

## 이런 것도 보세요

* [Duct Framework 가이드](https://github.com/eunmin/docs/blob/master/GUIDE-kr.rst) (한글)
