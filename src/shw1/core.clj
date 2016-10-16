(ns shw1.core
  (:gen-class)
  (:require [resources.user-commands :refer :all])
  (:require [resources.rooms :refer :all])
)


(defn handler 
  "Handles the users commands, dispatching them to and fro"
  [input commands]
  ; This is obviously filler code for now
  (let [parsed (clojure.string/split input #" ")]
    (let [result (search_command_name (nth parsed 0) commands)]
      (if (not (empty? result))
        ((:fn result) (clojure.string/join " " (drop 1 parsed)))
        (println "Command not found!")
      )
    )
  )  
)


(defn gather_input 
  "Gathers users command input and parses it"
  []
  (print "=CMD=> ")
  (flush)
  (loop [input (read-line)]
    (if (not (clojure.string/blank? input))
      (do
        (handler input commands)
        (print "\n=CMD=> ")
        (flush)
        (recur (read-line))
      )
      (do
        (println "???\n")
        (print "=CMD=> ")
        (flush)
        (recur (read-line))
      )
    )
  )
)


(defn -main
  "Gets the program going! Without where would we be?"
  [& args]
  (println "Hello! ARE YOU READY?")
  (gather_input)
)

