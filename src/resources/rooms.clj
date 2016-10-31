(ns resources.rooms)
(declare rooms)

;(def garage
;	(hash-map
;		:id 1
;		:name "Garage"
;		:exits (vector 2)
;		:description "You are in the Garage, parked inside is a \n candy-apple red 1972 Corvette Sting-ray."
;	)
;)
;
;(def foyer
;	(hash-map 
;		:id 2 
;		:name "Foyer" 
;		:exits (vector 1 3) 
;		:description "A Foyer, also known as a entry room for those who are not posh enough.\n It is a decent size allowing room for a bench, pile of shoes, and walking room."
;	)
;)
;
;(def parlour
;	(hash-map 
;		:id 3 
;		:name "Parlour" 
;		:exits (vector 2 4 6 7) 
;		:description "A Parlour, also refered to as a living room.\n It is full of extravagant art."
;	)
;)
;
;(def kitchen
;	(hash-map 
;		:id 4 
;		:name "Kitchen" 
;		:exits (vector 3 5) 
;		:description "This is a kitchen with some dirty dishes that need to be washed.\n It smells of fresh baked goods!\n... but it is probably a spray... "
;	)
;)
;
;(def pantry
;	(hash-map 
;		:id 5 
;		:name "Pantry" 
;		:exits (vector 4) 
;		:description "A Pantry with some expensive herbs and spices.\n This pantry has enough room to keep all of your cooking supplies and a years worth of canned goods.\n Perfect for the Dooms-Day prepper in all of us."
;	)
;)
;
;(def bathroom
;	(hash-map 
;		:id 6 
;		:name "Bathroom" 
;		:exits (vector 3 7) 
;		:description "A Bathroom that is ready for anything."
;	)
;)
;
;(def bedroom
;	(hash-map 
;		:id 7 
;		:name "Bedroom" 
;		:exits (vector 3 6 8) 
;		:description "Looks to be the master bedroom of the house.\n It fits a king size bed and full bedroom set.\n The decor is very elegant with a european touch.\n One lone pair of handcuffs remains on the dresser...\n the preveious owner must have been a police officer."
;	)
;)
;
;(def office
;	(hash-map 
;		:id 8 
;		:name "Office" 
;		:exits (vector 7) 
;		:description "A small office with a veiw reminiscent\n of the Windows XP wallpaper."
;	)
;)
;
;(def rooms
;	(list 
;		garage foyer parlour kitchen pantry bathroom bedroom office)
;)


(defn gen_room 
  "Generates a room given an room template, and a room ID"
  [template room_id]
  (let [sub (rand-nth (:subclass template))]
    (list
    	(hash-map :id room_id :name (str (:type sub) (:basename template)) :description (:description sub) :exits (vector))
    	(repeat (:total_exits sub) room_id)
    )
  )
)

(defn gen_new 
  "Generates a room by passing a randomly selected template to gen_room"
  [all_templates room_id]
  (gen_room 
      (rand-nth all_templates) 
      room_id
  )
)
; First pick exit
(defn pick_exit 
  "Returns an exit id for a room if given all exits or all exits and a required exit"
  [exits]
  ; Pick one exit
  [exits required]
  ; Pick one exit but "required" will be the other exit
  ; Room cannot have itself as the other exit
  ; Cannot have multiple exits to one room
  ; Cannot pick an exit if only one copy remains unless all of 1 remain. -> if set == list of exit good to go.
)


(defn map_linker
	"Gives room with linked exits"
	[rooms all_exits]
    (loop [bucket all_exits]
        (if (empty? bucket)
            rooms
            (recur nil) ; Function here
        )
    )
    
)

(defn gen_map
    "Generates a map given no arguments. Yet."
    (let [maximum 50]
		(loop [rooms (list (gen_new templates 1)) cur 1]
			(if (= cur maximum)
				rooms
				(recur
					(conj (gen_new (+ cur 1)))
                    (+ cur 1)
				)
            )
        )
	)
)

