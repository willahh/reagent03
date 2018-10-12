(ns reagent03.process.scraper.allocine
  (:require [reagent03.process.scraper.scraper-helper :refer [init-phantomjs get-html get-html-memoise]]
            [clojure.string :as str])
  (:use [net.cgrand.enlive-html :as html]))

(def movie-best-list
  (let [url "http://www.allocine.fr/film/meilleurs/"
        html-body (get-html-memoise url)
        nodes (html-snippet html-body)
        id (into []
                 (->> (select nodes [:.card :.meta-title-link])
                      (map (fn [m]
                             (second (re-find #"\/film\/fichefilm_gen_cfilm=(\w+).html"
                                              (-> m :attrs :href )))))))
        title (into []
                    (->> (select nodes [:.card :.meta-title-link])
                         (map (fn [m]
                                (-> m :content first)))))
        src (->> (select nodes [:.card :.thumbnail-img])
                 (map (fn [m]
                        (if (re-find #"^data:" (-> m :attrs :src))
                          (-> m :attrs :data-src)
                          (-> m :attrs :src)))))]
    (into []
          (map (fn [m]
                 (zipmap [:id :title :src] m)) (map vector id title src)))))




