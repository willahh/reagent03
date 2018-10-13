(ns reagent03.front.page.actor)

(def state (atom {:rows [{:name "María Pedraza",
                          :actor "Actrice espagnole",
                          :thumb
                          "http://fr.web.img4.acsta.net/c_120_160/pictures/17/02/22/13/27/316814.jpg"}
                         
                         {:name "Anya Chalotra",
                          :actor "Actrice américaine",
                          :thumb
                          "http://fr.web.img2.acsta.net/c_120_160/pictures/18/10/12/15/45/0880064.jpg"}
                         {:name "Bradley Cooper",
                          :actor "Acteur, Producteur, Producteur délégué américain",
                          :thumb
                          "http://fr.web.img1.acsta.net/c_120_160/pictures/15/11/20/15/05/267278.jpg"}]}))

(defn page-html []
  [:div
   [:h3 "Actor"]
   [:div.ui.three.column.grid
    (map (fn [actor-row]
           [:div.column {:key (:name actor-row)}
            [:div.ui.fluid.card
             [:div.image
              [:img {:src (:thumb actor-row) :style {:width 60}}]]
             [:div.content
              [:a.header (:name actor-row)]]]]) (:rows @state))]])
