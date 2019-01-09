public class Enemy extends Pokemon {
  private int killXP;

  //same constructor as player but this allows custom values
  public Enemy(String name, int level, int maxHP, int attack, int defense) {
    super(name, level, maxHP, attack, defense);
  }

  //add this XP to the player when the monster is killed
  public int getKillXP() {
    return killXP;
  }

  public void atkPlayer(Player player, int damage) {
    player.setHP(-1 * damage);
  }
}
