public class Item {
  //private Status status //for later
  private String name;
  private String description;
  private int value;

  public Item(String name) {
    if (this.name == "potion") {
      name = "Potion"; //description: taken from Bulbapedia
      description = "A spray-type medicine for treating wounds. It can be used to restore 20 HP to an injured Pokémon.";
      value = 20;
    }
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getValue() {
    return value;
  }
}
