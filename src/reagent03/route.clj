(ns reagent03.route
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [hiccup.page :refer [html5 include-css include-js]]
            [reagent03.api.api :as api]
            [reagent03.front.page.home :as home]
            [reagent03.front.page.about :as about]
            [reagent03.front.page.week :as week]
            [reagent03.front.page.actor :as actor]
            [reagent03.component.html-wrapper.wrapper :refer [wrap-page-html]]))

(defroutes handler
  (GET "/" [req] (-> (home/page-html)
                     wrap-page-html))
  (GET "/about" [req] (-> (about/page-html)
                          wrap-page-html))
  (GET "/week" [req] (-> (week/page-html)
                         wrap-page-html))
  (GET "/actor" [req] (-> 
                       (wrap-page-html "a")))
  api/api-route
  (route/not-found "<h1>Page not found</h1>"))