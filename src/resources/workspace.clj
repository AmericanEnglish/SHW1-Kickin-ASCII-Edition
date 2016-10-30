(ns resources.workspace)


(def room_templates 
  (list
    (hash-map 
      :basename "example"
      :subclass 
        (list 
          (hash-map 
            :type "size_example"
            :total_exits 10
            :description "example description here"
          )
          (hash-map
            :type "other_example"
            :total_exits 25
          )
        )
    )
    (hash-map 
      :basename "EXAMPLE"
      :subclass 
        (list 
          (hash-map 
            :type "SIZE_EXAMPLE"
            :total_exits 5
            :description "EXAMPLE DESCRPTION HERE"
          )
        )
    )
  )
)
   

(def room_spec
  (list
      (hash-map 
        :basename "Bathroom"
        :subclass 
          (list 
            (hash-map 
              :type "Master"
              :total_exits 2
              :description ""
            )
            (hash-map
              :type "Full"
              :total_exits 1
              :description ""
            )
            (hash-map
              :type "Half"
              :total_exits 1
              :description ""
            )
          )
      )
      (hash-map 
        :basename "Bedroom"
        :subclass 
          (list 
            (hash-map 
              :type "Master"
              :total_exits 2
              :description "test_descrip_large"
            )
            (hash-map
              :type "Guest"
              :total_exits 1
              :description "test_descrip_med"
            )
            (hash-map
              :type "Child"
              :total_exits 1
              :description "test_descrip_small"
            )
          )
      )
      (hash-map 
        :basename "foyer"
        :subclass 
          (list 
            (hash-map 
              :type "Front"
              :total_exits 2
              :description "test_descrip_large"
            )
            (hash-map
              :type "Back"
              :total_exits 2
              :description "test_descrip_med"
            )
          )
      )
      (hash-map 
        :basename "Garage"
        :subclass 
          (list 
            (hash-map 
              :type "3-Car"
              :total_exits 1
              :description "test_descrip_large"
            )
            (hash-map
              :type "2-Car"
              :total_exits 
              :description "test_descrip_med"
            )
            (hash-map
              :type "1-Car"
              :total_exits 
              :description "test_descrip_small"
            )
          )
      )
      (hash-map 
        :basename "Kitchen"
        :subclass 
          (list 
            (hash-map 
              :type "Full"
              :total_exits 4
              :description "test_descrip_large"
            )
            (hash-map
              :type "Half"
              :total_exits 2
              :description "test_descrip_med"
            )

          )
      )
      (hash-map 
        :basename "Office"
        :subclass 
          (list 
            (hash-map 
              :type "Large"
              :total_exits 2
              :description "test_descrip_large"
            )
            (hash-map
              :type "Medeium"
              :total_exits 1
              :description "test_descrip_med"
            )
            (hash-map
              :type "Small"
              :total_exits 1
              :description "test_descrip_small"
            )
          )
      )
      (hash-map 
        :basename "Pantry"
        :subclass 
          (list 
            (hash-map
              :type "Large"
              :total_exits 1
              :description "test_descrip_large"
            )
            (hash-map
              :type "small"
              :total_exits 1
              :description "test_descrip_med"
            )
          )
      )
      (hash-map 
        :basename "Parlour"
        :subclass 
          (list 
            (hash-map 
              :type "Dining"
              :total_exits 4
              :description "test_descrip_large"
            )
            (hash-map
              :type "Family"
              :total_exits 4
              :description "test_descrip_med"
            )
            (hash-map
              :type "Living"
              :total_exits 4
              :description "test_descrip_small"
            )
          )
      )
      
  )
)