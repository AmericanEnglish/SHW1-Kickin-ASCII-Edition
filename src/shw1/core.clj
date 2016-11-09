(ns shw1.core
  (:gen-class)
  (:require [resources.user-commands :refer :all])
  (:require [resources.rooms :refer :all])
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
  [input commands player rooms]
  ; This is obviously filler code for now
  (let [parsed (sanitize_input input)]
    (let [result (search_command_name (nth parsed 0) commands)]
      (if (not (empty? result))
        ((:fn result) (clojure.string/join " " (drop 1 parsed)) player rooms)
        (do
          (println "Command not found!")
          player
        )
      )
    )
  )  
)


(defn gather_input 
  "Gathers users command input and parses it"
  [rooms]
;  (print "=CMD=> ")
;  (flush)
  (loop [player plyr]
    (do 
      (print "\n=CMD=> ")
      (flush)
      (let [input (read-line)]
        (if (not (clojure.string/blank? input))
          (recur (handler input commands player rooms))
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
  (let [filename "main_mappu.clj"]
    (do
      (let [new_player (assoc plyr :rooms (nth (gen_map 500) 0))]
        (link_player_room new_player)
        (spit filename "(def mappu (list\n")
        (loop [stuff (:rooms new_player)]
          (if (not (empty? stuff))
            (do
              (spit filename (str (nth stuff 0) "\n\n") :append true)
              (recur (drop 1 stuff)))
            (spit filename "))" :append true)
          )
        )
      )
    )
  )
;  (gather_input)
  ; (let [info (gen_map 4)]
  ;   (do 
  ;     (println info)
  ;     (println (map_linker (nth info 0) (nth info 1)))
  ;   )
;  )
)
