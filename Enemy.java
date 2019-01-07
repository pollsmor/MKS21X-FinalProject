public class Enemy extends Pokemon {
  private int killXP;

  public Enemy(String name, int level, int maxHP, int attack, int defense) {
    super(name, level, maxHP, attack, defense);
  }

  public int getKillXP() {
    return killXP;
  }
}
