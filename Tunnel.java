public class Tunnel implements Explorable{
  private boolean isExplored;
  private Block[][]  blocksHere;

  //public Tunnel(Block start, Block end)
  /**Creates a Tunnel given two blocks (start, end) beginning with the start block and ending at the end block
    *Precondition: start and end block have either the same xcor or the same ycor
    *@param start is the Block representing the start of the Tunnel to be made
    *@param end is the Block representing the end of the tunnel to be made
  */
  public Tunnel(Block start, Block end){
    isExplored = false;
  }

  public boolean isExplored(){
    return isExplored;
  }
}
