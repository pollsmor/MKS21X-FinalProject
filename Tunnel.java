public class Tunnel implements Explorable{
  private boolean isExplored;
  private Block[] blocksHere;
  private Block startBlock;
  private Block endBlock;

  //public Tunnel(Block start, Block end)
  /**Creates a Tunnel given two blocks (start, end) beginning with the start block and ending at the end block
    *Precondition: start and end block have either the same xcor or the same ycor
    *@param start is the Block representing the start of the Tunnel to be made
    *@param end is the Block representing the end of the tunnel to be made
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
    blocksHere = new Block[length];
    //Creating blocksHere with a loop
    for (int i = 0; i < length; i++){
      blocksHere[i] = new Block(xcor, ycor+i, "Tunnel");
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
    for (int i = 0; i < blocksHere.length; i ++){
      blocksHere[i].setVisibility();
    }
  }
}
