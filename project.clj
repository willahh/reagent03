(defproject reagent03 "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :min-lein-version "2.7.1"
  
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [org.clojure/spec.alpha "0.2.176"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]
                 [reagent "0.8.1"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 ;; [enlive "1.1.6"]
                 [secretary "1.2.3"]
                 [soupup "0.2.0"]
                 [clj-webdriver "0.7.2"]
                 
                 ;; Clojurescript
                 [hiccups "0.3.0"]
                 ;; [hiccups "0.2.0"]
                 ;; [com.github.willahh/hiccups "0.3.1"]
                 [com.github.detro.ghostdriver/phantomjsdriver "1.0.3"]
                 [venantius/accountant "0.2.4"
                  :exclusions [org.clojure/tools.reader]]
                 [cljs-ajax "0.7.4"]
                 ;; [hickory "0.7.1"]
                 ;; [clojure-soup "0.0.1"]
                 ;; [org.jsoup/jsoup "1.10.3"]
                 ;; [org.clojars.clizzin/jsoup "1.5.1"]


                 [hickory "0.7.1"]]
  
  :source-paths ["src"]
  
  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" reagent03.test-runner]}
  
  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.1.9"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]]
                   :resource-paths ["target"]
                   ;; need to add the compliled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["target"]}})

