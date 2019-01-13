import java.util.Random;

public class Floor{
  private Block[][] blocksHere; //Array of Blocks (like patches)
  private int floorNumber;
  private int width;
  private int length;
  private Room[] roomsHere; //List of Rooms
  private int numRooms; //Number of successful Rooms
  //private Tunnels[] tunnelsHere; //List of total tunnels created
  //private Mission[] missions; to be added later if we get to it

  //public Floor(int num, int terminal Width, int terminalLength)
  /** Constructs a Floor based on the terminal width and length and assigns the floor a number
    *@param num an int which will become the floorNumber
    *@param terminalWidth an int which will become the floor's width (used for creating blocksHere)
    *@param terminalLength an int which will become the floor's length (used for creating blocksHere)
  */
  public Floor(int num, int terminalWidth, int terminalLength){
    floorNumber = num;
    width = terminalWidth;
    length = terminalLength;
    blocksHere = new Block[width][length];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < length; y++) {
        blocksHere[x][y] = new Block(x, y, "Wall");
      }
    }
  }

  public Block[][] getMap() {
    return blocksHere;
  }

  public Block getBlock(int col, int row) {
    return blocksHere[row][col];
  }

  //public int getFloorNumber()
  /**Returns the number of floors the Player has successfully passed
    *@return floorNumber: an int which represents the number of Floors played in the current Game
  */
  public int getFloorNumber(){
    return floorNumber;
  }
  //public int getWidth()
  /**Returns the width of the Floor
    *@return width: an int which represents the width of the current Floor
  */
  public int getWidth(){
    return width;
  }
  //public int getLength()
  /**Returns the length of the Floor
    *@return length: an int which represents the length of the current Floor
  */
  public int getLength(){
    return length;
  }

  //public void createRooms(int seed)
  /**Spawns rooms on the Floor given a seed
    *@param seed is an int given to randomly generate Rooms
  */
  public void createRooms(int seed){
    Random rnd = new Random(seed); //Takes seed generated by Game class
    //First decide number of Rooms to create
    int rooms = rnd.nextInt(3) + (width * length)/20;
    roomsHere = new Room[rooms];
    //System.out.println("Rooms: "+rooms);
    int attempts = 5000; //In case it's impossible to create all the rooms, have a set number of failed attempts possible
    //Using random, generate the xcors and ycors of top right Blocks and bottom left Blocks of the Rooms
    //Minimum width of room: 4 | Max: 13
    //Minimum length of room: 4 | Max: 13
    int startXcor, startYcor, endXcor, endYcor;
    int successfulRooms = 0; //Keep track of how many Rooms were successfully made
    boolean wasOverlap; //Keep track of whether or Room to be created overlaps with any other Room
    while (successfulRooms < rooms && attempts > 0){
      startXcor = Math.abs(rnd.nextInt(width - 13)) + 1;
      endXcor = startXcor + rnd.nextInt(11) + 4;
      startYcor = Math.abs(rnd.nextInt(length - 13)) + 1;
      endYcor = startYcor + rnd.nextInt(11) + 4;
      //System.out.println("startXcor: "+ startXcor +", startYcor: "+startYcor+", endXcor: "+endXcor+", endYcor: "+endYcor);
      wasOverlap = false;
      //Make sure that rooms don't overlap with each other
      //System.out.println("startXcor: "+ startXcor +", startYcor: "+startYcor+", endXcor: "+endXcor+", endYcor: "+endYcor);
      for (int i = 0; i < successfulRooms; i++){
        if (roomsHere[i].tooClose(startXcor, startYcor, endXcor, endYcor)){
          wasOverlap = true;
          i = roomsHere.length; //Stop loop once it has been discovered that a Room will overlap the new one
          attempts--; //Reduce attempts by 1
        }
      }
      if (!wasOverlap){ //If there were no overlapping Rooms, create the Room
        roomsHere[successfulRooms]= createRoom(startXcor, startYcor, endXcor, endYcor);
        successfulRooms++;
        //Connect to a room
        //Create a tunnel
        //Allow tunnels to pass through each other
      }
    }
    numRooms = successfulRooms;
  }

  //public Room createRoom(int startXcor, int startYcor, int endXcor, int endYcor)
  /**Creates a single room given the coordinates of the start and end Blocks
    *@param startXcor is the int representing the x-coordinate of the starting Block
    *@param startYcor is the int representing the y-coordinate of the starting Block
    *@param endXcor is the int representing the x-coordinate of the ending Block
    *@param endYcor is the int representing the y-coordinate of the ending Block
    *@return the new successfully created Room
  */
  public Room createRoom(int startXcor, int startYcor, int endXcor, int endYcor){
    Room a = new Room(startXcor, startYcor, endXcor, endYcor);
    //Must also update blocksHere
    for (int x = startXcor; x < endXcor - 1; x++){
      for (int y = startYcor; y < endYcor - 1; y++){
        this.blocksHere[x][y] = new Block(startXcor+x,startYcor+y,"Room");
      }
    }
    return a;
  }

  //public Tunnel createTunnel(Room r1, Room r2){
  /*
  *Each tunnel is only in one direction and as such is assigned one direction: 0 is left-right, 1 is up-down
  *Tunnels may intersect with other Tunnels
  */
    //Some function to get a border block from room 1 and room 2
    //Tunnel a = new Tunnel(Block, Block);
    //Construct the Tunnel
    //return a;
    //return Tunnel made
  //}

  public String toString(){
    String output = "|";
    for (int y = 0; y < length; y++){
      output += "-";
      //if (y != length - 1){
        //output += " ";
      //}
    }
    output += "|\n";
    for (int x = 0; x < width; x++){
      output+="|";
      for (int y = 0; y < length; y++){
        output+= blocksHere[x][y].getData();
        //if (y != length - 1){
        //  output += " ";
        //}
      }
      output+="|\n";
    }
    output +="|";
    for (int y = 0; y < length; y++){
      output += "-";
      //if (y != length - 1){
      //  output += " ";
      //}
    }

    return output;
  }

  public Block[][] getBlocksHere(){
    return blocksHere;
  }

  public static String toStringBlocks(Block[][] b){
    String output = "";
    for (int i = 0; i < b.length; i++){
      for (int j = 0; j < b[i].length; j++){
        output += b[i][j].getData();
        if (i!=b[i].length - 1){
          output += ", ";
        }
      }
      if (i!=b.length - 1){
        output += "\n";
      }
    }
    return output;
  }

  public String toStringClean() {
    String output = "";

    for (int i = 0; i < width; ++i) {
      for (int j = 0; j < length; ++j) {
        if (j == length - 1)
          output += "|";

        else
          output += blocksHere[i][j].getData();
      }

      output += '\n';
    }

    for (int i = 0; i < length; ++i)
      output += '-';
      
    return output;
  }
}
