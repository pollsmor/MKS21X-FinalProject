public abstract class Pokemon {
  private String name;
  private int level;
  private Type type;
  private int maxHP;
  private int currentHP;
  private int attack;
  private int defense;
  private int stepsTaken; //for healPassive
  private Item[] bag;
  private Status[] statuses;
  private Move move1;
  private Move move2;
  private Move move3;
  private Move move4;

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
        ++currentHP;
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

  public boolean moveUp {

  }

  public boolean moveDown {

  }

  public boolean moveLeft {

  }

  public boolean moveRight {

  }
}
