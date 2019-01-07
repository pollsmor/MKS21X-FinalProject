public class Player extends Pokemon {
  private int maxHunger;
  private int currentHunger;
  private int xp;

  public Player(String name) {
    super(name, 5, 20, 5, 5);
  }

  public int getHunger() {
    return currentHunger;
  }

  public void faintPlayer() {
    Game.endGame();
  }

  public void basicAttack(Enemy enemy, int damage) {
    enemy.setHP(-1 * damage);
  }
}
