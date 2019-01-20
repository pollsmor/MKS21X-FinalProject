import java.util.Random;

public class Game {
  private String name;
  private Player player;
  private Enemy[] enemies;
  private int level;
  private Floor floor;
  private Mission[] missions;
  private int seed;
  private boolean isRandomSeed;
  private int termRows;
  private int termCols;

  //Doesn't take a seed, so a random one is generated
  public Game(String inputName, int rows, int cols, int newLevel) {
    termRows = rows;
    termCols = cols;
    level = newLevel;
    floor = new Floor(level, cols, rows * 3/4); //Floor constructor takes left to right before top to bottom
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    Random randgen = new Random();
    seed = randgen.nextInt();
    isRandomSeed = true;
    floor.createRooms(seed);

    name = inputName;
    spawnPlayer();
    int amtEnemies = rows * cols / 450;
    enemies = new Enemy[amtEnemies];
    spawnEnemies();
  }

  //Takes a seed
  public Game(String inputName, int inputSeed, int rows, int cols, int newLevel) {
    termRows = rows;
    termCols = cols;
    level = newLevel;
    floor = new Floor(level, cols, rows * 3/4);
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    seed = inputSeed;
    isRandomSeed = false;
    floor.createRooms(seed);

    name = inputName;
    spawnPlayer();
    int amtEnemies = rows * cols / 450;
    enemies = new Enemy[amtEnemies];
    spawnEnemies();
  }

  public Player getPlayer() {
    return player;
  }

  public Enemy[] getEnemies() {
    return enemies;
  }

  public Enemy getEnemy(int num) {
    return enemies[num];
  }

  public int getLevel() {
    return level;
  }

  public Floor getFloor() {
    return floor;
  }

  //Allows getting a single mission from the array
  public Mission getMission(int number) {
    return missions[number];
  }

  //Why a getSeed? Because the one provided for main isn't the final seed - it gets run through the constructor.
  public int getSeed() {
    return seed;
  }

  public boolean isWall(int row, int col) {
    return floor.getBlock(row, col).getType() == "Wall";
  }

  public boolean isTunnel(int row, int col){
    return floor.getBlock(row, col).getType() == "Tunnel";
  }

  public boolean isObjective(int row, int col){
    return floor.getBlock(row, col).getType() == "Objective";
  }

  public Block getBlock(int row, int col){
    return floor.getBlock(row, col);
  }

  public int[] createSpawnPlayer() {
    Random randgenRow = new Random();
    Random randgenCol = new Random();

    if (!isRandomSeed) {
      randgenRow = new Random(seed + 2);
      randgenCol = new Random();
    }

    int row = 0;
    int col = 0;

    boolean spawnFound = false;
    while (!spawnFound) {
      row = Math.abs(randgenRow.nextInt() % (termRows * 3/4));
      col = Math.abs(randgenCol.nextInt() % termCols);
      if (!isWall(row, col))
        spawnFound = true;
    }

    int[] output = new int[2];
    output[0] = row;
    output[1] = col;

    return output;
  }

  public int[] createSpawnEnemy() {
    Random randgenRow = new Random();
    Random randgenCol = new Random();

    if (!isRandomSeed) {
      randgenRow = new Random(seed + 3);
      randgenCol = new Random();
    }

    int row = 0;
    int col = 0;

    boolean spawnFound = false;
    while (!spawnFound) {
      row = Math.abs(randgenRow.nextInt() % (termRows * 3/4));
      col = Math.abs(randgenCol.nextInt() % termCols);
      if (!isWall(row, col))
        spawnFound = true;
    }

    int[] output = new int[2];
    output[0] = row;
    output[1] = col;

    return output;
  }

  private void spawnPlayer() {
      int[] playerSpawn = createSpawnPlayer();
      int row = playerSpawn[0];
      int col = playerSpawn[1];
      player = new Player(name, row, col);
  }

  private void spawnEnemies() {
    for (int i = 0; i < enemies.length; ++i) {
      int[] enemySpawn = createSpawnEnemy();
      int row = enemySpawn[0];
      int col = enemySpawn[1];
      enemies[i] = new Enemy(row, col);
      floor.getBlock(row, col).spawnEnemyHere(enemies[i]);
    }
  }

  public boolean enemyNearby() {
    return
    (floor.getBlock(player.getRow() -1, player.getCol() - 1).getPokemonHere() != null) ||  //top left
    (floor.getBlock(player.getRow() -1, player.getCol()).getPokemonHere() != null) ||      //top
    (floor.getBlock(player.getRow() -1, player.getCol() + 1).getPokemonHere() != null) ||  //top right
    (floor.getBlock(player.getRow(), player.getCol() + 1).getPokemonHere() != null) ||     //right
    (floor.getBlock(player.getRow() + 1, player.getCol() + 1).getPokemonHere() != null) || //bottom right
    (floor.getBlock(player.getRow() + 1, player.getCol()).getPokemonHere() != null) ||     //bottom
    (floor.getBlock(player.getRow() + 1, player.getCol() - 1).getPokemonHere() != null) || //bottom left
    (floor.getBlock(player.getRow(), player.getCol() - 1).getPokemonHere() != null);        //left
  }
}
