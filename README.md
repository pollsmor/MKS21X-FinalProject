# MKS21X-Final

**Group 38: Kevin Li, Rachel Leong**

**Period 5**

**Development Log**

***--------------------Stage 1--------------------***

**Thursday 1/3/19**

Rachel:
  - Added constructors and some fields in Pokemon class
  - Added files for the map layout

Kevin:
  - Incorporated lanterna features into program
    - Added fields to various classes like Block, Pokemon, and Game

**Friday 1/4/19**

Kevin:
  - Added a mechanism to end games (as of now pressing backspace will simulate a Pokemon dying, prompting you to respawn or end the game)

Rachel:
  - Added class headers to Block, Floor, and Game

**Saturday 1/5/19**

Rachel:
  - Removed devlog.txt from the branch
  - Worked on Block.java
  - Added get/setVisibility and spawnEnemy/Player pseudocode

**Sunday 1/6/19**

Rachel:
  - Created interface Explorable.java
    - Added methods isExplored() and setVisibility()
  - All files now compile
  - Block.java
    - Organized code with comments
    - Added getters and setters
    - Commented out spawn code (Pokemon, Item, and Objective not made yet)
  - Tunnel.java
    - Implements Explorable
    - Added fields and documentation
    - Added constructor Tunnel(Block start, Block end)
    - Added isExplored() and setVisibility() which will be edited later when Player is added
  - Room.java
    - Implements Explorable
    - Added fields and documentation
    - Added constructor Room(Block start, Block end)
    - Added isExplored() and setVisibility()
  - Wall.java
    - Added fields and documentation
    - Added two constructors: one given Block and the other given xcor and ycor
  - Floor.java
    - Added fields and documentation
    - Added constructor given current floors, terminal width, and terminal length
  - Game.java
    - Began floor counting code

**Monday 1/7/19**
Rachel:
  - FloorDriver.java
    - Created file to test constructors for Room, Floor, and Block. Haven't started with Tunnels
  Block.java
    - Removed excess Block constructor
    - Added toString and getData
  Floor.java
    - Added width and length getters
    - Added createRoom and began createRooms
    - Started toString (for testing purposes)
  Room.java
    - Did some testing--> fixed constructor
    - Added toString (for testing purposes)

***--------------------Stage 2--------------------***
>>>>>>> makeMap
