(ns resources.workspace)

(defn gen_room 
  "Generates a room given an room template, and a room ID"
  [template room_id]
  (let [sub (rand-nth (:subclass template))]`
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

