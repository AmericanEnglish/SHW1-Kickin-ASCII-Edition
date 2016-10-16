(ns resources.user-commands)
(declare halp Into look quit)

(defn search_command_name
  [command commands]
  (let [res (filter #(= command (:name %)) commands)]
    (if (not (empty? res))
      res
    )
  )
)

(defn halp 
    "Displays help information about a specific command or just displays all commands"
    [command]
  (if (= command "")
    (print "User commands: into, look, halp. Type \"halp command\" for more information on the command.")
    (if-let [res (search_command_name command commands)]
      (println (:description res))
      (println "No such command found!") 
    )
  )
)

(defn Into
    "Moves the player into the room"
    [args]
    (println "Empty \"into\" command")
)

(defn look 
    "Allows player to look around a room"
    [args]
    (println "An empty \"look\" command" )
)

(defn quit
  "Allows the user to end the game."
  [args]
  ;Carlos will put the ask for comfirmation code
  (println "Goodbye!")
  (System/exit 0)
)
;We can place other user command functions in this file to stay organized.

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
