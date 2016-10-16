(ns shw1.core
  (:gen-class)
  (:require [resources.user-commands :refer :all])
)

(defn handler 
  "Handles the users commands, dispatching them to and fro"
  [input]
  ; This is obviously filler code for now
  (clojure.string/split input #" ")
)


(defn gather_input 
  "Gathers users command input and parses it"
  []
  (print "=CMD=> ")
  (flush)
  (loop [input (read-line)]
    (if (not (clojure.string/blank? input))
      (do
        (handler input)
        (print "=CMD=> ")
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

