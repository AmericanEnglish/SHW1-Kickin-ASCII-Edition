(ns resources.item-templates)
(declare items)

(def items
	(list
		(hash-map
			:name "Fireaxe"
            :description "You might think it bizzare but in this universe ALL fireaxes are actually comprised of nothing less than actual fire and raven feathers. It's standard issue directly from the Interdimensional Home Explorers Committee."
            :verb "potential door murder(s)" 
            :amount 1
		)
		(hash-map
			:name "Rock"
			:description "A petoskey stone the size of a baseball."
			:verb "smashes"
			:amount 1
		)
	)
)