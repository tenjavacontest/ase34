This is the repositiory for ase34's contribution to the [ten java contest](http://tenjava.com) using the theme *Entities* with CraftBukkit build `1.7.2-R0.1-SNAPSHOT`.

Frankly, I ran out of concepts and this was the only idea that could be coded in ~3h. Let's see how far I can get with this...

--

AquariumDecorator
=================

This plugin allows players to place their fishes inside an aquarium. There they will be floating around and can be seen from outside.

Comands
-------

- **/pickup** - Sets/Unsets yourself in the pickup-mode so you can pick up floating fishes. - `aquariumdecorator.pickup`
- **/place [offset]** - Places the fish in your hand at your feets. You can supply the number `offset` if you want to playe the fish higher. - `aquariumdecorator.place`  

Tutorial
--------

1. Fish yourself some nice-looking fishes! (Or cheat them :P)
2. Build your aquarium and fill it with water.
3. Get into the aquarium, select the fish and execute `/place`. The fish will be floating at your feets.

Building
--------

This project should be automatically built with Maven. Just clone this repo, make sure you are on the `master` branch, and run `mvn clean package`.