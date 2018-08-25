(ns graphqlapp.core
  (:gen-class)
  (:require [com.walmartlabs.lacinia.schema :as schema]
            [com.walmartlabs.lacinia.parser.schema :refer [parse-schema]]
            [com.walmartlabs.lacinia :as lacinia]
            [compojure.core :refer [context defroutes GET POST wrap-routes]]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn hero [context args value]
  {:name "R2-D2"
   :appearsIn ["NEWHOPE" "EMPIRE" "JEDI"]})

(def schema (schema/compile (parse-schema (slurp (clojure.java.io/resource "schema.txt"))
                                          {:resolvers {:Query {:hero hero}}})))

(defroutes app
  (context "/graphql" []
    (GET "/" {:keys [params]}
      (response (lacinia/execute schema (get params "query") nil nil)))
    (POST "/" {{:keys [query variables operationName]} :body}
      (response (lacinia/execute schema query variables nil {:operation-name operationName})))))

(def handler
  (-> app
      (wrap-routes wrap-json-response)
      (wrap-routes wrap-json-body {:keywords? true :bigdecimals? true})
      (wrap-routes wrap-params)))

(defn -main []
  (run-jetty handler {:port 3000}))
