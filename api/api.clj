(ns reagent03.api.api
  (:require [compojure.core :refer [defroutes GET]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [cheshire.core :refer [generate-string]]
            [reagent03.process.scraper.allocine :refer [movie-best-list]]))

(def api-route
  (-> (defroutes api-routes
        (GET "/api/movie" []
             (-> {:rows movie-best-list} (generate-string))))
      (wrap-json-body)))
