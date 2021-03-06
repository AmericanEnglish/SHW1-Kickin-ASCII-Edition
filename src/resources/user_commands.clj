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
    (println "User commands: enter, see, unlock, look, get, drop, pack, halp, quit. Type \"halp command\" for more information on the command.")
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

;(defn isolate_keys
;  "Given the pack, will find yo keyz"
;  [pack]
;  (nth (filterv #(= "keyz" (:name %)) pack) 0)
;)

(defn grab_item 
  "isolates an item for use by other function. Takes the pack and a string. Returns a hash-map"
  [backpack args]
  (let [res (filter #(= (clojure.string/lower-case args) (clojure.string/lower-case (:name %))) backpack)]
    (if (empty? res)
      (hash-map)
      (nth res 0)
    )
  )   
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

(defn update_pack
  [current_pack old_item updated_item]
  (if (> (:amount updated_item) 0)
    (conj (drop-item current_pack old_item) updated_item)
    (do
      (println (str (:break updated_item)))
      (drop-item current_pack old_item)
    )
  )
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
        (let [keyz (grab_item (:pack player) "Fireaxe")]
          (if (> (:amount keyz) 0)
            (do
              (println (str "With a swift axe blade you forcibly \"unlocked\" " args "!"))
              (let [unlocked (unlock (nth result 0))]
                (let [pack (nth (:pack player) 0)]
                  (hash-map
                    :player 
                      (assoc player :pack 
                             (update_pack (:pack player) 
                                          keyz 
                                          (assoc keyz :amount (- (:amount keyz) 1))))
                    :rooms (conj (drop-item rooms result) unlocked)
                  )
                )
              )
            )
            (do 
              (println "Out of swings!")
              (hash-map :player player :rooms rooms)
            )
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

(defn look 
  "Allows player to look around a room"
  [args player rooms]
  (let [cur_room (grab_room player rooms)]
    (do
      (println (str "* " (:name cur_room)))
      (println (str "=> " (:description cur_room)))
      (println (str "*Items in " (:name cur_room) ":"))
      (loop [room_inv (:inventory cur_room)]
        (if (not (empty? room_inv))
          (do
            (println (str "=> " (:name (nth room_inv 0)) 
                          " {" (:amount (nth room_inv 0)) "}"))
            (recur (drop 1 room_inv))
          )
        )
      )
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
            (if (> (count (grab_exits newplayer rooms)) 1)
              (hash-map 
                :player newplayer
                :rooms rooms 
              )
              (let [newestplayer (link_player_room (hash-map :player newplayer :rooms rooms) 3)]
                (look args (:player newestplayer) (:rooms newestplayer))
                (hash-map
                  :player (:player newestplayer)
                  :rooms (:rooms newestplayer)
                )
              )
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

(defn pack
  "Allows the player to view their inventory"
  [args player rooms]
  (let [fireaxe (grab_item (:pack player) "Fireaxe")]
    (println (str (:amount fireaxe) " " (:verb fireaxe "Fireaxe")
                  " remaining")
    )
  )
  (println "*Items in pack:")
  (loop [backpack (:pack player)]
    (if (not (empty? backpack))
      (do
        (println (str "=] " (:name (nth backpack 0)) 
                      " {" (:amount (nth backpack 0)) "}"))
        (recur (drop 1 backpack))
      )
    )
  )
  (hash-map
    :player player
    :rooms rooms
  )
)  

(defn see 
  "Allows player to examine item in their pack"
  [args player rooms]
  (if (not (empty? args))
    (let [item (grab_item (:pack player) args)]
      (if (not (empty? item))
        (do
          (println (str "You retrieve " args " from your bag!"))
          (println (:name item))
          (println (:description item))
          (println (str "It has about " (:amount item) " " (:verb item) " remaining."))
          (println (str "It is valued at " (:value item) " gold pieces."))
        )
        (println (str "You search for " args " but can't seem to find it"))
      )
    )
    (do
      (println "You examine your pack. It's made of a fine rubbery mesh.\nSimilar to rhino skin but without all the murdery poachy parts.")
    )
  )
  (hash-map :player player :rooms rooms)
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

(defn points 
  [player]
  (loop [backpack (:pack player) sum 0]
    (if (not (empty? backpack))
      (let [new_sum (+ sum (* (:value (nth backpack 0)) (:amount (nth backpack 0))))]
        (recur (drop 1 backpack) new_sum)
      )
      (list sum)
    )
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
        (println (slurp "Outie.txt"))
        (println (str "Score: " (points player)))
        (spit_rooms "recent_map.clj" rooms)
        (System/exit 0)
      )
      (hash-map
        :player player
        :rooms rooms
      )
    )
  )
)

(defn add_to_pack
  "Adds an item to a pack"
  [backpack item]
  (conj backpack item)
)

(defn remove_from_pack
  "Removes an item from a pack"
  [backpack item]  
  (drop-item backpack item)
)

(defn remove_from_room
  "Removes an item from the room"
  [room item]
  (assoc room :inventory (remove_from_pack (:inventory room) item))
)

(defn add_to_room
  "Adds an item to the room"
  [room item]
  (assoc room :inventory (add_to_pack (:inventory room) item))
)

(defn let_go
  "Drops an item from the pack"
  [args player rooms]
  (if (empty? args)
    (do
      (println "You open up your pack and turn it upside down.\nIt is odd, but only air came out.\nYou must specify what you want to drop in this dimension.")
      (hash-map :player player :rooms rooms)
    )
    (let [current_room (grab_room player rooms)]
      (let [item (grab_item (:pack player) args)]
        (if (not (empty? item))
          (let [new_room (add_to_room current_room item)]
            (do
              (println (str "You remove " args " from your backpack."))
              (hash-map
                :player (assoc player :pack (remove_from_pack (:pack player) item))
                :rooms (conj (drop-item rooms current_room) new_room)
              )
            )
          )
          (do
            (println (str "You search for " args " but cannot find it."))
            (hash-map :player player :rooms rooms)
          )
        )
      )
    )
  )
)

(defn obtain 
  "Grabs an item from a room"
  [args player rooms]
  (if (empty? args)
    (do 
      (println "You grasp at the air desperately shoving it in your pack.\nThis dimension has not been kind to you.")
      (hash-map :player player :rooms rooms)
    )
    (let [current_room (grab_room player rooms)]
      (let [item (grab_item (:inventory current_room) args)]
        (if (not (empty? item))
          (let [new_room (remove_from_room current_room item)]
            (do
              (println (str "You shove " args " into your backpack."))
              (hash-map
                :player  (assoc player :pack (add_to_pack (:pack player) item))
                :rooms (conj (drop-item rooms current_room) new_room)
              )
            )
          )
          (do
            (println (str "You search for " args " but cannot find it."))
            (hash-map :player player :rooms rooms)
          )
        )
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
              :name "see"
              :description "User types \"see itemname\" to view an item in your pack."
              :fn see
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
          (hash-map
              :name "get"
              :description "User types \"get item\" to pick up items, mostly rocks..."
              :fn obtain
          )
          (hash-map
              :name "drop"
              :description "User types \"drop item\" to drop the item into the current room."
              :fn let_go
          )
  )
)

(def plyr 
  (hash-map
    :player (hash-map 
              :location 1
              :pack (list
                      (hash-map
                        :name "Fireaxe"
                        :description "You might think it bizzare but in this universe ALL fireaxes are actually comprised of nothing less than actual fire and raven feathers. It's standard issue directly from the Interdimensional Home Explorers Committee."

                        :verb "potential door murder(s)" 
                        :amount 25
                        :value 1
                        :break "Your Fireaxe has shattered into a million pieces.\nI hope you brought some keys..."
                      )
                    )
            )
    :rooms nil
  )
)

(def unlocking_quips [
    "Carefully you take at the door.\nSwiftly you bring the axe above your head.\nAggressively you begin hacking the door down till it's nothing more than paper shavings.\nWell done?"
    "Across the room, BAM, you see it! That door. The most menacing door to ever be shut.\nHow dare it deny you entry like you're some sort of\nCOMMONER\nNO YOU WILL NOT HAVE!\nYou dig the fireaxe deep into it door flesh cackling widly."
    "You chop the door down like a civil person. Obviously."
    "The last door you'll ever chop. Unless . . .  you do it again I guess."
    ]
)
