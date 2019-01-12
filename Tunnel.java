public class Tunnel implements Explorable{
  private boolean isExplored;
  private Block[][] blocksHere;
  //private Tunnels[] tunnelsHere; //A completed Tunnel will be made up of at least one smaller Tunnel, since a segment can only go in one direction
  private Block startBlock; //Note: coors will be less than or equal to endBlock
  private Block endBlock; //Note: coors will be larger than or equal to startBlock
  private int length; //How many blocks long is the Tunnel
  private int direction; //Orientation of Tunnel: 0 is left=right, 1 is up-down

  //public Tunnel(int startXcor, int startYcor, int length, boolean isHorizontal, boolean isStart)
  /**Creates a Tunnel given the coordinates of the Block representing the beginning of the Tunnel, the length of the Tunnel, and a boolean whether or not the Tunnel is horizontal or vertical
    *Precondition: start acoordinates are the coordinates of the topleftmost Block of the Tunnel to be made
    *@param startXcor is the int representing the start x-coordinate of the Tunnel to be made
    *@param endXcor is the int representing the start y-coordinate of the Tunnel to be made
    *@param newLength is the int representing the length of the Tunnel to be made
    *@param isHorizontal is a boolean representing whether or not the Tunnel to be made is left-right or top-bottom
    *@param isStart is a boolean representing whether or not the coordinates given represent a startBlock or endBlock
  */
  public Tunnel(int startXcor, int startYcor, int newLength, boolean isHorizontal, boolean isStart){
    length = newLength;
    isExplored = false;
    //Setting start and end blocks
    Block start, end;
    if (isStart){ //Coordinates given are of the startBlock
      start = new Block(startXcor, startYcor, "Tunnel");
      if (isHorizontal){ //same ycor
        end = new Block(startXcor + length, startYcor, "Tunnel");
        direction = 0; //Setting orientation to left-right
      }
      else{ //same xcor
        end = new Block(startXcor, startYcor + length, "Tunnel");
        direction = 1; //Setting orientation to top-bottom
      }
    }
    else{ //Coordinates given are of the endBlock
      end = new Block(startXcor,startYcor, "Tunnel");
      if (isHorizontal){ //same ycor
        start = new Block(startXcor - length, startYcor, "Tunnel");
        direction = 0; //Setting orientation to left-right
      }
      else{ //same xcor
        start = new Block(startXcor, startYcor - length, "Tunnel");
        direction = 1; //Setting orientation to top-bottom
      }
    }

    isExplored = false;
    //Setting start and end blocks
    startBlock = start;
    endBlock = end;
    //If left-right, xcor changes
    //blocksHere is a horizontal array
    if (direction == 0){
      blocksHere = new Block[1][length];
      for (int i = 0; i < length; i++){
        blocksHere[0][i] = new Block(startXcor+i, startYcor, "Tunnel");
      }
    }
    //If top-bottom, ycor changes
    //blocksHere is a vertical array
    if (direction == 1){
      blocksHere = new Block[length][1];
      for (int i = 0; i < length; i++){
        blocksHere[i][0] = new Block(startXcor, startYcor+i, "Tunnel");
      }
    }
  }

  //public Tunnel(Block start, Block end)
  /**Creates a Tunnel given two blocks (start, end) beginning with the start block and ending at the end block
    *Precondition: start and end block have either the same xcor or the same ycor
    *@param start is the Block representing the start of the Tunnel to be made
    *@param end is the Block representing the end of the Tunnel to be made
  */
  public Tunnel(Block start, Block end){
    isExplored = false;
    //Setting start coors
    int xcor = start.getX();
    int ycor = start.getY();
    //Setting start and end blocks
    startBlock = start;
    endBlock = end;
    //Getting the length of the tunnel
    if (start.getX() == end.getX()){
      length = end.getY()-ycor;
    }
    else{
      length = end.getX()-xcor;
    }
    length++;  //Length is one more than difference
    //Getting the Orientation
    if (start.getX() == end.getX()){
      direction = 0;
    }
    if (start.getY() == end.getY()){
      direction = 1;
    }
    //Creating blocksHere with a loop
    //If left-right, xcor changes
    if (direction == 0){
      blocksHere = new Block[1][length];
      for (int i = 0; i < length; i++){
        blocksHere[0][i] = new Block(xcor+i, ycor, "Tunnel");
      }
    }
    //If top-bottom, ycor changes
    else{
      blocksHere = new Block[length][1];
      for (int j = 0; j < length; j++){
        blocksHere[j][0] = new Block(xcor, ycor+j, "Tunnel");
      }
    }
  }

  //public boolean isExplored()
  /** Checks to see if this segment of Tunnel has been explored by a Player
    *@return true when: segment has been explored
    *        false when: segment has not been explored
  */
  public boolean isExplored(){
    return isExplored;
  }

  //public void setVisibility()
  /** Sets isExplored of all Blocks in this segment of Tunnel to true
  */
  public void setVisibility(){
    for (int x = 0; x < blocksHere.length; x++){
      for (int y = 0; x < blocksHere[x].length; y++){
        blocksHere[x][y].setVisibility();
      }
    }
    //To be edited later such that only the next block will be visible, not the whole tunnel
    //Requires input from the Player
  }

  //public boolean nextToTunnel(Block startBlock, Block endBlock)
  /**Checks if Tunnel to be made will be parallel and next to another Tunnel
    *@param startBlock the topmost or leftmost Block of the Tunnel to be made
    *@param endBlock the bottommost or rightmost Block of the Tunnel to be made
    *@return true if the Tunnel to be made is 1 away and parallel to another Tunnel and longer than 1
    *        false if the Tunnel to be made is length 1 or the directions of each Tunnel is differnt
  */
  public boolean nextToTunnel(Block startBlock, Block endBlock){
    //Can't have parallel tunnels that have length longer than 2
    // one block away from each other.
    // That would essentially be like creating a Room
    int direction = 0;
    if (startBlock.getX() == endBlock.getX()){
      direction = 0;
    }
    if (startBlock.getY() == endBlock.getY()){
      direction = 1;
    }
    if (length == 1 || (direction + this.direction) == 1){
      return false;
    }
    if (Math.abs(this.startBlock.getX()-startBlock.getX()) == 1||
        Math.abs(this.startBlock.getY()-startBlock.getY()) == 1){
    return true;
    }
    return false;
  }

  //public boolean nextToRoom(Block startBlock, Block endBlock)
  /**Checks if Tunnel to be made will be parallel and next to another Tunnel
    *@param startBlock the topmost or leftmost Block of the Tunnel to be made
    *@param endBlock the bottommost or rightmost Block of the Tunnel to be made
    *@param r is the Room the function is basing the test off of
    *@return true if the difference between the xcors and ycors is not 1 and the direction is different or Tunnel intrsects with a Rooms
    *        false if the Tunnel to be made is length 1 or doesn't intersect  or borderthe Room
  */
  public static boolean nextToRoom(Block startBlock, Block endBlock, Room r){
    if (startBlock.equals(endBlock)){
      return false;
    }
    int direction = 0;
    if (startBlock.getX() == endBlock.getX()){
      direction = 0;
    }
    if (startBlock.getY() == endBlock.getY()){
      direction = 1;
    }
    if ((Math.abs(startBlock.getX()-r.getStartXcor()) == 1 && direction == 0)||
        (Math.abs(startBlock.getY()-r.getStartYcor()) == 1 && direction == 1)||
        (Math.abs(endBlock.getX()-r.getEndXcor()) == 1 && direction == 0)||
        (Math.abs(endBlock.getY()-r.getEndYcor()) == 1 && direction == 1)||
        //Above checks for if Tunnel would border a Room
        //Below checks for if Tunnel intersects with a Room
        (r.getEndXcor() >= startBlock.getX())
        && (r.getStartXcor() <= endBlock.getX())
        && (r.getStartYcor()<=endBlock.getY())
        && (r.getEndYcor() >= startBlock.getY())){
      return true;
    }
    return false;
  }

  //public String toString()
  /**Creates a String of the data of the Blocks in the Tunnel
    *@return a String of the data of the Blocks in the Tunnel
  */
  public String toString(){
    String output = "";
    if (direction == 0){ //horizontal, so x changes
      for (int i = 0; i < length; i++){
        output+=blocksHere[0][i].getData();
      }
    }
    //If top-bottom, ycor changes
    else{ //vertical, so y changes
      for (int i = 0; i < length; i++){
        output+= blocksHere[i][0].getData();
        if (i!= length-1){
          output += "\n";
        }
      }
    }
    return output;
  }

  //Getters
  public int getLength(){
    return length;
  }
  public Block getStartBlock(){
    return startBlock;
  }
  public Block getEndBlock(){
    return endBlock;
  }
  public String getDirection(){
    if (direction == 0){
      return "Left-Right";
    }
    else{
      return "Top-Bottom";
    }
  }
}
