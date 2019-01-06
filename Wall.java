public class Wall{
  private Block blockHere;

  //public Wall(int xcor, int ycor)
  /**Creates a Wall at (xcor, ycor)
    *@param xcor: the x-coordinate of the new Wall
    *@param ycor: the y-coordinate of the new Wall
  */
  public Wall(int xcor, int ycor){
    blockHere = new Block(xcor, ycor, "Wall");
  }

  //public Wall(int xcor, int ycor)
  /**Creates a Wall at (xcor, ycor)
    *@param Block: the Block to be made a new Wall
  */
  public Wall(Block){
    blockHere = new Block(Block.getX(), Block.getY(), "Wall");
  }
}
