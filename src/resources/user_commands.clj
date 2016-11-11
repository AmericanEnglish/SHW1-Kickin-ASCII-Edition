(ns resources.user-commands
  (:require [resources.rooms :refer :all])
)
(declare commands)
(declare mappu)

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
    (println "User commands: enter, unlock, look, pack, halp, quit. Type \"halp command\" for more information on the command.")
    (if-let [res (search_command_name command commands)]
      (println (:description res))
      (println "No such command found!") 
    )
  )
  (hash-map
    :player player
    :rooms rooms
  )
)

(defn grab_room 
  [player rooms]
  (nth (filter #(= (:id %) (:location player) ) rooms) 0)
)

(defn find_exits_hash
  [exits rooms]
  ; (let [room_ints (map (:id rooms))]
    (loop [cur_rooms exits room_maps []]
      (if (empty? cur_rooms)
        room_maps
        (recur (drop 1 cur_rooms) ; One room has been filtered for
               (apply conj room_maps ; Put that into the vector of rooms
                     (filterv #(= (:id %) (nth cur_rooms 0)) rooms) ; Filter for one room
               )
        )
      )
    )
  ; )
)

(defn grab_exits
  [player rooms]
  (find_exits_hash 
    (:exits (grab_room player rooms)) rooms
  )
)

(defn unlock
  [room]
  (assoc room :locked false)
)

(defn unlock_room
  "Allows the player to unlock a room"
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
      (if (not (:locked (nth result 0)))
        (do
          (println "Room is already unlocked!")
          (hash-map :player player :rooms rooms)
        )
        (if (> (:keyz (nth (:pack player) 0)) 0)
          (do
            (println (str "Unlocked " args "!"))
            (let [unlocked (unlock (nth result 0))]
              (let [pack (nth (:pack player) 0)]
                (hash-map
                  :player (assoc player :pack (update_pack (:pack player) pack ()))
                  :rooms (conj (drop-item rooms result) unlocked)
                )
              )
            )
          )
          (do 
            (println "Out of keyz!")
            (hash-map :player player :rooms rooms)
          )
        )
      )
      (do
        (println "You cannot unlock that which does not exist!")
        (hash-map :player player :rooms rooms)
      )
    )
  )
)

(defn enter
  "Allows the player enter the room"
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
      (if (not (:locked (nth result 0)))
        (do 
          (let [newplayer (assoc player :location (:id (nth result 0)))]
            (println (str "Entered " args " !"))
            (println (str "\n" (:description (nth result 0))))
            (if (> (count (grab_exits newplayer rooms)) 1)
              (hash-map 
                :player newplayer
                :rooms rooms 
              )
              (link_player_room (hash-map :player newplayer :rooms rooms) 3)
            )
          )
        )
        (do
          (println (str args " is locked!"))
          (print "Would you like to unlock the room? (y/N): ")
          (flush)
          (let [answer (read-line)]
            (if (= (clojure.string/lower-case answer) "y")
              (do
                (let [new_data (unlock_room args player rooms)]
                  (enter args (:player new_data) (:rooms new_data))
                )
              )
              (do
                (println "Did not unlock!")
                (hash-map
                  :player player
                  :rooms rooms
                )
              )
            )
          )
        )
      )
      (do
        (println (str "There is no exit: " args "!"))
        (hash-map
          :player player
          :rooms rooms
        )
      )
    )
  )
)



(defn look 
  "Allows player to look around a room"
  [args player rooms]
  (let [cur_room (grab_room player rooms)]
    (do
      (println (str "* " (:name cur_room)))
      (println (str "=> " (:description cur_room)))
    )
  )
  (println "*Exits:")
  (loop [res (map :name (grab_exits player rooms))]
    (if (not (empty? res))
      (do
        (println (str "+ "(nth res 0)))
        (recur (drop 1 res))
      )
    )
  )
  (hash-map
    :player player
    :rooms rooms
  )
)

(defn update_pack
  [current_pack old_item updated_item]
  (conj (drop-item current_pack old_item) updated_item)
)

(defn pack
  "Allows the player to view their inventory"
  [args player rooms]
  (println (str "keyz remaining: " (:keyz (nth (:pack player) 0))))
  (hash-map
    :player player
    :rooms rooms
  )
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
      (hash-map
        :player player
        :rooms rooms
      )
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
              :name "unlock"
              :description "User types \"unlock room\" to unlock the room only if the player has enough keyz."
              :fn unlock_room
          )
          (hash-map 
              :name "look" 
              :description "User types \"look\" with no arguments to display current room information."
              :fn look
          )
          (hash-map
              :name "pack"
              :description "User types \"pack\" with no arguments to display the inventory of the player."
              :fn pack
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
  (hash-map
    :player (hash-map 
              :location 1
              :pack (list
                      (hash-map
                        :name "keyz"
                        :amount 25
                      )
                    )
            )
    :rooms nil
  )
)
