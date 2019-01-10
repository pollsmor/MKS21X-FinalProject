public class Tunnel implements Explorable{
  private boolean isExplored;
  private Block[] blocksHere;
  private Block startBlock; //Note: coors will be less than or equal to endBlock
  private Block endBlock; //Note: coors will be larger than or equal to startBlock
  private int length; //How many blocks long is the Tunnel
  private int direction; //Orientation of Tunnel: 0 is left=right, 1 is up-down

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
      length = ycor-end.getY();
    }
    else{
      length = xcor-end.getX();
    }
    //Getting the Orientation
    if (start.getXcor() == end.getXcor()){
      direction = 0;
    }
    if (start.getYcor() == end.getYcor()){
      direction = 1;
    }
    blocksHere = new Block[length];
    //Creating blocksHere with a loop
    for (int i = 0; i < length; i++){
      blocksHere[i] = new Block(xcor, ycor+i, "Tunnel");
    }
    length += 1; //Length is one more than difference
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
    for (int i = 0; i < blocksHere.length; i ++){
      blocksHere[i].setVisibility();
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
    int direction;
    if (startBlock.getXcor() == endBlock.getXcor()){
      direction = 0;
    }
    if (startBlock.getYcor() == endBlock.getYcor()){
      direction = 1;
    }
    if (length == 1 || (t.direction + this.direction) == 1){
      return false;
    }
    if (Math.abs(this.startBlock.getXcor()-startBlock.getXcor()) == 1||
        Math.abs(this.startBlock.getYcor()-startBlock.getYcor()) == 1)
    return true;
  }

  //public boolean nextToRoom(Block startBlock, Block endBlock)
  /**Checks if Tunnel to be made will be parallel and next to another Tunnel
    *@param startBlock the topmost or leftmost Block of the Tunnel to be made
    *@param endBlock the bottommost or rightmost Block of the Tunnel to be made

    *@return true if the difference between the xcors and ycors is not 1 and the direction is different or Tunnel intrsects with a Rooms
    *        false if the Tunnel to be made is length 1 or doesn't intersect  or borderthe Room
  */
  public static boolean nextToRoom(Block startBlock, Block endBlock, Room r){
    if (length == 1){
      return false;
    }
    int direction;
    if (startBlock.getXcor() == endBlock.getXcor()){
      direction = 0;
    }
    if (startBlock.getYcor() == endBlock.getYcor()){
      direction = 1;
    }
    if ((Math.abs(startBlock.getXcor()-r.getStartXcor()) == 1 && direction == 0)||
        (Math.abs(startBlock.getYcor()-r.getStartYcor()) == 1 && direction == 1)||
        (Math.abs(endBlock.getXcor()-r.getEndXcor() == 1) && direction == 0)||
        (Math.abs(endBlock.getYcor()-r.getEndYcor() == 1) && direction == 1)||
        //Above checks for if Tunnel would border a Room
        //Below checks for if Tunnel intersects with a Room
        (r.getEndXcor() >= startBlock.getXcor())
        && (r.getStartXcor() <= endBlock.getXcor())
        && (r.getStartYcor()<=endBlock.getYcor())
        && (r.getEndYcor >= startBlock.getYcor())){
      return true;
    }
    return false;
  }
}
