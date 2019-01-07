public abstract class Pokemon {
  private String name;
  private int level;
  //private Type type; for later
  private int maxHP;
  private int currentHP;
  private int attack;
  private int defense;
  private int stepsTaken; //for healPassive
  //private Item[] toolbox; for later
  //private Status[] status; for later
  //private Move move1; for later
  //private Move move2; for later
  //private Move move3; for later
  //private Move move4; for later

  public Pokemon(String name, int level, int maxHP, int attack, int defense) {
    name = this.name;
    level = this.level;
    maxHP = this.maxHP;
    currentHP = maxHP;
    attack = this.attack;
    defense = this.defense;
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
}
