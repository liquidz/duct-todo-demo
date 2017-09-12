(ns duct-todo-demo.boundary.todos
  (:require [clojure.java.jdbc :as jdbc]
            duct.database.sql))
 
(defprotocol Todo
  (get-all-todos [db])
  (insert-todo! [db todo])
  )

(extend-protocol Todo
  duct.database.sql.Boundary
  (get-all-todos [{:keys [spec]}]
    (jdbc/query spec ["select * from todos"]))
  (insert-todo! [{:keys [spec]} todo-record]
    (jdbc/insert! spec :todos [:name :description] todo-record))
  )
