public class Player extends Pokemon {
  private int maxHunger;
  private int currentHunger;
  private int xp;
  private int money;
  private int score; //Kinda useless but whatever
  private Item[] bag;

  //In the core games the starter is typically lvl 5, 20 HP, and around 10 atk/def
  public Player(String name, int row, int col) {
    super(name, 5, 20, 10, 10, row, col);
    maxHunger = 10;
    currentHunger = maxHunger;
    xp = 0;
    money = 500;
    score = 0;

    //Item bag
    bag = new Item[4];
    bag[0] = new Item("potion");
    bag[1] = new Item("superpotion");
    bag[2] = new Item("hyperpotion");
    bag[3] = new Item("maxpotion");
  }

  public int getHunger() {
    return currentHunger;
  }

  public int getXP() {
    return xp;
  }

  public int getMoney() {
    return money;
  }

  public int getScore() {
    return score;
  }
}
