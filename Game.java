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
import java.util.Arrays;

public class Game {
  private Player player;
  private Enemy[] enemies;
  private int level;
  private Floor floor;
  private Mission[] missions;
  private int seed;

  //Doesn't take a seed, so a random one is generated
  public Game(String name, int terminalWidth, int terminalLength) {
    player = new Player(name);
    enemies = new Enemy[12]; //random number
    level = 1;
    floor = new Floor(1, terminalWidth, terminalLength);
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    Random randgen = new Random();
    seed = randgen.nextInt() % 10000; //arbitrary number, limit seeds to less than 10000
  }

  //Takes a seed
  public Game(String name, int seed, int terminalWidth, int terminalLength) {
    player = new Player(name);
    enemies = new Enemy[12];
    level = 1;
    floor = new Floor(1, terminalWidth, terminalLength);
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    seed = this.seed % 10000;
  }

  public int getLevel() {
    return level;
  }

  public Floor getFloor() {
    return floor;
  }
}
