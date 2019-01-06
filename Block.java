public class Block {
  private boolean isVisible;
  //private item itemHere; for later
  //private Pokemon pokemonHere; for later
  private boolean objectiveHere;
  //private String type; for later
  private int xcor;
  private int ycor;
  private String color;
  private char data; //what's printed in the block

  public Block(int x, int y){ //Constructor given coordinates
    isVisible = false;
    objectiveHere = false;
    xcor = x;
    ycor = y;
    color = "black";
    data = ' ';
  }
//----------Getters and Setters----------//

  //----------Visibility----------//
  public boolean getVisibility(){
    return isVisible;
  }

  public boolean setVisibility(){
    isVisible = !isVisible;
    return isVisible;
  }

  //----------Objectives---------//
  public boolean isObjective() {
    return objectiveHere;
  }

  //----------Type of Block----------//
  public String getType(){
    return null;
  }

  //----------Coodinate stuffs----------//
  public int getX(){
    return xcor;
  }

  public int getY(){
    return ycor;
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
