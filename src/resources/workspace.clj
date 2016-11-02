(ns resources.workspace)

; First pick exit
(defn pick_exit 
  "Returns an exit id for a room if given all exits or all exits and a required exit"
  ([exits all_rooms]
  ; Pick one exit
  ; Check to make sure the exit isn't unique in set(exits) != list(exits)
  ; If set(exits) == list(exits), then proceed anyways
  (loop []
    (if (exits == (distinct exits))
      (rand-nth exits)
      (let [exit (rand-nth exits)]
        (let [accept (acceptable exits)]
          (if (> -1 (.indexOf exit accept))
            (list exit)
            (recur exits)
          )
        )
      )
    )
  )
  )
  ([doors door1 rooms]
  ; Pick one exit but "required" will be the other exit
  ; Room cannot have itself as the other exit
  ; Cannot have multiple exits to one room
  ; Cannot pick an exit if only one copy remains unless all of 1 remain. -> if set == list of exit good to go.
  (let [accept (acceptable doors) unaccept (unaccptable)]
    (if (or (= doors (distinct doors) (= doors (conj unaccept door1))))
      (loop [choices unaccept]
        (if (empty? choices)
          (list door1)
          (let [door2 (rand-nth accept)]
            (if (not (= door1 door2))
              (if (not (duplicate_exit? door1 door2 rooms))
                (if (not (and (one_distinct_exit? door1) (one_distinct_exit? door2)))
                  (list door1 door2)
                  (recur (drop 1 choices))
                )
                (recur (drop 1 choices))
              )
              (recur (drop 1 choices))
            )
          )
        ); this is the (1 2 3 4) loop
      )
      (loop [choices accept]; This is the (1 1 2 2 3 3 4 4) loop
        (if (empty? choices)
          (list door1)
          (let [door2 (rand-nth accept)]
            (if (not (= door1 door2))
              (if (not (duplicate_exit? door1 door2 rooms))
                (list door1 door2)
                (recur (drop 1 choices))
              )
              (recur (drop 1 choices))
            )
          )
        )
      )
    )
  )
)





























  )
)

