public abstract class Pokemon {
  private String name;
  private int level;
  private Type type;
  private int maxHP;
  private int currentHP;
  private int attack;
  private int defense;
  private int stepsTaken; //for healPassive
  private Item[] bag;
  private Status[] statuses;
  private Move move1;
  private Move move2;
  private Move move3;
  private Move move4;
  private int row;
  private int col;

  public Pokemon(String inputName, int inputLevel, int inputMaxHP, int inputAttack, int inputDefense, int inputRow, int inputCol) {
    name = inputName;
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

  public int setHP(int change) {
    int temp = currentHP; //return this for other purposes
    currentHP += change; //can be positive or negative depending on case
    return temp;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public boolean moveUp(Game game) {
    if (!game.isWall(row - 1, col)) { //if next Block is not a Wall
      if (game.isTunnel(row, col)){ //If current Block is a Tunnel
        if (game.getBlock(row,col).canMove('u')){ //Check if Block allows you to move up
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          --row;
        }
        else{
          return false;
        }
      }
      else{ //Current Block is a Room Block and next Block is not a Wall
        if (game.isTunnel(row - 1, col)){ //If the next Block is a Tunnel
          if (game.getBlock(row - 1, col).canMove('d')){ //Check that that Tunnel Block allows you to move down
            game.getFloor().getBlock(row, col).setPokemonHere(null);
            --row;
          }
          else{ //If not return false
            return false;
          }
        }
        //Next Block is Room Block
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        --row;
      }
      return true;
    }
    return false;
  }

  public boolean moveDown(Game game) {
    if (!game.isWall(row + 1, col)) {//if next Block is not a Wall
      if (game.isTunnel(row, col)){//If current Block is a Tunnel
        if (game.getBlock(row,col).canMove('d')){//Check if Block allows you to move down
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          ++row;
        }
        else{
          return false;
        }
      }
      else{ //Current Block is a Room Block and next Block is not a Wall
        if (game.isTunnel(row + 1, col)){ //If the next Block is a Tunnel
          if (game.getBlock(row + 1, col).canMove('u')){ //Check that that Tunnel Block allows you to move up
            game.getFloor().getBlock(row, col).setPokemonHere(null);
            ++row;
          }
          else{ //If not return false
            return false;
          }
        }
        //Next Block is Room Block
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        ++row;
      }
      return true;
    }
    return false;
  }

  public boolean moveLeft(Game game) {
    if (!game.isWall(row, col - 1)) {//if next Block is not a Wall
      if (game.isTunnel(row, col)){//If current Block is a Tunnel
        if (game.getBlock(row,col).canMove('l')){//Check if Block allows you to move left
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          --col;
        }
        else{//If not return false
          return false;
        }
      }
      else{ //Current Block is a Room Block and next Block is not a Wall
        if (game.isTunnel(row, col - 1)){ //If the next Block is a Tunnel
          if (game.getBlock(row, col - 1).canMove('r')){ //Check that that Tunnel Block allows you to move right
            game.getFloor().getBlock(row, col).setPokemonHere(null);
            --col;
          }
          else{ //If not return false
            return false;
          }
        }
        //Next Block is Room Block
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        --col;
      }
      return true;
    }
    return false;
  }

  public boolean moveRight(Game game) {
    if (!game.isWall(row, col + 1)) {//if next Block is not a Wall
      if (game.isTunnel(row, col)){//If current Block is a Tunnel
        if (game.getBlock(row,col).canMove('r')){//Check if Block allows you to move right
          game.getFloor().getBlock(row, col).setPokemonHere(null);
          ++col;
        }
        else{//If not return false
          return false;
        }
      }
      else{ //Current Block is a Room Block and next Block is not a Wall
        if (game.isTunnel(row, col + 1)){ //If the next Block is a Tunnel
          if (game.getBlock(row, col + 1).canMove('l')){ //Check that that Tunnel Block allows you to move left
            game.getFloor().getBlock(row, col).setPokemonHere(null);
            ++col;
          }
          else{ //If not return false
            return false;
          }
        }
        //Next Block is Room Block
        game.getFloor().getBlock(row, col).setPokemonHere(null);
        ++col;
      }
      return true;
    }
    return false;
  }
}
