(ns duct-todo-demo.boundary.todos
  (:require [clojure.java.jdbc :as jdbc]
            duct.database.sql))
 
(defprotocol Todo
  (get-tables [db])
  (get-todos [db])
  )

(extend-protocol Todo
  duct.database.sql.Boundary
  (get-all-todos [{:keys [spec]}]
    (jdbc/query spec ["select * from todos"])
    )
  (get-tables [{:keys [spec]}]
    (jdbc/query spec ["select name from sqlite_master where type = 'table'"])))
