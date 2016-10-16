(ns shw1.core
  (:gen-class))

(defn handler 
  "Handles the users commands, dispatching them to and fro"
  [args]
  ; This is obviously filler code for now
  (println args)
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
          (clojure.string/split input #" ")
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
