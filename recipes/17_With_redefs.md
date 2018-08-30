# 임시로 Var 다시 정의하기

by Eunmin Kim

## 이럴 때 쓰세요

실행 중에 특정 함수를 다른 함수로 교체하고 싶습니다.

## 어떻게 하나요?

### 1. with-redefs 매크로 사용하기

```clojure
(require '[org.httpkit.client :as http])

(defn stub-get [url]
  {:status 200 :headers {} :body "Hello World"})

(deftest test-get
  (with-redefs [http/post stub-get]
    (is (= "Hello World" (:body (http/get "http://service.com/greet"))))))
```

### 2. 네임스페이스에 있는 모든 Var를 다른 네임스페이스에 있는 Var로 다시 정의하기

```clojure
(defn- var-symbol [var]
  (symbol (str (.ns var)) (str (.sym var))))

(defn- var-map [source-ns target-ns]
  (let [target (ns-interns target-ns)]
    (reduce (fn [r [k v]]
              (let [target-v (get target k)]
                (if (and target-v (not (:macro (meta v))))
                  (conj r v target-v)
                  r)))
            []
            (ns-interns source-ns))))

(defmacro with-ns-redefs [bindings & body]
  (let [bindings (map var-symbol (mapcat (fn [[s t]]
                                           (var-map s t))
                                         (partition 2 bindings)))]
    `(with-redefs ~bindings
       ~@body)))

(require '[org.httpkit.client :as http])
(require '[myproject.mock :as mock])

(deftest test-get
 (with-ns-redefs [org.httpkit.client myproject.mock]
   (is (= "Hello World" (:body (http/get "http://service.com/greet"))))))
```
