public class Player extends Pokemon {
  private int maxHunger;
  private int currentHunger;
  private int xp;
  private int money;
  private int score; //Kinda useless but whatever

  //In the core games the starter is typically lvl 5, 20 HP, and around 10 atk/def
  public Player(String name) {
    super(name, 5, 20, 10, 10);
    maxHunger = 10;
    currentHunger = maxHunger;
    xp = 0;
    money = 500;
    score = 0;
  }

  public int getHunger() {
    return currentHunger;
  }

  public void basicAttack(Enemy enemy, int damage) {
    enemy.setHP(-1 * damage);
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
