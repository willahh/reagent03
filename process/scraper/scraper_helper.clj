(ns reagent03.process.scraper.scraper-helper
  (:require [clj-webdriver.taxi :as taxi]
            [clj-webdriver.driver :as driver])
  (:import (org.openqa.selenium.phantomjs PhantomJSDriver)
           (org.openqa.selenium.remote DesiredCapabilities)))

(def phantomjs-open? (atom false))

(defn init-phantomjs []
  (taxi/set-driver! (driver/init-driver
                     {:webdriver (PhantomJSDriver. (DesiredCapabilities.))}))
  (reset! phantomjs-open? true))

(defn get-html [url]
  (when-not @phantomjs-open?
    (init-phantomjs))
  (-> (taxi/get-url url)
      (taxi/html "body")))

(def get-html-memoise (memoize get-html))
