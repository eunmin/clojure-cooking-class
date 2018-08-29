(ns app.core
  (:require [puppetlabs.structured-logging.core :refer [maplog]]))

(defn -main []
  (maplog :warn {:user-service "https://..."
                 :status 503
                 :elapsed 27}
          #(format "Failed to query user-service %s. Response: status %d"
                   (:user-service %) (:status %))))
