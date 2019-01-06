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

  public Block(x,y){ //Constructor given coordinates
    isVisible = false;
    objectiveHere = false;
    xcor = x;
    ycor = y;
    color = "black";
    data = ' ';
  }

  public boolean getVisibility(){
    return isVisible;
  }

  public boolean setVisibility(){
    isVisible = !isVisible;
    return isVisible;
  }

  public boolean isObjective() {
    return objectiveHere;
  }

  public void spawnItem(){

  }

  public void spawnEnemy() {
    Enemy newEnemy = new Enemy();
    data = 'E';
    pokemonHere = newEnemy;
  }

  public void spawnObjective() {
    objectiveHere = true;
    data = '%';
  }

  public void spawnPlayer() {
    Player newPlayer = new Player();
    data = 'P';
    pokemonHere = newPlayer;
  }
}
