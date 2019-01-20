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

**Thursday 1/10/19**

Kevin:
- As per Mr. K's instructions, I have moved the main from Game.java to Driver.java to avoid confusion while coding.
- Grouped related code together in main   
- Simplified the interface by removing the screen showing key presses, and the only timer on screen being a seconds timer.
- get functions in various classes (especially Game) to interact with main (thanks for suggesting getFloor Mr. K).
- Random spawning mechanism - spawns the character into a room, not blank space or anywhere inappropriate.
- Spawning can be set as well, if a seed is provided via a command-line argument.
- Changed the xy coordinate system to a col-row coordinate system to conform overall with the rest of the program.

Rachel:
- Asked Mr. K about how to approach making Tunnels
- Plan goes as follows (for now)
  - Within createRooms in Floor.java right after a successful Room has been created, immediately connect a Room to another Room that already exists, if it is not the first Room to be constructed
  - Tunnels may pass through Rooms, allowing for possibly, multiple Tunnels to pass through a single Room
    - Ideas for more stages???
    - Mode where each room only has one Tunnel to connect to a Room?
    - Mode where visibility of Rooms and Tunnels depends on whether or not a Player is in that Room at the moment
- In terms of coding, I haven't done anything major yet, but jotted down ideas on how to make Tunnels

**Friday 1/11/19**

Rachel:
  - Block.java
    - Added default data for a Wall Block and default data for a Tunnel Block
  - Tunnel.java
    - Worked on constructors
    - Added toString
  - FloorDriver.java
    - Added test cases for constructors and toString()
  - Everything compiles!!!

**Saturday 1/12/19**

Rachel:
  - Tunnel.java
    - Added comments to constructors and fixed blockHeres
    - Fixed constructor
  - Room.java
    - Considered what to do with connectRooms in comments, fixed some visualization comments
    - Added param seed to connectRooms
    - Attempted to define borderBlocks
    - Added random to generate random rooms to connect and algorithm to decide relative locations
    - Added removeDupes() in order to make the probability of each borderBlock equal
  - Floor.java
    - Added getBlocksHere()
    - Moved connectRooms to Room.java
  - FloorDriver.java
    - More test cases for constructors

 **Sunday 1/13/19**

Kevin:
- Borders to keep players inside rooms didn't work but thanks to Rachel setting every block in the array that is empty to a Wall type, it was done in 2 minutes flat. Now players can't get out of the room.
- Different toString used to maintain the same coordinate system between what is being printed on screen and the blocksHere[][] array in Floor.java. Borders are drawn to the right and the bottom of the game window for visual flair - it does not affect the coordinate system.
- Fixed seeds not working properly. Previously if providing a second parameter the seed would always be set to 0, had something to do with how the constructors used this. Removed all uses of "this" from Game.java, Player.java, and Pokemon.java.
- Added necessary getters for Player and Pokemon to help with:
- UI! Printing extra stuff on screen for visual flair. HP, level, XP, floor number, attack stat, defense stat, money, etc.
- Colored the HP stat red and (how did I forget to mention this in the previous comment) colored the Pokemon's name green, with the help of ANSI codes.
- Added back the Delete key causing "death" to show off for the demo.

Rachel:
  - Tested Tunnel(Block, Block, Floor) in FloorDriver.java with new test cases
  - Continued working on constructing Tunnels


**Monday 1/14/19**

Kevin:
After seeing the demo code, I've decided to:
- Utilize background colors
- Move stat counters from the right of the screen to the bottom onto 2 lines, which in turn:
- Allows me to extend the map all the way across the screen. Width is still limited to 3/4 of the screen to accomodate for the UI.
- I felt really dumb after seeing everyone's demos showing their project's name. Well, now we have one too. And it blinks!
- Had to make the minimum dimensions of the Terminal larger for  everything to fit together, but the limit should still be smaller than the typical Terminal size.

Rachel:
  - Working on fixing the dimensions of constructors to conceptually make more sense
    - x: represents columns
    - y: represents rows and is selected first when indexing
    - Ex: blocksHere[y][x]

 **Tuesday 1/15/19**

 Rachel:
  - Dimensions! Fixed such that blocksHere reflects the 4th quadrant in a standard cartesian plane: x refers to width, y refers to length
  - Fixed a Tunnel constructor error
  - Block.java
    - Added printPoint() for debugging
  - Floor.java
    - Fixed addTunnel
  - Problems... :(
    - Discovered issue with getting correct start/endBlocks


 **Wednesday 1/16/19**

Rachel:
  - Room.java
    - Fixed createRoom and createRooms: Tunnels now generate!!!
    - Fixed constructor and toSTring(ArrayList<Block>)
    - Commented out some print statements
  - Tunnel.java
    - Commented out some print statements
  - Floor.java
    - Fixed toStringClean()

Kevin:
  - In the UIelements branch, I managed to get background colors working. Instead of having the array show "R"s to show that it is a traversable area, I just color everything that isn't a room char white so it's easy to distinguish walls from a room.
  - Attempted merging with master but the map is completely broken. I realize that my coordinate system is the opposite of what Rachel uses. We haven't had any issues before this because we mostly just stuck to our own Java classes, but I'm tryng to look at Floor.java to figure out what's breaking the map now.

**Thursday 1/17/19**

Kevin:
  - Fixed most if not all of the issues caused by the merge. Now going to working on enemy spawns.

Rachel:
  - Got Tunnels to print in FloorDriver.java such that if the original Block was a Room Block, it would not alter the Block
  - Spaced out the Rooms more so it isn't as crammed
  - Changed the min and max dimensions of Rooms

***--------------------Stage 2--------------------***

**Friday 1/18/19**

Kevin: 
  - Copy pasting the *working* random spawn generation code from Driver and attempting to transplant it into a function still doesn't work
  - Switched putString's coordinate system to match the rest of Driver: row, col

Rachel: 
  - Added new constructor specifically for Tunnel Blocks
  - Attempting to style Tunnels: horizontal means '=' and vertical '|' while change in direction or intersection of Tunnels gets '#'

**Saturday 1/19/19**

Rachel:
  - Added spawnObjective
  - Successfully finished differentiating between horizontal, vertical, and intersecting Tunnel Blocks!
  - Starting to implement the canMoves for adjacent Tunnels issues
  - After Kevin added the check for minimum size of terminal, Driver.java no longer runs, saying my terminal is always too small even though it clearly isnt... we'll figure it out soon :P
  
**Sunday 1/20/19**
