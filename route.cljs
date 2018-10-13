(ns ^:figwheel-hooks reagent03.route
  (:require
   [reagent.core :as reagent :refer [atom]]
   [secretary.core :as secretary :include-macros true :refer [defroute dispatch!]]
   [accountant.core :as accountant]
   [ajax.core :refer [GET POST]]
   [reagent03.component.card :refer [card-html]]
   [reagent03.component.html-wrapper.wrapper :refer [wrap-page-html]]
   [reagent03.front.page.home :as home]
   [reagent03.front.page.about :as about]
   [reagent03.front.page.week :as week]
   [reagent03.front.page.actor :as actor]
   [reagent03.front.page.actor-detail :as actor-detail]))

(defonce state (atom {:start 1
                      :toto [{:id "1"
                              :name "Todo 1"
                              :done true}
                             {:id "2"
                              :name "Todo 2"
                              :done false}
                             {:id "3"
                              :name "Todo 3"
                              :done false}]}))

(defn about-page []
  [:div [:h2 "About reagent02"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -----------
;; Routes
(defonce page (atom #'home/page-html state))

(defn current-page []
  [:div [@page state]])

(defroute "/" []
  (reset! page (fn []
                 (-> (home/page-html state)
                     wrap-page-html))))

(defroute "/about" []
  (reset! page (fn []
                 (-> (about/page-html)
                     wrap-page-html))))

(defroute "/week" []
  (reset! page (fn []
                 (-> (week/page-html)
                     wrap-page-html))))

(defroute "/actor" []
  (reset! page (fn []
                 (-> (actor/page-html)
                     wrap-page-html))))
(defroute "/actor/:actorid" []
  (reset! page (fn []
                 (-> (actor-detail/page-html)
                     wrap-page-html))))

;; --------
;; Initialize
(defn mount-app-element []
  (when-let [el (.getElementById js/document "app")]
    (reagent/render-component [current-page] el)))

(mount-app-element)
(accountant/configure-navigation! {:nav-handler
                                   (fn [path]
                                     (secretary/dispatch! path))
                                   :path-exists?
                                   (fn [path]
                                     (secretary/dispatch! path))})
(accountant/dispatch-current!)

(defn ^:after-load on-reload []
  (mount-app-element))
