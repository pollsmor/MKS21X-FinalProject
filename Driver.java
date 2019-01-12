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

public class Driver {
  public static void putString(int r, int c, Terminal t, String s) {
    t.moveCursor(r, c);
    for (int i = 0; i < s.length(); ++i) {
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main(String[] args) {
    //The game definitely needs at least 1 input to run, so this runs first.
    if (args.length == 0) {
      System.out.println("Please provide the name of a Pokemon, and optionally a seed.");
      System.exit(0);
    }

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();
    terminal.setCursorVisible(false);

    TerminalSize terminalSize = terminal.getTerminalSize();
    int width = terminalSize.getRows();
    int length = terminalSize.getColumns();

    //Timer commands
    long tStart = System.currentTimeMillis();
    long lastSecond = 0;

    //I don't want this to be try-ed in case the user only provides 1 input (which is valid)
    if (args.length >= 2) {
      try {
        Integer.parseInt(args[1]);
      }

      catch (NumberFormatException e) {
        terminal.exitPrivateMode();
        System.out.println("Seeds can only be integers.");
        System.exit(0);
      }
    }

    String name = args[0];
    int seed;
    Game game;
    //This should really be in the random spawn generation section, but
    //I need to define this outside the following if statements.
    //The if statements distinguish between whether a seed is provided or not.
    Random randgenCol;

    //Instantiate game outside of try since it wouldn't work inside it
    if (args.length == 1) {
      game = new Game(name, width, length);
      randgenCol = new Random();
    }

    else {
      seed = Integer.parseInt(args[1]);
      game = new Game(name, seed, width, length);
      randgenCol = new Random(seed); //always spawn in the same spot
    }

    //Print the game
    putString(0, 0, terminal, game.getFloor().toString());

    boolean running = true;
    boolean alive = true; //controls the inner while loop

    //Random spawn generation
    int row = 0;
    int col = 0;
    boolean spawnFound = false;
    while (!spawnFound) {
      col = Math.abs(randgenCol.nextInt() % (width * 3/4));
      if (game.blockExists(col, row)) {
        spawnFound = true;
        --row;
      }

      ++row;
    }

    //Move one to the right and one below because toString adds a - and a | but the array doesn't know that
    ++row;
    ++col;

    while (running) {
      if (col < 0)
        col = 1;

      if (row < 0)
        row = 1;

      terminal.moveCursor(col, row);
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
        putString(0, width, terminal,"You died. Would you like to respawn? (y/n)");
        while (!alive) {
          Key key2 = terminal.readInput(); //if I'm in the if I can't read the first readInput, so I need a second one
          if (key2 != null) {
            //Do you want to respawn?
            if (key2.getCharacter() == 'y') {
              alive = true;                //Below is so I can replace the you died text:
              putString(0, width, terminal, "                                          ");
            }

            //No, I don't want to respawn.
            if (key2.getCharacter() == 'n') {
              alive = true;
              running = false;
            }
          }
        }
      }

      if (key != null) {
        String lastDirection = "";

        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }

        if (key.getKind() == Key.Kind.ArrowLeft) {
          if (game.blockExists(col, row)) {
            lastDirection = "left";
            terminal.moveCursor(col, row);
            terminal.putCharacter('R');
            --col;
          }
        }

        if (key.getKind() == Key.Kind.ArrowRight) {
          if (game.blockExists(col, row)) {
            lastDirection = "right";
            terminal.moveCursor(col, row);
            terminal.putCharacter('R');
            ++col;
          }
        }

        if (key.getKind() == Key.Kind.ArrowUp) {
          if (game.blockExists(col, row)) {
            lastDirection = "up";
            terminal.moveCursor(col, row);
            terminal.putCharacter('R');
            --row;
          }
        }

        if (key.getKind() == Key.Kind.ArrowDown) {
          if (game.blockExists(col, row)) {
            terminal.moveCursor(col, row);
            terminal.putCharacter('R');
            ++row;
          }
        }

        if (key.getKind() == Key.Kind.Backspace) {
          alive = false;
        }

        //putString(1, 1, terminal, key + "        "); //to clear leftover letters pad withspaces
      }

      //Do even when no key is pressed:ßß
      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      //putString(length - 16, width, terminal, millis + "ms");
      if (millis / 1000 > lastSecond) {
        lastSecond = millis / 1000; //One second has passed.
        putString(length - 5, width, terminal, lastSecond + "s");
      }
    }

    terminal.exitPrivateMode();
    System.out.println("Thank you for playing Pokemon Terminal Dungeon.");
    System.exit(0);
  }
}
