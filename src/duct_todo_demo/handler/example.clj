(ns duct-todo-demo.handler.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :duct-todo-demo.handler/example [_ options]
  (context "/example" []
    (GET "/" []
      {:body {:example "data"}}(io/resource "duct_todo_demo/handler/example/example.html"))))
