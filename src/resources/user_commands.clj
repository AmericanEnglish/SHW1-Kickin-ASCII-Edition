(ns resources.user-commands
  (:require [resources.rooms :refer :all])
)
(declare commands)

(defn search_command_name
  [command commands]
  (let [res (filter #(= command (:name %)) commands)]
    (if (not (empty? res))
      (nth res 0)
    )
  )
)

(defn halp 
    "Displays help information about a specific command or just displays all commands"
    [command player rooms]
  (if (= command "")
    (println "User commands: enter, look, halp, quit. Type \"halp command\" for more information on the command.")
    (if-let [res (search_command_name command commands)]
      (println (:description res))
      (println "No such command found!") 
    )
  )
  player
)

(defn grab_room [player rooms]
    (nth (filter #(= (:id %) (:location player) ) rooms) 0)
)

(defn find_exits_hash
  [rooms]
  (let [room_ints (map (:id rooms))]
    (loop [cur_rooms room_ints room_maps []]
      (if (empty? cur_rooms)
        room_maps
        (recur (drop 1 cur_rooms) ; One room has been filtered for
               (apply conj room_maps ; Put that into the vector of rooms
                     (filterv #(= (:id %) (nth cur_rooms 0)) rooms) ; Filter for one room
               )
        )
      )
    )
  )
)

(defn grab_exits
  [player rooms]
  (find_exits_hash 
    (:exits (grab_room player rooms))
  )
)

(defn enter
    "Moves the player into the room"
    [args player rooms]
    (let [result 
          (filter 
            #(
              = (clojure.string/lower-case (:name %)) (clojure.string/lower-case args)
             ) 
             (grab_exits player rooms)
          )
        ]
      (if (not (empty? result))
        (if (not (:locked result))
          (do 
            (println (str "Entered " args " !"))
            (println (str "\n" (:description (nth result 0))))
            (assoc player :location (:id (nth result 0)))
          )
          (do
            (println (str args " is locked!"))
            player
          )
        )
        (do
          (println (str "There is no exit: " args "!"))
          player
        )
      )
    )
)

(defn look 
    "Allows player to look around a room"
    [args player rooms]
    (let [cur_room (grab_room player rooms)]
      (do
        (println (:name cur_room))
        (println (:description cur_room))
      )
    )
    (println "Exits:")
    (loop [res (map :name (grab_exits player rooms))]
      (if (not (empty? res))
        (do
          (println (nth res 0))
          (recur (drop 1 res))
        )
      )
    )
  player
)
      




(defn quit
  "Allows the user to end the game."
  [args player rooms]
  ;Carlos will put the ask for comfirmation code
  (print "Are you sure you want to leave? (y/N): ")
  (flush)
  (let [answer (read-line)]
    (if (= (clojure.string/lower-case answer) "y")
      (do
        (println "Goodbye!")
        (System/exit 0)
      )
      player
    )
  )
)
;We can place other user command functions in this file to stay organized.

(def commands
  (list (hash-map 
              :name "enter" 
              :description "User types \"enter room\" to move into the room only if the new room is attached to current room."
              :fn enter  
          )
          (hash-map 
              :name "look" 
              :description "User types \"look\" with no arguments to display current room information."
              :fn look
          )
          (hash-map 
              :name "halp" 
              :description "User types \"halp\" with no argument to display all user commands, or with an argument to show more information on command."
              :fn halp
          )
          (hash-map
              :name "quit"
              :description "User types \"quit\" with no argument to end the game."
              :fn quit
          )
  )
)

(def plyr 
    (hash-map :location 1)
)
