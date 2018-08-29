# JSON 형태로 로그 남기기

by Eunmin Kim

## 이럴 때 쓰세요

JSON 형태로 로그를 남기고 싶습니다.

## 어떻게 하나요?

### 1. structured-logging 의존성 추가하기

JSON 로그를 지원하는 [structured-logging(https://github.com/puppetlabs/structured-logging)
라이브러리 의존성을 추가합니다.

```clojure
[puppetlabs/structured-logging "0.2.0"]
```

Logger 구현체인 `logback-classic` 의존성을 추가합니다.

```clojure
[ch.qos.logback/logback-classic "1.1.3"]
```

### 2. logback.xml 설정하기

JSON 로그 형식을 위해 아래와 같은 `logback.xml` 파일을 `resources` 아래 만듭니다.

```xml
<configuration>
  <statusListener class="ch.qos.logback.core.status.NopStatusListener" />

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
      <providers>
        <timestamp/>
        <message/>
        <loggerName/>
        <threadName/>
        <logLevel/>
        <logLevelValue/>
        <stackTrace/>
        <logstashMarkers/>
      </providers>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```

### 3. 로그 작성하기

```clojure
(require '[puppetlabs.structured-logging.core :refer [maplog]])

(maplog :warn {:user-service "https://..."
                 :status 503
                 :elapsed 27}
          #(format "Failed to query user-service %s. Response: status %d"
                   (:user-service %) (:status %)))
```

```json
{"@timestamp":"2018-08-29T21:50:34.639+09:00","message":"Failed to query user-service https://.... Response: status 503","logger_name":"app.core","thread_name":"main","level":"WARN","level_value":30000,"user-service":"https://...","status":503,"elapsed":27}
```

## 이런 것도 보세요

* [logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder)
