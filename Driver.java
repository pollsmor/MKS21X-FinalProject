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

//----------------------------------------------------------------------------------------------------------------

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

    //I don't want this to be try-ed in case the user only provides 1 input (which is valid)
    if (args.length >= 2) {
      try {
        Integer.parseInt(args[1]);
      }

      catch (NumberFormatException e) {
        System.out.println("Seeds can only be integers.");
        System.exit(0);
      }
    }

    //Timer commands
    long tStart = System.currentTimeMillis();
    long lastSecond = 0;

    //Terminal creation commands
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();
    terminal.setCursorVisible(false);
    TerminalSize terminalSize = terminal.getTerminalSize();
    int width = terminalSize.getRows();
    int length = terminalSize.getColumns();
    //Minimum terminal size requirement - prevents index exceptions and having too small of a map
    if (length < 55 || width < 20) {
      terminal.exitPrivateMode();
      System.out.println("This game can only be played on a terminal at least 55px in length, and 20px in width.");
      System.exit(0);
    }

    String name = args[0];
    int seed;
    Game game;
    Random randgenCol;
    Random randgenRow;

    //Instantiate game outside of try since it wouldn't work inside it
    if (args.length == 1) {
      game = new Game(name, width, length);
      randgenCol = new Random();
      randgenRow = new Random();
    }

    else {
      seed = Integer.parseInt(args[1]);
      game = new Game(name, seed, width, length);
      randgenCol = new Random(seed); //always spawn in the same spot
      randgenRow = new Random(seed + 2);
    }

    //Print the game
    putString(0, 0, terminal, game.getFloor().toStringClean());
    putString(length - 17, 0, terminal, "HP: ");
    putString(length - 17, 1, terminal, "Area: ");
    putString(length - 17, width - 1, terminal, "Time: ");
    putString(length - 17, width - 2, terminal, "Seed: " + game.getSeed());

    boolean running = true;
    boolean alive = true; //controls the inner while loop
    String lastKey = "";

    //Random spawn generation
    int col = 0;
    int row = 0;
    boolean spawnFound = false;
    while (!spawnFound) {
      col = Math.abs(randgenCol.nextInt() % (length * 3/4));
      row = Math.abs(randgenRow.nextInt() % (width * 3/4));
      if (!game.isWall(col, row)) {
        spawnFound = true;
      }
    }

    while (running) {
      terminal.moveCursor(col, row);
      terminal.applyForegroundColor(Terminal.Color.GREEN); //Green is nice, right?
      terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
      terminal.putCharacter('\u04dd'); //was '\u00a4'
      terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
      terminal.applyForegroundColor(Terminal.Color.DEFAULT);
      terminal.applySGR(Terminal.SGR.RESET_ALL);

      Key key = terminal.readInput();

    //----------------------------------------------------------------------------------------------------------------
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
    //----------------------------------------------------------------------------------------------------------------

      if (key != null) {
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          System.exit(0);
        }

        if (key.getKind() == Key.Kind.ArrowLeft) {
          if (!game.isWall(col - 1, row)) {
            terminal.moveCursor(col, row);
            terminal.putCharacter('R');
            --col;
          }
        }

        if (key.getKind() == Key.Kind.ArrowRight) {
          if (!game.isWall(col + 1, row)) {
            terminal.moveCursor(col, row);
            terminal.putCharacter('R');
            ++col;
          }
        }

        if (key.getKind() == Key.Kind.ArrowUp) {
          if (row != 0)
            if (!game.isWall(col, row - 1)) {
              terminal.moveCursor(col, row);
              terminal.putCharacter('R');
              --row;
            }
        }

        if (key.getKind() == Key.Kind.ArrowDown) {
          if (!game.isWall(col, row + 1)) {
            terminal.moveCursor(col, row);
            terminal.putCharacter('R');
            ++row;
          }
        }

        //Just to demo the death function in class
        if (key.getKind() == Key.Kind.Delete)
          alive = false;
      }

    //----------------------------------------------------------------------------------------------------------------

      //Do even when no key is pressed:ßß
      putString(length - 10, 0, terminal, "" + game.getPlayer().getHP());
      putString(length - 10, 1, terminal, "" + game.getLevel());

      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      if (millis / 1000 > lastSecond) {
        lastSecond = millis / 1000; //One second has passed.
        putString(length - 11, width, terminal, lastSecond + "s");
      }
    }

    terminal.exitPrivateMode();
    System.out.println("Thank you for playing Pokemon Terminal Dungeon.");
    System.exit(0);
  }
}
