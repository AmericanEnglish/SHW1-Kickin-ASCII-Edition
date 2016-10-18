(ns shw1.core
  (:gen-class)
  (:require [resources.user-commands :refer :all])
)

(defn sanitize_input 
  [input]
  (let [splitt (clojure.string/split input #" ")]
    (apply list (clojure.string/lower-case (nth splitt 0))
           (drop 1 splitt))
  )
)

(defn handler 
  "Handles the users commands, dispatching them to and fro"
  [input commands player]
  ; This is obviously filler code for now
  (let [parsed (sanitize_input input)]
    (let [result (search_command_name (nth parsed 0) commands)]
      (if (not (empty? result))
        ((:fn result) (clojure.string/join " " (drop 1 parsed)) player)
        (println "Command not found!")
      )
    )
  )  
)


(defn gather_input 
  "Gathers users command input and parses it"
  []
;  (print "=CMD=> ")
;  (flush)
  (loop [player plyr]
    (do 
      (print "\n=CMD=> ")
      (flush)
      (let [input (read-line)]
        (if (not (clojure.string/blank? input))
          (recur (handler input commands player))
          (do
            (println "\n???")
            (recur player)
          )
        )
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

