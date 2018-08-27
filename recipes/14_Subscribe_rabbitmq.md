# RabbitMQ 구독하기

by Eunmin Kim

## 이럴 때 쓰세요

RabbitMQ를 구독하는 애플리케이션을 만들고 싶습니다.

## 어떻게 하나요?

### 1. Langohr 의존성 추가하기

Clojure RabbitMQ 라이브러리인 [Langohr](http://clojurerabbitmq.info/) 의존성을 추가합니다.

```clojure
[com.novemberain/langohr "5.0.0"]
```

### 2. 채널 열기

구독할 RabbitMQ 큐에 연결 하기 위해 채널을 엽니다.

```clojure
(require '[langohr.core :as rmq])
(require '[langohr.channel :as lch])

;; default config {:host "localhost", :port 5672, :username "guest", :vhost "/", :password "guest"}
(def conn (rmq/connect))

(def channel (lch/open conn))
```

### 3. 메시지 핸들러 만들기

구독한 큐에 있는 메시지를 처리할 핸들러를 만들어 줍니다.

```clojure
(require '[langohr.basic :refer [ack nack]])

(defn message-handler [channel {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (String. payload "UTF-8")
  (ack channel delivery-tag)))
```

### 4. 채널 닫기

더 구독을 하지 않으려면 채널을 닫아 줍니다.

```clojure
(lch/close channel)

(rmq/close conn)
```

## 이런 것도 보세요

* [RabbitMQ](https://www.rabbitmq.com/)
