public class Floor{
  private Block[][] blocksHere; //Array of blocks (like patches)
  private int floorNumber;
  private int width;
  private int length;
  //private Mission[] missions; to be added later if we get to it

  //public Floor(int num, int terminal Width, int terminalLength)
  /** Constructs a Floor based on the terminal width and length and assigns the floor a number
    *@param num: an int which will become the floorNumber
    *@param terminalWidth: an int which will become the floor's width (used for creating blocksHere)
    *@param terminalLength: an int which will become the floor's length (used for creating blocksHere)
  */
  public Floor(int num, int terminalWidth, int terminalLength){
    floorNumber = num;
    blocksHere = new Block[terminalWidth][terminalLength];
  }

  public int getFloor(){
    return floorNumber;
  }
}
