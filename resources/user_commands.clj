(def commands
	(list (hash-map :name "into" :description "User types \"into room id\" to move into the room only if the new room is attached to current room.")
			(hash-map :name "look" :description "User types \"look\" with no arguments to display current room information.")
				(hash-map :name "halp" :description "User types \"halp\" with no argument to display all user commands, or with an argument to show more information on command.")
	)
)

(defn halp [command]
	(if (= command "")
		(print "User commands: into, look, halp. Type \"halp command\" for more information on the command.")
		(if (= command (get commands :name)) ; This line needs to be changed ~ Carlos
			(print (get commands :name) (get commands :description))
			(print "No such command found!")
		)
	)
;Not sure how to print the info on specified command but this is a start. 
)

;We can place other user command functions in this file to stay organized.
