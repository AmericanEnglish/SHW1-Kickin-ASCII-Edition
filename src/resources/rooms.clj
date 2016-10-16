(ns resources.rooms)

(def garage
	(hash-map
		:id 1
		:name "Garage"
		:exits (vector 2)
		:description "Just you typical garage."
	)
)

(def foyer
	(hash-map 
		:id 2 
		:name "Foyer" 
		:exits (vector 1 3) 
		:description "A Foyer, also known as a entry room for those who don't know."
	)
)

(def parlour
	(hash-map 
		:id 3 
		:name "Parlour" 
		:exits (vector 2 4 6 7) 
		:description "A Parlour, could sometimes be refered to as a living room."
	)
)

(def kitchen
	(hash-map 
		:id 4 
		:name "Kitchen" 
		:exits (vector 3 5) 
		:description "This is a kitchen with some dirty dishes that need to be washed."
	)
)

(def pantry
	(hash-map 
		:id 5 
		:name "Pantry" 
		:exits (vector 4) 
		:description "A Pantry with some expensive herbs and spices."
	)
)

(def bathroom
	(hash-map 
		:id 6 
		:name "Bathroom" 
		:exits (vector 3 7) 
		:description "A Bathroom, also known as a wash room to some."
	)
)

(def bedroom
	(hash-map 
		:id 7 
		:name "Bedroom" 
		:exits (vector 3 6 8) 
		:description "Looks to be the master bedroom of the house."
	)
)

(def office
	(hash-map 
		:id 8 
		:name "Office" 
		:exits (vector 7) :description "Looks to be an Office."
	)
)

(def rooms
	(list 
		garage foyer parlour kitchen pantry bathroom bedroom office)
)
