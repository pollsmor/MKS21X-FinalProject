//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;

import java.util.Random;

public class Game {
  private Player player;
  private Enemy[] enemies;
  private int level;
  private Floor floor;
  private Mission[] missions;
  private int seed;

  //Doesn't take a seed, so a random one is generated
  public Game(String name, int rows, int cols) {
    player = new Player(name);
    enemies = new Enemy[12]; //random number
    level = 1;
    floor = new Floor(1, cols, rows * 3/4); //Floor constructor takes left to right before top to bottom
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    Random randgen = new Random();
    seed = randgen.nextInt();

    floor.createRooms(seed);
  }

  //Takes a seed
  public Game(String name, int inputSeed, int rows, int cols) {
    player = new Player(name);
    enemies = new Enemy[12];
    level = 1;
    floor = new Floor(1, cols, rows * 3/4);
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    seed = inputSeed;

    floor.createRooms(seed);
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
}
