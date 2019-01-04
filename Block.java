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

  public boolean isObjective() {
    return objectiveHere;
  }

  public void spawnItem(){

  }

  public void spawnEnemy() {

  }

  public void spawnObjective() {
    objectiveHere = true;
    data = '%';
  }

  public void spawnPlayer() {

  }
}
