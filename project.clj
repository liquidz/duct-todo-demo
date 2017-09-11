(defproject duct-todo-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [
                 [org.clojure/clojure            "1.9.0-alpha19"]
                 [duct/core                      "0.6.1"]
                 [duct/module.logging            "0.3.1"]
                 [duct/module.web                "0.6.2"]
                 [duct/module.cljs               "0.3.1"]
                 [duct/module.sql                "0.4.0"]
                 [org.xerial/sqlite-jdbc         "3.19.3"]
                 [duct/migrator.ragtime          "0.2.0"]
                 [com.gearswithingears/shrubbery "0.4.1"]
                 ]
  :plugins [[duct/lein-duct "0.10.2"]]
  :main ^:skip-aot duct-todo-demo.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user
                         :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.2.0"]
                                   [eftest "0.3.1"]
                                   [kerodon "0.8.0"]]}})
