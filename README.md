# shw1

FIXME: description

## Installation

Just clone the repo and start playing!

## Usage

Usage pending
```
    $ java -jar shw1-0.1.0-standalone.jar [args]
```

## Options

There are none! Isn't that wonderful?!

## Progress

|     Goal     | Progress |   Date   |
|:------------:|:--------:|:--------:|
| Basic REPL   | Done     |   N / A  |
| Halp CMD     | Done     |   N / A  |
| Enter CMD    | Done     |   N / A  |
| Quit CMD     | Done     |   N / A  |
| Look CMD     | Done     |   N / A  |


## Rules
### Room Generation

|   Room   |   Exits   |  Subclass   | 
|:--------:|:---------:|:-----------:|
| Bathroom |     2     | Master      | 
|  . . .   |     1     | Full        |
|  . . .   |     1     | Half        |
| Bedroom  |     2     | Master      |
|  . . .   |     1     | Guest       |
|  . . .   |     1     | Child       |
| Foyer    |     2     | Front       |
|  . . .   |     2     | Back        |
|  . . .   |     0     | Nill        |
| Garage   |     1     | 3 Car       |
|  . . .   |     1     | 2 Car       |
|  . . .   |     1     | 1 Car       |
| Kitchen  |     4     | Full        |
|  . . .   |     2     | Half        |
|  . . .   |     0     | Nill        |
| Office   |     2     | Large       | 
|  . . .   |     1     | Medium      |
|  . . .   |     1     | Small       |
| Pantry   |     1     | Large       |
|  . . .   |     0     | Nill        |
|  . . .   |     1     | Small       |
| Parlor   |     4     | Dining      |
|  . . .   |     4     | Family      |
|  . . .   |     4     | Living      |


### House Generation

### Process of Generation
Coming soon

#### Connection Rules
| Room     | Subclass |              Rule                  |
|:--------:|:--------:|:-----------------------------------|
| Bathroom | Master   | Always connects to master bedroom  |
| Bedroom  | Master   | Always connects to master bathroom |
| Foyer    | Front    | Always connects to garage          |
| Garage   | All      | Always connects to front foyer     |
| Kitchen  | Full     | Always connects to pantry          |
| Pantry   | All      | Only   connects to  full kitchen   |

### Misc Rules
* Rooms who "always connect" must have appropriate room generated next
* Start at front foyer
* No more than 50 total rooms
* No less than 8 total rooms
* Enter every room to win
* No two rooms of the same superclass can connect to each other

## License

Copyright © 2016 

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
