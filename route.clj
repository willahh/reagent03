(ns reagent03.route
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [hiccup.page :refer [html5 include-css include-js]]
            [reagent03.api.api :as api]
            [reagent03.component.html-wrapper.wrapper :refer [wrap-page-html]]))

(defn index-html []
  [:div "INDEX HTML"])

(defroutes handler
  (GET "/" [] (-> index-html
                  wrap-page-html))
  (GET "/about" [] (-> index-html
                       wrap-page-html))
  api/api-route
  (route/not-found "<h1>Page not found</h1>"))