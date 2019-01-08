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

public class Game {
  private Player player;
  private Enemy[] enemies;
  private int floor = 1; //Starting off on the first floor
  private Mission[] missions;
  private int seed;
  private static boolean alive = true; //toggle this on or off to respawn or end the game, controls while loop

  public Game(int seed) {
    player = new Player("Bulbasaur");
    enemies = new Enemy[12];
    //missions = new Mission(); //ArrayList to allow easy adding/removing
    seed = this.seed % 10000;
  }

  public static void endGame() { //Just so the alive variable can be changed by other classes
    alive = false;
  }

  public static void putString(int r, int c, Terminal t, String s) {
    t.moveCursor(r, c);
    for (int i = 0; i < s.length(); ++i) {
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main(String[] args) {
    Player squirtle = new Player("yoloswag");

    //Start in the bottom center
    int x = 40;
    int y = 20;

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize terminalSize = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    long tStart = System.currentTimeMillis();
    long lastSecond = 0;

    boolean running = true;


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
          squirtle.faintPlayer();
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
