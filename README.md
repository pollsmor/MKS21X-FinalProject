# MKS21X-Final

Group 38: Kevin Li, Rachel Leong - Period 5

1/4/19: Rachel
-Added class headers to Block, Floor, and Game

1/5/19: Rachel
-Removed devlog.txt from the branch
-Worked on Block.java
  -Added get/setVisibility and spawnEnemy/Player pseudocode

1/6/19: Rachel
-Created interface Explorable.java
  -Added methods isExplored() and setVisibility()
-Block.java
  -Organized code with comments
  -Added getters and setters
  -Commented out spawn code (Pokemon, Item, and Objective not made yet)
-Tunnel.java
  -Implements Explorable
  -Added fields
  -Added documentation
  -Added constructor Tunnel(Block start, Block end)
  -Added isExplored() and setVisibility() which will be edited later when Player is added
-Room.java
  -Implements Explorable
  -Added fields and documentation
  -Added constructor Room(Block start, Block end)
  -Added isExplored() and setVisibility()
-Wall.java
  -Added fields and documentation
  -Added two constructors: one given Block and the other given xcor and ycor
-Floor.java
  -Added fields and documentation
  -Added constructor given current floors, terminal width, and terminal length
-Game.java
  -Began floor counting code
