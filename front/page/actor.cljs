(ns reagent03.front.page.actor
  (:require [ajax.core :refer [GET POST]]))

(defonce state (atom {:rows [{:id "1"
                              :name "María Pedraza",
                              :actor "Actrice espagnole",
                              :thumb
                              "http://fr.web.img4.acsta.net/c_120_160/pictures/17/02/22/13/27/316814.jpg"}
                             {:id "2"
                              :name "Anya Chalotra",
                              :actor "Actrice américaine",
                              :thumb
                              "http://fr.web.img2.acsta.net/c_120_160/pictures/18/10/12/15/45/0880064.jpg"}
                             {:id "3"
                              :name "Bradley Cooper",
                              :actor "Acteur, Producteur, Producteur délégué américain",
                              :thumb
                              "http://fr.web.img1.acsta.net/c_120_160/pictures/15/11/20/15/05/267278.jpg"}]}))

(defn fetch-actor [state]
  (GET (str "http://localhost:9500/api/actor")
       :handler (fn [resp]
                  (let [json-data (js->clj (js/JSON.parse resp) :keywordize-keys true)]
                    (do (println (:rows json-data))
                        (swap! state assoc-in [:rows] (:rows json-data)))))))

(defn page-html []
  [:div
   [:h3 "Actor"]
   [:button.ui.button {:on-click #(fetch-actor state)} "Fetch"]
   [:div (pr-str @state)]
   [:div.ui.three.column.grid
    (map (fn [actor-row]
           [:div.column {:key (:name actor-row)}
            [:a.ui.fluid.card {:href (str "/actor/" (:id actor-row))}
             [:div.image
              [:img {:src (:thumb actor-row) :style {:width 60}}]]
             [:div.content
              [:a.header (:name actor-row)]]]]) (:rows @state))]])
