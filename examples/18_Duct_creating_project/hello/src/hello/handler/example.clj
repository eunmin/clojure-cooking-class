(ns hello.handler.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :hello.handler/example [_ options]
  (context "/example" []
    (GET "/" []
      (io/resource "hello/handler/example/example.html"))))
