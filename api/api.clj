(ns reagent03.api.api
  (:require [compojure.core :refer [defroutes GET]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [cheshire.core :refer [generate-string]]
            [reagent03.process.scraper.allocine :as scraper-allocine]))

(def api-route
  (-> (defroutes api-routes
        (GET "/api/movie" []
             (-> {:rows (scraper-allocine/movie-best-list)} (generate-string)))
        (GET "/api/actor" []
             (-> {:rows (scraper-allocine/get-actor-list)} (generate-string))))
      (wrap-json-body)))
