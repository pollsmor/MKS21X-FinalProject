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
  private int level = 1; //Starting off on the first floor
  private Floor floor;
  private Mission[] missions;
  private int seed;
  private static boolean alive = true; //toggle this on or off to respawn or end the game, controls while loop

  public Game(String name, int terminalWidth, int terminalLength) {
    player = new Player(name);
    enemies = new Enemy[12];
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    Random randgen = new Random();
    seed = randgen.nextInt() % 10000;
    floor = new Floor(1, terminalWidth, terminalLength);
  }

  public Game(String name, int seed, int terminalWidth, int terminalLength) {
    player = new Player(name);
    enemies = new Enemy[12];
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    seed = this.seed % 10000;
    floor = new Floor(1, terminalWidth, terminalLength);
  }

  //Simply controls the inner while loop
  public static void faintPlayer() {
    alive = false;
  }

  public static void putString(int r, int c, Terminal t, String s) {
    t.moveCursor(r, c);
    for (int i = 0; i < s.length(); ++i) {
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main(String[] args) {
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize terminalSize = terminal.getTerminalSize();
    int width = terminalSize.getRows();
    int length = terminalSize.getColumns();
    terminal.setCursorVisible(false);

    //Start in the bottom center
    int x = terminalSize.getColumns() / 2;
    int y = terminalSize.getRows() - 2;

    long tStart = System.currentTimeMillis();
    long lastSecond = 0;

    boolean running = true;

    String name;
    int seed;

    try {
      if (args.length == 1) {
        name = args[0];
        Game game = new Game(name, width, length);
      }

      else {
        name = args[0];
        seed = Integer.parseInt(args[1]);
        Game game = new Game(name, seed, width, length);
      }
    }

    catch (NumberFormatException e) {
      terminal.exitPrivateMode();
      System.out.println("Seeds can only be integers.");
      System.exit(0);
    }

    catch (ArrayIndexOutOfBoundsException e) {
      terminal.exitPrivateMode();
      System.out.println("You need to provide the name of a Pokemon and a seed, in that order.");
      System.exit(0);
    }

    while (running) {
      terminal.moveCursor(x, y);
      //Applying background makes it look bad, can't see the symbol as easily
      //terminal.applyBackgroundColor(Terminal.Color.WHITE);
      terminal.applyForegroundColor(Terminal.Color.GREEN); //Green is nice, right?
      terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
      terminal.putCharacter('\u04dd'); //was '\u00a4'
      terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
      terminal.applyForegroundColor(Terminal.Color.DEFAULT);
      terminal.applySGR(Terminal.SGR.RESET_ALL);

      Key key = terminal.readInput();

      if (!alive) {
        putString(0, terminalSize.getRows(), terminal,"You died. Would you like to respawn? (y/n)");
        while (!alive) {
          Key key2 = terminal.readInput(); //if I'm in the if I can't read the first readInput, so I need a second one
          if (key2 != null) {
            if (key2.getCharacter() == 'y') {
              alive = true;                                     //Below is so I can replace the you died text:
              putString(0, terminalSize.getRows(), terminal, "                                          ");
            }

            if (key2.getCharacter() == 'n') {
              alive = true;
              running = false;
            }
          }
        }
      }

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }

        if (key.getKind() == Key.Kind.ArrowLeft) {
          terminal.moveCursor(x, y);
          terminal.putCharacter(' ');
          --x;
        }

        if (key.getKind() == Key.Kind.ArrowRight) {
          terminal.moveCursor(x, y);
          terminal.putCharacter(' ');
          ++x;
        }

        if (key.getKind() == Key.Kind.ArrowUp) {
          terminal.moveCursor(x, y);
          terminal.putCharacter(' ');
          --y;
        }

        if (key.getKind() == Key.Kind.ArrowDown) {
          terminal.moveCursor(x, y);
          terminal.putCharacter(' ');
          ++y;
        }

        if (key.getKind() == Key.Kind.Backspace) {
          faintPlayer();
        }

        putString(1, 1, terminal, key + "        "); //to clear leftover letters pad withspaces
      }

      //Do even when no key is pressed:ßß
      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      putString(1, 2, terminal, "Milliseconds since start of program: " + millis);
      if (millis / 1000 > lastSecond) {
        lastSecond = millis / 1000; //One second has passed.
        putString(1, 3, terminal, "Seconds since start of program: " + lastSecond);
      }
    }

    terminal.exitPrivateMode();
    System.out.println("Thank you for playing Pokemon Terminal Dungeon.");
    System.exit(0);
  }
}
