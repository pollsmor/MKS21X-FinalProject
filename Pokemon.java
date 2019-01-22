import java.util.Random;

public abstract class Pokemon {
  private String name;
  private int level;
  private Type type;
  private int maxHP;
  private int currentHP;
  private int attack;
  private int defense;
  private int stepsTaken; //for healPassive
  private Status[] statuses;
  private Move move1;
  private Move move2;
  private Move move3;
  private Move move4;
  private int row; //location in map
  private int col;

  public Pokemon(String inputName, int inputLevel, int inputMaxHP, int inputAttack, int inputDefense, int inputRow, int inputCol) {
    name = inputName; //why not the this command? Because this is totally broken for whatever reason
    level = inputLevel;
    maxHP = inputMaxHP;
    currentHP = maxHP;
    attack = inputAttack;
    defense = inputDefense;
    stepsTaken = 0;
    row = inputRow;
    col = inputCol;
  }

  public void healPassive() {
    if (stepsTaken == 15) {
      stepsTaken = 0;
      if (currentHP < maxHP)
        ++currentHP;
    }
  }

  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }

  public int getAttack() {
    return attack;
  }

  public int getDefense() {
    return defense;
  }

  public int getHP() {
    return currentHP;
  }

  public int getMaxHP() {
    return maxHP;
  }

  public int setHP(int change) {
    int temp = currentHP; //return this for other purposes
    currentHP += change; //can be positive or negative depending on case
    return temp;
  }

  public void maxRegen() {
    currentHP = maxHP;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public void moveUp(Game game) {
    if (!game.isWall(row - 1, col)) {
      if (game.isTunnel(row, col)) {
        //if (game.getBlock(row, col).canMove('u')) {
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          --row;
        //}
      }

      else {
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        --row;
      }
    }
  }

  public void moveDown(Game game) {
    if (!game.isWall(row + 1, col)) {
      if (game.isTunnel(row, col)) {
        //if (game.getBlock(row, col).canMove('d')) {
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          ++row;
        //}
      }

      else {
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        ++row;
      }
    }
  }

  public void moveLeft(Game game) {
    if (!game.isWall(row, col - 1)) {
      if (game.isTunnel(row, col)) {
        //if (game.getBlock(row, col).canMove('l')) {
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          --col;
        //}
      }

      else {
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        --col;
      }
    }
  }

  public void moveRight(Game game) {
    if (!game.isWall(row, col + 1)) {
      if (game.isTunnel(row, col)) {
        //if (game.getBlock(row, col).canMove('r')) {
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          ++col;
        //}
      }

      else {
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        ++col;
      }
    }
  }

  public int setRow(int newRow){
    int x = row;
    row = newRow;
    return x;
  }

  public int setCol(int newCol){
    int x = col;
    col = newCol;
    return x;
  }

  public void basicAttack(Pokemon pokemon, int damage) {
    pokemon.setHP(-1 * damage);
  }
}
