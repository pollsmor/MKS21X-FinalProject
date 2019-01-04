public class Room{
  private boolean isExplored;
  private Block[][] blocksHere;
  private int seed;

  public Room(){
    Random rnd = new Random();
    int length = Math.abs(rnd.nextInt() % 7);
    int width = Math.abs(rnd.nextInt() % 7);
    blocksHere = new Block[length][width];
  }

}
