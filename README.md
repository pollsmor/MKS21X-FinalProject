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


***--------------------Stage 2--------------------***

**Monday 1/7/19**

Kevin:
- Created necessary functions and instance variables for Enemy
- Created necessary functions and instance variables for Player
- Item class
- Changed up the looks of the Game, different symbol for the character and colors

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
    - tooClose: Began code to check if rooms to be created are too close or overlap


**Tuesday 1/8/19**

Kevin: 
- Worked on stuff in the game class such as adding constructors that utilize command line arguments, catching the proper exceptions, and moving functions around to make them easier to use (e.g. faintPlayer has been moved to Game)
- Unfortunately all of the computers in the lab just shut off at the end bell before I could commit and push the latest commits and SSHing in is giving me some kind of TLS error. Whoever reads this will just have to take my word for it :)

Rachel:
  - FloorDriver.java
    - Added test cases for createRooms and createRoom
    - Now takes 0 args, 1 arg (seed), 2 args (width, length) or 3 args (Seed, width, length)
    - Uses random to generate a random floor
  - Floor.java
    - Success! createRooms now completely works
    - Fixed toString
  - Room.java
    - Success! tooClose functions properly!
    - Added comments to better show how tooClose() works
    
**Wednesday 1/9/19**

Rachel:
  - Floor.java
    - Added fields to keep track of tunnels and successful rooms
    - Added method headers for createTunnel and createTunnels (not sure about params yet tho)
  - Tunnel.java
    - Started nextToTunnel and nextToRoom functions... but have yet to be tested T-T
    - Added documentation
    - Added direction field to keep track of orientation
    - eyyyyy it compiles but maybe doesn't work? :/
  - Room.java
    - Added getters for startXcor, startYcor, endXcor, endYcor
    - Added documentation for toString()
    - Added field Blocks[] borderBlocks in preparation for creating Tunnels
      - Will chose a random borderBlock from one Room and try to make a series of Tunnels to connect with another Room's random borderBlock... but how is the question...
      
Kevin: 
- Instantiating the floor required moving the terminal creation commands to the front of main as the floor constructor requires the terminal width/length.
- Turns out game wasn't even being insantiated properly. Can't do it in a try catch, so I moved the code outside of them. 
- Worked out the logic allowing the aforementioned to work, such as tossing out the game if the args.length is less than 0, only trying the instantiation of seed if the length of args is >= 2 so it doesn't interfere with the case where the user only provides a name, and instantiating game after the try catch block since I am now 100% sure that the arguments work. 
- Tomorrow I'll try and get the floor on screen, since I've gotten the instantiation of it down. 
