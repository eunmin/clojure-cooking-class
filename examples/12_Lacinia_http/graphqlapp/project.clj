(defproject graphqlapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot graphqlapp.core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.walmartlabs/lacinia "0.29.0-rc-1"]
                 [ring "1.7.0-RC1"]
                 [compojure "1.6.1"]
                 [ring/ring-json "0.4.0"]])
