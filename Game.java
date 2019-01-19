import java.util.Random;

public class Game {
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
  public Game(String name, int rows, int cols) {
    player = new Player(name);
    int amtEnemies = rows * cols / 450;
    enemies = new Enemy[amtEnemies];
    level = 1;
    floor = new Floor(1, cols, rows * 3/4); //Floor constructor takes left to right before top to bottom
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    Random randgen = new Random();
    seed = randgen.nextInt();
    isRandomSeed = true;
    termRows = rows;
    termCols = cols;

    floor.createRooms(seed);
    spawnEnemies();
  }

  //Takes a seed
  public Game(String name, int inputSeed, int rows, int cols) {
    player = new Player(name);
    int amtEnemies = rows * cols / 450;
    enemies = new Enemy[amtEnemies];
    level = 1;
    floor = new Floor(1, cols, rows * 3/4);
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    seed = inputSeed;
    isRandomSeed = false;
    termRows = rows;
    termCols = cols;

    floor.createRooms(seed);
    spawnEnemies();
  }

  public Player getPlayer() {
    return player;
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

  public int[] spawnPlayer() {
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

  public int[] spawnEnemy() {
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

  private void spawnEnemies() {
    for (int i = 0; i < enemies.length; ++i) {
      int[] enemySpawn = spawnEnemy();
      int row = enemySpawn[0];
      int col = enemySpawn[1];
      floor.getBlock(row, col).spawnEnemyHere();
    }
  }
}
