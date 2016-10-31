(ns resources.room-templates)

(def room_spec
  (list
      (hash-map 
        :basename "Bathroom"
        :subclass 
          (list 
            (hash-map 
              :type "Master"
              :total_exits 2
              :description "A Master Bathroom that is ready for anything."
            )
            (hash-map
              :type "Full"
              :total_exits 1
              :description "A Full Bathroom that is ready for anything."
            )
            (hash-map
              :type "Half"
              :total_exits 1
              :description "A Half Bathroom that is ready for anything."
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
              :description "Looks to be the Master Bedroom of the house.\n It fits a king size bed and full bedroom set.\n The decor is very elegant with a european touch.\n One lone pair of handcuffs remains on the dresser...\n the preveious owner must have been a police officer."
            )
            (hash-map
              :type "Guest"
              :total_exits 1
              :description "Looks to be the Guest Bedroom of the house.\n It fits a full size bed and full bedroom set.\n The decor is similar to the Master Bedroom."
            )
            (hash-map
              :type "Child"
              :total_exits 1
              :description "Looks to be the Child Bedroom of the house.\n It fits a twin size bed and play area.\n The artwork on the walls depicts dinosaurs."
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
              :description "A Front Foyer, also known as a entry room for those who are not posh enough.\n It is a decent size allowing room for a bench, pile of shoes, and walking room."
            )
            (hash-map
              :type "Back"
              :total_exits 2
              :description "A Back Foyer, also known as a entry room for those who are not posh enough.\n It is a decent size allowing room for a bench, pile of muddy shoes, and walking room."
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
              :description "You are in the 3-Car Garage, parked inside is a \n candy-apple red 1972 Corvette Sting-ray, white & gold 1969 Hurst Olds, and a\n black 1967 Chevrolet Impala. Something feels supernatural about the Impala..."
            )
            (hash-map
              :type "2-Car"
              :total_exits 
              :description "You are in the 2-Car Garage, parked inside is a\n candy-apple red 1972 Corvette Sting-ray and a white & gold 1969 Hurst Olds."
            )
            (hash-map
              :type "1-Car"
              :total_exits 
              :description "You are in the 1-Car Garage, parked inside is a\n candy-apple red 1972 Corvette Sting-ray."
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
              :description "This is a Full Kitchen with some dirty dishes that need to be washed.\n It smells of fresh baked goods!\n... but it is probably a spray... "
            )
            (hash-map
              :type "Half"
              :total_exits 2
              :description "This is a Half Kitchen with some dirty dishes that need to be washed.\n It seems as if the dishes have been there a while..."
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
              :description "A Large Office with a veiw reminiscent\n of the Windows XP wallpaper."
            )
            (hash-map
              :type "Medium"
              :total_exits 1
              :description "A Medium Office with a veiw reminiscent\n of the Windows XP wallpaper."
            )
            (hash-map
              :type "Small"
              :total_exits 1
              :description "A Small Office with a veiw reminiscent\n of the Windows XP wallpaper."
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
              :description "A Large Pantry with some expensive herbs and spices.\n This pantry has enough room to keep all of your cooking supplies and a years worth of canned goods.\n Perfect for the Dooms-Day prepper in all of us."
            )
            (hash-map
              :type "small"
              :total_exits 1
              :description "A Small Pantry with some expensive herbs and spices."
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
              :description "This Parlour seems to be used as a Dining room.\n It contains a large table, more than likely used for family gatherings.\n It is probably only used a few times a year."
            )
            (hash-map
              :type "Family"
              :total_exits 4
              :description "This Parlour seems to be used as a Family room.\n It has plenty of seating, enough for a large family."
            )
            (hash-map
              :type "Living"
              :total_exits 4
              :description "This Parlour seems to be used as a Living room.\n It is full of extravagant art."
            )
          )
      )
      
  )
)