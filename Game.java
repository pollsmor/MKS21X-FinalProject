//API : http://mabe02.github.io/lanterna/apidocs/2.1/
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
  //private Pokemon[] pokemon;
  //private Player player;
  //private Enemy[] enemies;
  private int floors;
  //private Items[] items;
  //private Mission[] missions;
  private int seed;
  private static boolean alive;

  public Game() {
    alive = true;
  }

  public static void endGame() {
    alive = false;
  }

  public static void putString(int r, int c, Terminal t, String s) {
    t.moveCursor(r, c);
    for (int i = 0; i < s.length(); ++i) {
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main(String[] args) {
    int x = 10;
    int y = 10;

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize terminalSize = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    long tStart = System.currentTimeMillis();
    long lastSecond = 0;

    boolean running = true;

    while (running) {
      terminal.moveCursor(x, y);
      terminal.applyBackgroundColor(Terminal.Color.YELLOW);
      terminal.applyForegroundColor(Terminal.Color.RED);
      terminal.putCharacter('O');
      terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
      terminal.applyForegroundColor(Terminal.Color.DEFAULT);

      Key key = terminal.readInput();

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }

        if (key.getKind() == Key.Kind.ArrowLeft) {
          terminal.moveCursor(x, y);
          terminal.putCharacter('P');
          --x;
        }

        if (key.getKind() == Key.Kind.ArrowRight) {
          terminal.moveCursor(x, y);
          terminal.putCharacter('P');
          ++x;
        }

        if (key.getKind() == Key.Kind.ArrowUp) {
          terminal.moveCursor(x, y);
          terminal.putCharacter('P');
          --y;
        }

        if (key.getKind() == Key.Kind.ArrowDown) {
          terminal.moveCursor(x, y);
          terminal.putCharacter('P');
          ++y;
        }

<<<<<<< HEAD
        if (key.getKind() == Key.Kind.Backspace) {
=======
        if (key.getKind() == Key.Kind.n) {
>>>>>>> 2332db6593b6d15a24aaa14e4abc8e0d7f742f5e
          alive = false;
        }

        putString(1, 1, terminal, key + "        "); //to clear leftover letters pad withspaces
      }

      if (!alive) {
        putString(x, y, terminal, "You have died.");
        System.exit(0);
      }

      //Do even when no key is pressed:
      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      putString(1, 2, terminal, "Milliseconds since start of program: " + millis);
      if (millis / 1000 > lastSecond) {
        lastSecond = millis / 1000; //One second has passed.
        putString(1, 3, terminal, "Seconds since start of program: " + lastSecond);
      }
    }
  }
}
