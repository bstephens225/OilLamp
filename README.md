# OilLamp
CS120Final
Gaslamp
You wake up in a Victorian era mansion. You don’t remember how you got there. It is dark except for a gas lamp which will not run out or turn off. 
You can walk north south east west up down.
Every other action you take has the chance of causing a glitch.

Possible glitches:
Change pronouns (once)
Change aspects of the rooms
Change inventory (add, remove, replace)
Gas lamp dims
Change what the person typed

I’m adding graphics

White text that gets gradually closer to black when we have a glitch

Game Over if you put the lamp down
Game Over if you let the lamp die out
Other endings: you set something on fire using the lamp. 

How to “win”: You find the pen and paper and you record your observations and inventory
You get a new light source

Classes:
Character
Has a current location
Has an inventory
Takes actions
Walk
grab/drop
Use
Examine
Write
Burn
glitches

Location
Stores coordinates (x,y,z)
Stores place name
Stores place description
Has inventory??

Floor Plan
Aggregates Locations

Inventory
Has items
Has size/weight

Item
Has name
Has alternative versions (broken, lit, open/closed, etc)
Throws exception if try to do impossible thing - caught by character
Has properties (flammable, openable, breakable)

Lamp
Has brightness

Vibes:
Horror
You don’t know what you think you know
Gaslighting
Memory

What must I accomplish:
Eight rooms you can walk between
Eight objects you can interact with
Basic methods work
Graphic of text
Inventory works
Game over if light is out
Glitches change inventory and dims light
What that means:
Make item class
Make inventory class
Make location class
Make floor plan class
Write descriptions for 8 rooms 8 objects
Alter character to use the classes above
Text scanner runs methods (walk/grab/etc)
Graphic text of command and response
Glitches


Rooms:

Living room
Dining Room
Entryway
Kitchen

Master Bedroom
Children’s Bedroom
Bathroom
