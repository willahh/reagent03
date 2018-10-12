(ns ^:figwheel-hooks reagent03.route
  (:require
   [reagent.core :as reagent :refer [atom]]
   [secretary.core :as secretary :include-macros true :refer [defroute dispatch!]]
   [accountant.core :as accountant]
   [ajax.core :refer [GET POST]]
   [reagent03.component.html-wrapper.wrapper :refer [wrap-page-html]]
   [reagent03.front.page.home :as home]
   [reagent03.front.page.about :as about]))

(defn about-page []
  [:div [:h2 "About reagent02"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -----------
;; Routes
(defonce page (atom #'home/page-html))

(defn current-page []
  [:div [@page]])

(defroute "/" []
  (reset! page (fn []
                 (-> (home/page-html)
                     wrap-page-html))))

(defroute "/about" []
  (reset! page (fn []
                 (-> (about/page-html)
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
