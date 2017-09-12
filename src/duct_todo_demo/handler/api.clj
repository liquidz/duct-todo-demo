(ns duct-todo-demo.handler.api
  (:require
    [compojure.core  :refer :all]
    [clojure.java.io :as    io]
    [integrant.core  :as    ig]
    [duct-todo-demo.boundary.todos :as db]
    ))

(defmethod ig/init-key :duct-todo-demo.handler/api [_ options]
  (context "/api" []
    ;; get all todo
    (GET "/" []
      {:body (-> options :db db/get-all-todos)}
      )
    ;(GET "/:id" []
    ;  {:body }
    ;  )
    (PUT "/" {:keys [params]}
      {:body (-> options :db (db/insert-todo! [(:name params) (:description params)]))}
      )
    )
  )
