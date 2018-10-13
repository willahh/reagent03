(ns reagent03.process.scraper.scraper-helper
  (:require [clojure.string :as str]
            [clj-webdriver.taxi :as taxi]
            [clj-webdriver.driver :as driver])
  (:import (org.openqa.selenium.phantomjs PhantomJSDriver)
           (org.openqa.selenium.remote DesiredCapabilities)))

(def phantomjs-open? (atom false))

(defn init-phantomjs []
  (taxi/set-driver! (driver/init-driver
                     {:webdriver (PhantomJSDriver. (DesiredCapabilities.))}))
  (reset! phantomjs-open? true))

(defn remove-meta-itemprop [html]
  (str/replace html #"<meta itemprop=\"(\w+)\" content=\"(.+)\">" ""))

(defn remove-html-comment [html]
  (str/replace html #"(\s+)<!--[| ](.+)[| ]-->" ""))

(defn remove-empty-tag [html]
  (str/replace html #"(\s+)<(\w+)(| class=\"(.+)\")>(|(\s+))</(\w+)>" ""))

(defn remove-empty-textnode [html]
  (str/replace html #"\n(\s+)\n" ""))

(defn cleanup [str]
  "Removes excess spaces at the beginning and end of the chain, as well as line
breaks."
  (if str (-> (clojure.string/replace str #"\n" "")
              (clojure.string/replace #" +$" "")
              (clojure.string/replace #"^ +" "")
              (clojure.string/replace #"" ""))
      ""))

(defn get-html [url]
  (when-not @phantomjs-open?
    (init-phantomjs))
  (-> (taxi/get-url url)
      (taxi/html "body")
      (remove-meta-itemprop)
      (remove-html-comment)
      ;; (remove-empty-tag)
      (remove-empty-textnode)))

(def get-html-memoise (memoize get-html))
