public class Pokemon {
  private String name;
  private int level;
  //private Type type; for later
  private double maxHP;
  private double currentHP;
  private int attack;
  private int defense;
  private int stepsTaken; //for healPassive
  //private Item[] toolbox; for later
  //private Status[] status; for later
  //private Move move1; for later
  //private Move move2; for later
  //private Move move3; for later
  //private Move move4; for later

  public Pokemon(String){
    name = String;
    level = 1;
    maxHP = 20;
    currentHP = 20;
    attack = 10;
    defense = 10;
    stepsTaken = 0;
  }

  public void healPassive() {
    if (stepsTaken == 6){
      stepsTaken = 0;
      if (currentHP < maxHP){
        currentHP++;
      }
    }
  }
}
