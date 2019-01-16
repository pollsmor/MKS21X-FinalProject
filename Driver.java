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
    //Minimum terminal size requirement - prevents index exceptions and having too little space to work with
    if (length < 70 || width < 21) {
      terminal.exitPrivateMode();
      System.out.println("This game can only be played on a terminal at least 70px in length, and 21px in width.");
      System.exit(0);
    }

    String name = args[0];
    int seed;
    Game game;
    Random randgenCol;
    Random randgenRow;

    if (name.length() > 25) {
      terminal.exitPrivateMode();
      System.out.println("There are no Pokemon that have this long of a name.");
      System.exit(0);
    }


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

    //Colors/styles to use
    String red = "\u001B[31m";
    String green = "\u001B[32m";
    String white = "\u001B[37m";
    String black = "\u001B[30m";;
    String bgWhite = "\u001B[47m";
    String bgBlack = "\u001B[40m";
    String underline = "\u001B[4m";
    String blink = "\u001B[5m";
    String resetColor = "\u001B[0m"; //need to add this or the whole program will be the selected color

    //Print the game's UI elements
    putString(0, 0, terminal, game.getFloor().toStringClean());
    putString(1, width * 3/4 + 1, terminal, bgWhite + black + game.getPlayer().getName() + resetColor);
    putString(length - 25, width * 3/4 + 1, terminal, underline + blink + "Pok" + '\u00e9' + "mon Terminal Dungeon" + resetColor);
    //Row 1 of stats, below the name                                             //accented e
    putString(1, width * 3/4 + 2, terminal, "HP: ");
    putString(13, width * 3/4 + 2, terminal, "Level: ");
    putString(28, width * 3/4 + 2, terminal, "Attack: ");
    putString(43, width * 3/4 + 2, terminal, "Defense: ");
    //Row 2 of stats
    putString(1, width * 3/4 + 3, terminal, "Area: ");
    putString(13, width * 3/4 + 3, terminal, "Hunger: ");
    putString(28, width * 3/4 + 3, terminal, "Money: ");
    putString(length - 17, width - 2, terminal, "Time: " );
    putString(length - 17, width - 1, terminal, "Seed: " + game.getSeed());

    boolean running = true;
    boolean alive = true; //controls the inner while loop

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
      terminal.applyForegroundColor(Terminal.Color.GREEN);
      terminal.putCharacter('\u04dd');
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

      //Do even when no key is pressed
      //First row of stats
      putString(5, width * 3/4 + 2, terminal, green + game.getPlayer().getHP());
      putString(20, width * 3/4 + 2, terminal, "" + game.getPlayer().getLevel());
      putString(36, width * 3/4 + 2, terminal, "" + game.getPlayer().getAttack());
      putString(52, width * 3/4 + 2, terminal, "" + game.getPlayer().getDefense());
      //Second row of stats
      putString(7, width * 3/4 + 3, terminal, "" + game.getLevel());
      putString(21, width * 3/4 + 3, terminal, "" + game.getPlayer().getHunger());
      putString(35, width * 3/4 + 3, terminal, "" + game.getPlayer().getMoney() + '\u00a5' + resetColor); //yen symbol

      long tEnd = System.currentTimeMillis();
      long millis = tEnd - tStart;
      if (millis / 1000 > lastSecond) {
        lastSecond = millis / 1000; //One second has passed.
        putString(length - 11, width - 2, terminal, lastSecond + "s");
      }
    }

    terminal.exitPrivateMode();
    System.out.println("Thank you for playing Pokemon Terminal Dungeon.");
    System.exit(0);
  }
}
