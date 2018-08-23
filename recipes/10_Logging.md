# 로그 남기기

by Eunmin Kim

## 이럴 때 쓰세요

클로저 개발을 하면서 로그를 남기고 싶습니다.

## 어떻게 하나요?

### 1. tools.logging 의존성 추가

`slf4j`, `Apache commons-logging`, `log4j2`, `log4j`, `java.util.logging` 구현체를
클로저에서 매크로로 쉽게 사용할 수 있는 `clojure/tools.logging` [라이브러리](https://github.com/clojure/tools.logging)
의존성을 `project.clj` `:dependencies`에 추가합니다.

```clojure
[org.clojure/tools.logging "0.4.1"]
```

### 2. logback-classic 의존성 추가

`clojure/tools.logging`는 여러가지 로그 구현체를 사용할 수 있지만 많이 쓰고 있고 `slf4j` API를
구현한 [`logback`](https://logback.qos.ch/)을 사용해봅시다. 역시 `project.clj` `:dependencies`에 추가합니다.

```clojure
[ch.qos.logback/logback-classic "1.2.3"]
```

### 3. 로그 포멧 설정

`logback` 설정 파일을 `resources/logback.xml`에 아래와 같이 만들어 봅시다.

```xml
<configuration>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```

`pattern` 태그에 있는 값을 바꿔 포멧을 바꿔 봅시다. 자세한 설졍은
[여기](https://logback.qos.ch/manual/configuration.html)를 참고하세요.

### 4. 코드 작성

```clojure

(require '[clojure.tools.logging :as log])

(log/info "Hello World")

(log/error (RuntimeException. "Exception for test") "Hello World")
```

## 이런 것도 보세요

* Pure Clojure/Script 로깅 라이브러리 [timbre](https://github.com/ptaoussanis/timbre)
