public class Room implements Explorable{
  private boolean isExplored;
  private Block[][] blocksHere;
  private int startXcor, startYcor, endXcor, endYcor;
  private int width;
  private int length;

  //public Room(Block start, Block end)
  /**Creates a Room given two blocks (start, end) with the start block being at the top left and the end block being at the bottom right
    *Precondition: start is to the top and left of end block
    *@param start is the Block representing the top left Block of the Room to be made
    *@param end is the Block representing the bottom right Block of the Room to be made
  */
  public Room(Block start, Block end){
    //Storing starting coordinates
    int xcor = start.getX();
    int ycor = start.getY();
    //Storing length and width
    width = xcor - end.getX();
    length = ycor - end.getY();
    startXcor = xcor;
    startYcor = ycor;
    endXcor = end.getX();
    endYcor = end.getY();
    blocksHere = new Block[width][length];
    for (int x = 0; x < length; x++){
      for (int y = 0; y < width; y++){
        blocksHere[x][y] = new Block(xcor+x,ycor+y,"Room");
      }
    }
  }

  //public Room(int startXcor, int startYcor, int endXcor, int endYcor
  /**Creates a Room given the coordinates of two blocks (start, end) with the start block being at the top left and the end block being at the bottom right
    *Precondition: start is to the top and left of end block
    *@param startXcor is the xcor of the Block representing the top left Block of the Room to be made
    *@param endXcor is the xcor of the Block representing the bottom right Block of the Room to be made
    *@param startYcor is the xcor of the Block representing the top left Block of the Room to be made
    *@param endYcor is the xcor of the Block representing the bottom right Block of the Room to be made
  */
  public Room(int newStartXcor, int newStartYcor, int newEndXcor, int newEndYcor){
    startXcor = newStartXcor;
    startYcor = newStartYcor;
    endXcor = newEndXcor;
    endYcor = newEndYcor;
    width = endXcor - startXcor;
    length = endYcor - startYcor;
    blocksHere = new Block[width][length];
    for (int x = 0; x < width; x++){
      for (int y = 0; y < length; y++){
        blocksHere[x][y] = new Block(startXcor+x,startYcor+y,"Room");
      }
    }
  }

  //public boolean tooClose(int startXcor, int startYcor, int endXcor, int endYcor)
  /**Tests if rooms overlap with each other or border each other
    *@param startXcor is the xcor of the Block representing the top left Block of the Room to be made
    *@param endXcor is the xcor of the Block representing the bottom right Block of the Room to be made
    *@param startYcor is the xcor of the Block representing the top left Block of the Room to be made
    *@param endYcor is the xcor of the Block representing the bottom right Block of the Room to be made
    *@return whether or not the rooms will overlap or border
  */
  public boolean tooClose(int startXcor, int startYcor, int endXcor, int endYcor){
    //Make sure that rooms don't overlap with each other
    //Rooms overlap when...
    //Case 1:
    //    startXcor of Room to be created is more than or equal to startXcor of a Room that already exists
    //AND startXcor of Room to be created is less than or equal to endXcor + 1 of a Room that already exists
    //AND startYcor of Room to be created is more than or equal to startYcor of a Room that already exists
    //AND startYcor of Room to be created is less than or equal to endYcor + 1 of a Room that already exists
    /*
      0 1 2 3 4 5 6 7 8 9 10
    0 + - - - - - - - - - - +
    1 |    Original         |
    2 |             + - - - - - +
    3 + - - - - - - |    New    |
    4               + - - - - - +
    */
    //Case 2:
    //    endXcor of Room to be created is less than or equal to endXcor of a Room that already exists
    //AND endXcor of Room to be created is more than or equal to startXcor - 1 of a Room that already exists
    //AND endYcor of Room to be created is less than or equal to endYcor of a Room that already exists
    //AND endYcor of Room to be created is more than or equal to startYcor - 1 of a Room that already exists
    /*
      0 1 2 3 4 5 6 7 8 9 10
    0 + - - - - - - - - - - +
    1 |                    |
    2 |       New          + - - - - - +
    3 + - - - - - - - - - |  Original |
    4                    + - - - - - +
    */

    boolean case1 = (startXcor >= this.startXcor && startXcor<=endXcor + 1 && startYcor >= this.startYcor && startYcor<this.endYcor + 1);
    boolean case2 = (endXcor <= this.endXcor && endXcor >= this.startXcor + 1 && endYcor <= this.endYcor && endYcor >= this.startYcor + 1);
    System.out.println("Case 1: "+case1);
    System.out.println("Case 2: "+case2);
    return (case1||case2);
  }
  public String toString(){
    String output = "";
    for (int x = 0; x < width; x++){
      for (int y = 0; y < length; y++){
        output+= blocksHere[x][y].getData();
      }
      output+= "\n";
    }
    return output;
  }
  //public boolean isExplored()
  /**Returns whether or not the Room has been explored
    *@return true when: the Player has entered the Room before
    *        false when: the Player has not entered the Room before
  */
  public boolean isExplored(){
    return isExplored;
  }

  //public void setVisibility()
  /**Sets isExplored of all Blocks in the Room to true
  */
  public void setVisibility(){
    for (int x = 0; x < width; x++){
      for (int y = 0; y < length; y++){
        blocksHere[x][y].setVisibility();
      }
    }
    if (!isExplored){
      isExplored = true;
    }
    else{
      isExplored = false;
    }
  }
}
