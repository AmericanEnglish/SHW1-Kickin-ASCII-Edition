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
  [input commands user]
  ; This is obviously filler code for now
  (let [
        parsed (sanitize_input input) 
        player (:player user) 
        rooms  (:rooms  user)
      ]
    (let [result (search_command_name (nth parsed 0) commands)]
      (if (not (empty? result))
        ((:fn result) (clojure.string/join " " (drop 1 parsed)) player rooms)
        (do
          (println "Command not found!")
          user
        )
      )
    )
  )  
)


(defn gather_input 
  "Gathers users command input and parses it"
  [user]
;  (print "=CMD=> ")
;  (flush)
  (loop [player user]
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

(defn spit_rooms
  [filename rooms]
  (spit filename "(defn mappu (list\n\n")
  (loop [stuff rooms]
    (if (not (empty? stuff))
      (do
        (spit filename (str (nth stuff 0) "\n\n") :append true)
        (recur (drop 1 stuff))
      )
      (spit filename "))" :append true)
    )
  )
)

(defn begin 
  "Begins the game with a randomly generated map"
  [room_total]
  (let [
          filename "recent_map.clj" 
          new_player (link_player_room
                        (assoc plyr :rooms (nth (gen_map room_total) 0))
                      4
                      )
        ]
    (spit_rooms filename
      (:rooms (gather_input new_player))
    )
   )
) 

;(defn splash)

(defn -main
  "Gets the program going! Without where would we be?"
  [& args]
  (println (slurp "house.txt"))
  (begin 500)
)
;   (let [filename "main_mappu.clj"]
;     (do
;       (let [new_player (assoc plyr :rooms (nth (gen_map 500) 0))]
;         (let [newest_player (link_player_room new_player 4)]
;           (spit_rooms filename (:rooms newest_player))
;         )
;       )
;     )
;   )
; )
