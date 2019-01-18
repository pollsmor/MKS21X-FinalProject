public class Block implements Explorable{
  private boolean isVisible;
  private Item itemHere;
  private Pokemon pokemonHere;
  private boolean objectiveHere;
  private String type;
  private int xcor;
  private int ycor;
  private String color;
  private char data; //what's printed in the block
  private boolean canMoveLeft, canMoveRight, canMoveUp, canMoveDown; //To handle adjacent Tunnels
  private int direction; //For intersection Tunnels

  public Block(int x, int y, String newType) { //Constructor given coordinates
    isVisible = false;
    objectiveHere = false;
    xcor = x;
    ycor = y;
    color = "black";
    //Default data ' ' is for Wall
    data = ' ';
    //For testing purposes
    if (newType.equals("Room")){
      data = 'R';
    }
    if (newType.equals("Tunnel")){
      data = 'T';
    }
    if (newType.equals("Opening")){
      data = '0';
    }
    type = newType;
  }

  public Block (int x, int y, int dir){ //Separate constructor for Tunnels
    isVisible = false;
    objectiveHere = false;
    xcor = x;
    ycor = y;
    color = "gray";
    if (dir == 0){ //LR
      data = '=';
    }
    if (dir == 1){ //UD
      data = '|';
    }
    if (dir == 2){ //EndBlock of tunnel or intersection of Tunnels
      data = '#';
    }
    direction = dir;
    type = "Tunnel";
  }

  public String toString(){
    return data + "";
  }
//----------Getters and Setters----------//

  //----------Visibility----------//
  public boolean isExplored(){
    return isVisible;
  }

  public void setVisibility(){
    if (!isVisible){
      isVisible = true;
    }
    else{
      isVisible = false;
    }
  }

  //----------Objectives---------//
  public boolean isObjective() {
    return objectiveHere;
  }

  //----------Type of Block----------//
  public String getType(){
    return type;
  }

  //----------Coodinate stuffs----------//
  public int getX(){
    return xcor;
  }

  public int getY(){
    return ycor;
  }

  public char getData(){
    return data;
  }

  public String setType(String newType){
    String oldType = type;
    type = newType;
    if (type == "Opening"){
      data = '0';
    }
    return oldType;
  }

  public String printPoint(){
    return "("+xcor+", "+ ycor +")";
  }

  public void setCanMove(String dir,boolean b){
    if (dir == "u"){
      canMoveUp = b;
    }
    if (dir == "d"){
      canMoveDown = b;
    }
    if (dir == "l"){
      canMoveLeft = b;
    }
    if (dir == "r"){
      canMoveRight = b;
    }
  }

  public int getDirection(){
    return direction;
  }

/* Spawns
  public void spawnObjective() {
    objectiveHere = true;
    data = '%';
  }
  public void spawnItem(){
    Item newItem = new Item();
    data = 'I';
    itemHere = newItem;
  }

  public void spawnEnemy() {
    Enemy newEnemy = new Enemy();
    data = 'E';
    pokemonHere = newEnemy;
  }

  public void spawnPlayer() {
    Player newPlayer = new Player();
    data = 'P';
    pokemonHere = newPlayer;
  }*/
}
