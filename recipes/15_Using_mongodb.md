# MongoDB 사용하기

by Eunmin Kim

## 이럴 때 쓰세요

클로저 애플리케이션에서 [MongoDB](https://www.mongodb.com/)를 사용하고 싶습니다.

## 어떻게 하나요?

### 1. Monger 의존성 추가하기

프로젝트에 클로저 MongoDB 클라이언트 [Monger]() 라이브러리를 추가합니다.

```clojure
[com.novemberain/monger "3.1.0"]
```

### 2. MongoDB에 연결하기

```clojure
(require '[monger.core :as mg])

;; localhost, default port
(defn conn (mg/connect))
```

### 3. 데이터 사용하기

```clojure
(require '[monger.core :as mg])
(require '[monger.collection :as mc])
(import 'org.bson.types.ObjectId)

(def db (mg/get-db conn "monger-test"))

(mc/insert db "documents" {:first_name "John" :last_name "Lennon"})

(mc/find-maps db "documents" {:first_name "John"})

(mc/update-by-id db "documents" (ObjectId. "4ec2d1a6b55634a935ea4ac8") {:last_name "Starr"})

(mc/remove db "documents" {:first_name "John"})
```

### 4. 연결 끊기

```clojure
(require '[monger.core :as mg])

(mg/disconnect conn)
```

## 이런 것도 보세요

* [CongoMongo](https://github.com/congomongo/congomongo)
