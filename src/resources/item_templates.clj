(ns resources.item-templates)
(declare items)

(def items
	(list
		(hash-map
			:name "Fireaxe"
            :description "You might think it bizzare but in this universe ALL fireaxes are actually comprised of nothing less than actual fire and raven feathers. It's standard issue directly from the Interdimensional Home Explorers Committee."
            :verb "potential door murder(s)" 
            :amount 1
            :value 1
            :break "\nYour Fireaxe has shattered into a million pieces.\nI hope you brought some keys...\n"
		)
		(hash-map
			:name "Rock"
			:description "A petoskey stone the size of a baseball."
			:verb "smashes"
			:amount 1
			:value 1
			:break "\nYou broke the Rock.\n"
		)
		(hash-map
			:name "Gold Ingot"
			:description "A solid gold bar."
			:verb "possess"
			:amount 1
			:value 100
			:break "\nThe Gold Ingot shattered and vanished.\n"
		)
		(hash-map
			:name "Iron Ingot"
			:description "An iron ingot."
			:verb "possess"
			:amount 1
			:value 25
			:break "\nThe Iron Ingot shattered and vanished.\n"
		)
		(hash-map
			:name "Steel Ingot"
			:description "A steel ingot."
			:verb ""
			:amount 1
			:value 50
			:break "\nThe Steel Ingot shattered and vanished.\n"
		)
		(hash-map
			:name "Amethyst"
			:description "A violet variety of quartz often used in jewelry."
			:verb "possess"
			:amount 1
			:value 150
			:break "\nWhat have you done? You broke the Amethyst into tiny pieces.\nThe pieces vansih as the hit the ground.\n"
		)
		(hash-map
			:name "Ruby"
			:description "A pink to blood-red colored gemstone."
			:verb "possess"
			:amount 1
			:value 200
			:break "\nWhat have you done? You broke the Ruby into tiny pieces.\nThe pieces vansih as the hit the ground.\n"
		)
		(hash-map
			:name "Sapphire"
			:description "A blue colored gemstone. Also have varieties that occur naturally as yellow, purple, orange, or green colors."
			:verb "possess"
			:amount 1
			:value 400
			:break "\nWhat have you done? You broke the Sapphire into tiny pieces.\nThe pieces vansih as the hit the ground.\n"
		)
		(hash-map
			:name "Diamond"
			:description "A rare, transparent, and extremely hard gemstone of great value."
			:verb "possess"
			:amount 1
			:value 1000
			:break "\nWhat have you done? You broke the Diamond into tiny pieces.\nThe pieces vanish as they hit the ground.\n"
		)
	)
)