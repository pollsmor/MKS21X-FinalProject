public class Player extends Pokemon {
  private int maxHunger;
  private int currentHunger;
  private int xp;

  //In the core games the starter is typically lvl 5, 20 HP, and around 10 atk/def
  public Player(String name) {
    super(name, 5, 20, 10, 10);
  }

  public int getHunger() {
    return currentHunger;
  }

  //Calls static fn. in Game to end inner while loop in Game
  public void faintPlayer() {
    Game.endGame();
  }

  public void basicAttack(Enemy enemy, int damage) {
    enemy.setHP(-1 * damage);
  }
}
