(ns reagent03.component.html-wrapper.wrapper
  (:require [hiccup.page :as page]
            [reagent03.component.html-wrapper.main :as main]))

(defn wrap-page-html [html]
  (page/html5 (main/head)
              [:body
               [:div {:id "app"}
                [:div (main/header-html)]
                [:div.ui.main.text.container {:style "margin-top: 5em;"} html]]
               ;; (include-js "/cljs-out/dev-main.js")
               [:script {:type "text/javascript", :src "/cljs-out/dev-main.js"}]
               ]))
