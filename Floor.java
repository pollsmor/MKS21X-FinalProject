public class Floor{
  private Block[][] blocksHere; //Array of blocks (like patches)
  private int floorNumber;
  //private Mission[] missions; to be added later if we get to it

  public Floor(int num, int terminalWidth, int terminalLength){
    floorNumber = num;
    blocksHere = new Block[terminalWidth][terminalLength];
  }

  public int getFloor(){
    return floorNumber;
  }
}
