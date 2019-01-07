public class Player extends Pokemon {
  private int maxHunger;
  private int currentHunger;
  private int xp;
  private String name;

  public Player(String name) {
    super(name);
  }

  public int getHunger() {
    return currentHunger;
  }
}
