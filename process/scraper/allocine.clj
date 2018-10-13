(ns reagent03.process.scraper.allocine
  (:require [reagent03.process.scraper.scraper-helper :refer [init-phantomjs get-html get-html-memoise cleanup]]
            [clojure.string :as str])
  (:use [soupup.core :as soupup]))

(defn movie-best-list []
  (let [url "http://www.allocine.fr/film/meilleurs/"
        html-body (get-html-memoise url)
        nodes (parse html-body)
        id (->> (selectup nodes ".card .meta-title-link")
                (map (fn [m]
                       (-> (re-find #"\/film\/fichefilm_gen_cfilm=(\w+).html"
                                    (-> m second :href))
                           second)))
                (into []))
        title (->> (selectup nodes ".card .meta-title-link")
                   (map (fn [m]
                          (-> m (nth 2))))
                   (into []))
        src   (->> (selectup nodes ".card .thumbnail-img")
                   (map (fn [m] 
                          (if (re-find #"^data:" (-> m second :src))
                            (-> m second :src)
                            (-> m second :src))))
                   (into []))
        src   (->> (selectup nodes ".card .thumbnail-img")
                   (map (fn [m] 
                          (-> m second :data-src)))
                   (into []))]
    (map (fn [m]
           (zipmap [:id :title :src] m))
         (map vector id title src))))

(defn get-actor-list []
  (let [url "http://www.allocine.fr/personne/top/les-plus-vues/general/"
        html-body (get-html-memoise url)
        nodes (parse html-body)
        id (->> (selectup nodes ".colcontent .contenzone .titlebar h2 a")
                (map (fn [m]
                       (-> (re-find #"/personne/fichepersonne_gen_cpersonne=(\w+).html"
                                    (-> m second :href ))
                           second))
                     ))
        name (->> (selectup nodes ".colcontent .contenzone .titlebar h2 a")
                  (map (fn [m]
                         (-> m (nth 2) cleanup))))
        actor (->> (selectup nodes ".colcontent .contenzone")
                   (map (fn [m]
                          (-> m (nth 5) cleanup))))
        thumb (->> (selectup nodes ".colcontent .picturezone img")
                   (map (fn [m]
                          (-> m second :src))))]
    (map (fn [m]
           (zipmap [:id :name :actor :thumb] m))
         (map vector id name actor thumb))))

(defn get-actor [actorid]
  (let [url (str "http://www.allocine.fr/personne/fichepersonne_gen_cpersonne=" actorid ".html")
        html-body (get-html-memoise url)
        nodes (parse html-body)
        name (->> (selectup nodes ".titlebar-title-lg")
                  first second cleanup)
        job (->> (selectup nodes ".meta-body-item a")
                 (map (fn [m]
                        (-> m (nth 2))))
                 (filter #(not= % " "))
                 (into []))
        birth-name (->> (selectup nodes ".meta-body-item h2")
                        first second cleanup)
        nationality (-> (selectup nodes ".meta-body-item")
                        (nth 2) (nth 3) cleanup)
        age (-> (selectup nodes ".meta-body-item")
                (nth 4) (nth 4) second cleanup)
        biography (-> (selectup nodes ".ovw .content-txt")
                      first second cleanup)]
    (zipmap [:name :job :birth-name :nationality :ae :biography]
            [name job birth-name nationality age biography])))
