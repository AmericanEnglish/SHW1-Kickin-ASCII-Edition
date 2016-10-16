(ns resources.user-commands)
(declare halp Into look)

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
	)
)


(defn halp 
    "Displays help information about a specific command or just displays all commands"
    [command]
	(if (= command "")
		(print "User commands: into, look, halp. Type \"halp command\" for more information on the command.")
		(if (= command (get commands :name)) ; This line needs to be changed ~ Carlos
			(print (get commands :name) (get commands :description))
			(print "No such command found!")
		)
	)
    ;Not sure how to print the info on specified command but this is a start. 
)


(defn Into
    "Moves the player into the room"
    []
    (println "Empty \"into\" command")
)


(defn look 
    "Allows player to look around a room"
    []
    (println "An empty \"look\" command" )
)
;We can place other user command functions in this file to stay organized.

