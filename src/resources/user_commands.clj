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
    [command player]
  (if (= command "")
    (println "User commands: into, look, halp. Type \"halp command\" for more information on the command.")
    (if-let [res (search_command_name command commands)]
      (println (:description res))
      (println "No such command found!") 
    )
  )
  player
)

(defn grab_room [player]
    (filter #(= (:id %) (:location player) ) rooms)
)

(defn exit_loc [room]
  (filter #(= :id %)
  (:exit room))
)

(defn Into
    "Moves the player into the room"
    [args player]
    (if-let [result (filter #(= (:name (exit_loc (grab_room player))) args))]
    (update player :location ))

)

(defn look 
    "Allows player to look around a room"
    [args player]
    (println "An empty \"look\" command" )
)

(defn quit
  "Allows the user to end the game."
  [args player]
  ;Carlos will put the ask for comfirmation code
  (println "Goodbye!")
  (System/exit 0)
)
;We can place other user command functions in this file to stay organized.

(defn find_exits_hash
  [room_ints]
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

(defn grab_exits
  [player]
  (find_exits_hash 
    (:exits (grab_room player))
  )
)

(def commands
  (list (hash-map 
              :name "into" 
              :description "User types \"into room id\" to move into the room only if the new room is attached to current room."
              :fn Into  
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
    (hash-map :location nil)
)
