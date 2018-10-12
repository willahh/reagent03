(ns reagent03.component.html-wrapper.wrapper
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]
            [reagent03.component.html-wrapper.main :as main]))

(defn wrap-page-html [html]
  [:div (main/header-html)
   [:div.ui.container html]])
