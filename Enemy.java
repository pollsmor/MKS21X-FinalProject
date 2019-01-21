public class Enemy extends Pokemon {
  private int killXP;

  //same constructor as player but this allows custom values
  public Enemy(int row, int col) {
    //name = "genericEnemy";
    //level = (int) (Math.random() * 1000) % 10;
    //maxHP = (int) (Math.random() * 1000) % 40;
    //attack = (int) (Math.random() * 1000) % 10;
    //defense = (int) (Math.random() * 1000) % 10;

    super("genericEnemy",
         (int) (Math.random() * 1000) % 10,
         (int) (Math.random() * 1000) % 40,
         (int) (Math.random() * 1000) % 5,
         (int) (Math.random() * 1000) % 10,
         row, col);
  }

  //add this XP to the player when the monster is killed
  public int getKillXP() {
    return killXP;
  }
}
