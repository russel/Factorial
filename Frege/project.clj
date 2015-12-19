(defproject factorial "0.0.0-SNAPSHOT"
  :description "Factorial function with tests in Frege."
  :url "https://github.com/russel/Factorial/tree/master/Frege"
  :license {:name "GPL v3"
            :url "http://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [
                 [org.frege-lang/frege "3.23.443-gad63824"]]
  :repositories {"clojars" "https://clojars.org/org.frege-lang/frege"
                 "sonatype" "https://oss.sonatype.org/content/repositories/snapshots/"}
  :plugins [[lein-fregec "3.23.450"]]
  :frege-source-paths ["src" "test"]
  :profiles {:uberjar {:aot :all
                       :prep-tasks ["fregec" "compile"]}})
