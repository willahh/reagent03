(ns reagent03.route-list
  (:require [reagent03.front.page.home :as home]
            [reagent03.front.page.about :as about]
            [reagent03.front.page.week :as week]
            [reagent03.front.page.actor :as actor]))

(def routes
  [{:uri "/"
    :handler home/page-html}
   {:uri "/about"
    :handler about/page-html}
   {:uri "/week"
    :handler week/page-html}
   {:uri "/actor"
    :handler actor/page-html}])