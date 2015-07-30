(ns charlie.models.db
  (:require [clojure.java.jdbc :as sql]))

(def db {:subprotocol "postgresql"
         :subname "//localhost/reporting"
         :user "admin"
         :password "admin"})

(defn create-employee-table
  []
  (sql/create-table
    :employee
    [:name "varchar(50)"]
    [:occupation "varchar(50)"]
    [:place "varchar(50)"]
    [:country "varchar(50)"]))

(sql/with-connection
  db
  (create-employee-table)
  (sql/insert-rows
   :employee
  ["Hal Ashby", "Director", "Hollywood", "USA"]
  ["Stanley Kubrick", "Director", "London", "England"]
  ["Francis Ford Coppola", "Director", "St. Helena", "USA"]
  ["David Lead", "Director", "London", "England"]
  ["Wes Anderson", "Director", "New York", "USA"]))

(defn read-employees []
  (sql/with-connection db
    (sql/with-query-results rs ["select * from employee"] (doall rs))))

