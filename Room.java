
public class Room implements Explorable{
  private boolean isExplored;
  private Block[][] blocksHere;
  private int seed;
  private int width;
  private int length;

  //public Tunnel(Block start, Block end)
  /**Creates a Tunnel given two blocks (start, end) beginning with the start block and ending at the end block
    *Precondition: start is to the top and left of end block
    *@param start is the Block representing the top left Block of the Room to be made
    *@param end is the Block representing the bottom right Block of the Room to be made
  */
  public Room(Block start, Block end){
    int xcor = start.getX();
    int ycor = start.getY();
    width = xcor - end.getX();
    length = ycor - end.getY();
    blocksHere = new Block[width][length];
    for (int x = 0; x < length; x++){
      for (int y = 0; y < width; y++){
        blocksHere[x][y] = new Block(xcor+x,ycor+y,"Room");
      }
    }
  }

  public boolean isExplored(){

  }

  //public void setVisibility()
  /**Sets isExplored of all Blocks in the Room to true
  */
  public boolean setVisibility(){
    for (int x = 0; x < width; x++){
      for (int y = 0; y < length; y++){
        blocks.setVisibility();
      }
    }
  }
}
