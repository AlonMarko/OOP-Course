alonmarko208




=============================
=      File description     =
=============================
SpaceShip.java - the main spaceship class that all ships inherit from
SpaceShipFactory.java - the class that creates all the spaceships for the game
according to the inputs
PlayerShip.java - creates and runs the human contorolled ship - extends SpaceShip
RunnerShip.java -  the class that creates and manages the runner ship - extends SpaceShip
BasherShip.java - the class that creates and manages the basher ship - extends SpaceShip
AggresiveShip.java - the class that createsa and manages the basher ship - extends SpaceShip
DrunkShip.java - the class that creates and manages the drunk ship - extends SpaceShip
SpecialShip.java - the class that creates and manages the special ship - extends SpaceShip



=============================
=          Design           =
=============================
i implemented the SpaceShip class to its fullest, meaning it has all the common things a spaceship shares doesnt matter the
 type of ship. 
the actions, the regeneration , the creation , the reset, etc. leaving all special types of ship very little to implement - 
in my case i left only 2
methods to implement - the movement method which is a private method that doAction uses - doAction is overriden in each
 class but i use super to use the main
method aswell since it is the regenerative part - end or round section.
 so basicaly - SpaceShip does most of the stuff and the extensions only implement 2 methods differently in each class 
( the player ship 3 - has to send different image)
because of this design it is very easy to add new features. i wanted to add the special ship as a musical ship - which 
plays music and it should have been easy but met with difficulties
with the imports reqiuerd in order to do so easily compared to the intelliJ imports.
but the concept is clear - the new classes extends only their special features - the rest is controlled by the main class.
so this results in a modular code.



=============================
=  Implementation details   =
=============================
first, i created all the constant variables in the main class that are shared, and than some non contants to indicate 
shield etc. 
i chose to return the enemy ship image since its the most common and only humanplayer implements it differently.
the details are pretty much written above in the design section.

furtehr more in the SpaceShipFactory i did limit the number of Human ships to 1 as indicated on the exercise 
insructions. so more than one human ship input
results in null return and threats it as invalid input.

i also chose all the parameters to be private, with only specific ones being protected so they can be used by
 the subclasses. 
and those are teh turning variables and the shild on boolean flag.


=============================
=    Answers to questions   =
=============================

1) special ship
as stated above - i wanted at first to do the special ship as normal runner ship but a one that plays music 
so i wanted to 
extend the runnership and just put music into the game whenever a specialship is present.
 i had difficulties with the imports intelliJ had , i needed extra ones
and so i gave up since i cannot submit them - since on your pc's they wont be.

so i mixed a aggresive ship and runnership and gave it extra regenerative abbilities - 
it ifres a lot more since its cooldown is much faster and it regenerates more energy.
but it does not accelerate at all so it speed is relatively slow.

2)drunk ship 
the drunk ship uses two random "borders" - for the movement - 2.
which means it has 2 50/50 chance to move left or right each round so the result is a
 relativly straight line but it jiggles around like a drunk person would.

and for the actions - 4
which means each round it iwll try to do an action - accelerate,shoot,teleport,shieldon.
which means it has a chance of 1/4 for each action - assuming it has enough energy.
