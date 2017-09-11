(ns duct-todo-demo.handler.api-test
  (:require [clojure.test :refer :all]
            [kerodon.core :refer :all]
            [kerodon.test :refer :all]
            [integrant.core :as ig]
            [shrubbery.core :refer [stub]]
            [duct-todo-demo.boundary.todos :as todo]
            ))

(def database-stub
  (stub todo/Todo
    {:get-tables [{:name "test-todos"}]
     :get-all-todos [{:id 1 :name "foo" :description "bar"}]
     }))

(def handler
  (ig/init-key :duct-todo-demo.handler/api
               { :db database-stub }))

(deftest smoke-test
  (testing "get todos"
    (-> (session handler) (visit "/api"))
    )
  (testing "api page exists"
    (let [res (-> (session handler)
                  (visit "/api"))]
      (has res (status? 200))
      (is (= "data" (-> res :response :body :example)))
      (is (= "data" (-> res :response :body :tables)))

      )
    ;(-> (session handler)
    ;    (visit "/api")
    ;    nil?
    ;    is
    ;    ;(has (status? 200) "page exists")
    ;    )
    ))
