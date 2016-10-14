(ns shw1.core
  (:gen-class))

(defn parse-input 
  "Parses input into a head and tail fashion"
  [input]
  ; This is obviously filler code for now
  (vector "str" 12)
)

(defn handler 
  "Handles the users commands, dispatching them to and fro"
  [command & args]
  ; This is obviously filler code for now
  (println "I am an empty function\n")
)


(defn gather_input 
  "Gathers users command input and parses it"
  []
  (print "=CMD=> ")
  (flush)
  (loop [input (read-line)]
    (if (not (clojure.string/blank? input))
      (do
        (handler 
          (parse-input input)
        )
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
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello! ARE YOU READY?")
  (gather_input)
)
