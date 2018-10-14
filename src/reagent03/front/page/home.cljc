(ns reagent03.front.page.home
  (:require [ajax.core :refer [GET POST]]
            [reagent03.component.card :refer [card-html]]))

;; (defonce state (atom {:start 1
;;                       :toto [{:id "1"
;;                               :name "Todo 1"
;;                               :done true}
;;                              {:id "2"
;;                               :name "Todo 2"
;;                               :done false}
;;                              {:id "3"
;;                               :name "Todo 3"
;;                               :done false}]}))

(defn fetch-movie [state]
  #?(:cljs
     (GET "http://localhost:9500/api/movie"
          :handler (fn [resp]
                     (let [json-data (js->clj (js/JSON.parse resp) :keywordize-keys true)]
                       (js/console.log json-data)
                       (swap! state assoc-in [:todo] (:rows json-data)))))))

(defn add-todo [state todo-row]
  (swap! state update-in [:todo] conj todo-row))

(defn delete-todo [state todo-id]
  (swap! state update-in [:todo]
         (fn [v]
           (filter #(not= (:id %) todo-id) v))))

(defn todo-ui [state]
  [:div.ui.stackable.six.column.grid
   (map (fn [m]
          [:div.column {:key (:id m)}
           (card-html (:id m) "" (:title m) "" (:src m) [])
           [:input {:type "checkbox" :name "ok" :checked (:done m)
                    :on-change #(delete-todo state (:id m))}]])
        (:todo @state))])

(defn page-html [state]
  [:div [:h2 "Welcome to reagent02"]
   [:div [:a {:href "/about"} "go to about page"]]
   [:div "Todos"]
   (todo-ui state)
   [:button {:on-click (fn [m]
                         (let [id (-> @state :todo count inc)] 
                           (add-todo state {:id id
                                            :name (str "Todo" id)
                                            :done false})))}
    "add"
    ]
   [:button {:on-click #(fetch-movie state)} "Load todos"]])