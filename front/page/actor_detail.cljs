(ns reagent03.front.page.actor-detail
  (:require [ajax.core :refer [GET POST]]))

(def state (atom {:name "",
                  :job [],
                  :birth-name "",
                  :nationality "",
                  :age "",
                  :biography ""}))

(defn fetch-actor [actor-id]
  (GET (str "http://localhost:9500/api/actor/" actor-id)
       :handler (fn [resp]
                  (let [json-data (js->clj (js/JSON.parse resp) :keywordize-keys true)]
                    (swap! state assoc-in [:rows] (:rows json-data))))))

(defn page-html []
  [:div
   [:div (:name @state)]])
