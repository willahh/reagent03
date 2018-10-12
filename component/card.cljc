(ns reagent03.component.card
  (:require [clojure.string :as str]))

(defn card-html [id href title description image meta]
  [:a.ui.card {:key id :href href}
   [:div 
    [:img {:src image :style {:max-width "60px"}}]]
   [:div {:class "content"}
    [:div {:class "header"}
     title]
    [:div {:class "meta"}
     (str/join "," meta)]
    [:div.desription
     {:style {:min-height "60px"}} description]]])
