public class Item {
  //private Status status //for later
  private String name;
  private String description;
  private int value;
  private int amount;

  public Item(String inputName) {
    if (inputName == "potion") {
      name = "Potion"; //description: taken from Bulbapedia
      description = "A spray-type medicine for treating wounds. It can be used to restore 20 HP to an injured Pokémon.";
      value = 20;
      amount = 10;
    }

    if (inputName == "superpotion") {
      name = "Super Potion"; //description: taken from Bulbapedia
      description = "A spray-type medicine for treating wounds. It can be used to restore 60 HP to an injured Pokémon.";
      value = 60;
      amount = 5;
    }

    if (inputName == "hyperpotion") {
      name = "Hyper Potion"; //description: taken from Bulbapedia
      description = "A spray-type medicine for treating wounds. It can be used to restore 120 HP to an injured Pokémon.";
      value = 120;
      amount = 2;
    }

    if (inputName == "maxpotion") {
      name = "Hyper Potion"; //description: taken from Bulbapedia
      description = "A spray-type medicine for treating wounds. It can be used to restore 120 HP to an injured Pokémon.";
      value = 9999; //since it heals you fully and chances are no Pokemon will reach above 1000 HP let alone 9999
      amount = 0;
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

  public int getAmount() {
    return amount;
  }
}
