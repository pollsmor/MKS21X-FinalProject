public class Enemy extends Pokemon {
  private int killXP;

  //same constructor as player but this allows custom values
  public Enemy(int row, int col) {
    //name = "genericEnemy";
    //level = (int) (Math.random() * 1000) % 10;
    //maxHP = (int) (Math.random() * 1000) % 40;
    //attack = (int) (Math.random() * 1000) % 55; //weaker enemies so the player doesn't just die
    //defense = (int) (Math.random() * 1000) % 10;

    super("genericEnemy", //Name
         (int) (Math.random() * 1000) % 10, //Level
         (int) (Math.random() * 1000) % 40, //Max HP
         (int) (Math.random() * 1000) % 5,  //Attack
         (int) (Math.random() * 1000) % 10, //Defense
         row, col);
  }

  //add this XP to the player when the monster is killed
  public int getKillXP() {
    return killXP;
  }
}
