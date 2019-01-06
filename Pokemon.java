public class Pokemon {
  private String name;
  private int level;
  //private Type type; for later
  private int maxHP;
  private int currentHP;
  private int attack;
  private int defense;
  private int stepsTaken; //for healPassive
  private boolean isAlive;
  //private Item[] toolbox; for later
  //private Status[] status; for later
  //private Move move1; for later
  //private Move move2; for later
  //private Move move3; for later
  //private Move move4; for later

  public Pokemon(String name) {
    name = this.name;
    level = 5;
    maxHP = 20;
    currentHP = 20;
    attack = 4;
    defense = 10;
    stepsTaken = 0;
  }

  public void healPassive() {
    if (stepsTaken == 15) {
      stepsTaken = 0;
      if (currentHP < maxHP)
        currentHP++;
    }
  }

  public int getHP() {
    return currentHP;
  }

  public int setHP(int change) {
    int temp = currentHP; //return this for other purposes
    currentHP += change; //can be positive or negative depending on case
    return temp;
  }

  public void basicAttack(Enemy enemy) {

  }
}
