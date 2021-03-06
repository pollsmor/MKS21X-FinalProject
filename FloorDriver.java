import java.util.Random;
public class FloorDriver{
  public static void main(String[] args){
    Random rnd = new Random();
    int seed = Math.abs(rnd.nextInt()) % 10000;
    int width = rnd.nextInt(40)+20;
    int length = rnd.nextInt(40)+20;
    if (args.length == 1){
      seed = Integer.parseInt(args[0]);
    }
    if (args.length == 2){
      width = Integer.parseInt(args[0]);
      length = Integer.parseInt(args[1]);
    }
    if (args.length == 3){
      seed = Integer.parseInt(args[0]);
      width = Integer.parseInt(args[1]);
      length = Integer.parseInt(args[2]);
    }
    Floor a = new Floor(1, width, length);
    //System.out.println(a.toString());
    System.out.println("Width: "+ width);
    System.out.println("Length: "+ length);
    System.out.println("Seed: "+seed);
    a.createRooms(seed);
    System.out.println(a.toString());
    //a.spawnObjective(seed);
    //System.out.println(a.toString());
    //a.createRoom(0, 0, 2, 2);
    //System.out.println(a.toString());
    //a.createRoom(3, 4, 10, 10);
    //System.out.println(a.toString());
    //a.createRoom(0, 9, 4, 12);
    //System.out.println(a.toString());

    //Testing Room constructors
    //Room z = new Room(0, 0, 5, 5);
    //System.out.println(z.toString());
    //Room y = new Room(2, 6, 7, 8);
    //System.out.println(y.toString());

    //Testing tooClose
    //Room roomOriginal = new Room(4, 4, 7, 7);
    /*System.out.println("\tResult: "+roomOriginal.tooClose(10, 10, 11, 11)); //Should print false
    System.out.println("\tResult: "+roomOriginal.tooClose(1, 1, 3, 3)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(2, 4, 6, 6)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(4, 9, 8, 7)); //Should print false
    System.out.println("\tResult: "+roomOriginal.tooClose(3, 2, 9, 9)); //Should print false
    System.out.println("\tResult: "+roomOriginal.tooClose(8, 7, 10, 10)); //Should print true
*/
    //Testing bordering
/*
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 3, 4)); //Should print true
    System.out.println("Should trigger Case 1\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 3, 7)); //Should print true
    System.out.println("Should trigger Case 1\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 4, 3)); //Should print true
    System.out.println("Should trigger Case 2\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 7, 3)); //Should print true
    System.out.println("Should trigger Case 2\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(8, 4, 10, 10)); //Should print true
    System.out.println("Should trigger Case 3\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(8, 7, 10, 10)); //Should print true
    System.out.println("Should trigger Case 3\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(7, 8, 10, 10)); //Should print true
    System.out.println("Should trigger Case 4\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(4, 8, 4, 10)); //Should print true
    System.out.println("Should trigger Case 4\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(5, 5, 6, 6)); //Should print true
    System.out.println("Should trigger all Cases\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(4, 4, 7, 7)); //Should print true
    System.out.println("Should trigger all Cases\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(0,0,10,10)); //Should print true
    System.out.println("Should trigger all Cases\n");
    System.out.println("\tResult: "+roomOriginal.tooClose(0,0,2,2)); //Should print false
    System.out.println("Should trigger no Cases\n");
    */

    //Testing constructor Tunnel(int startXcor, int startYcor, int newLength, boolean isHorizontal)
    //Tunnel tuna = new Tunnel(5, 5, 2, true, true, a);
    //System.out.println(tuna.toString()+"\n");
    //System.out.println(a.toString());
    /*System.out.println(tuna.getLength());
    System.out.println(tuna.getStartBlock().toString());
    System.out.println(tuna.getEndBlock().toString());*/
    //Tunnel salmon = new Tunnel(9, 9, 5, false, false, a);
    //System.out.println(salmon.toString()+"\n");

    //Tunnel tilapia = new Tunnel(10, 7, 4, true, false, a);
    //System.out.println(tilapia.toString() +"\n");

    //Tunnel cod = new Tunnel( 8, 2, 3, false, true, a);
    //System.out.println(cod.toString() +"\n");

    //Tunnel clownfish = new Tunnel (1, 1, 1, false, true, a);
    //System.out.println(clownfish.toString()+"\n");

    //Testing constructor Tunnel(Block start, Block end)
    //Block bleh = new Block(9, 9,"Tunnel");
    //Block blah = new Block (9, 13, "Tunnel");
    //Should be 5 long
    //Tunnel bluh = new Tunnel(bleh, blah, a);
    //System.out.println(a.toString());
    //System.out.println(bluh.getLength());
    //System.out.println(bluh.toString());
    //System.out.println();
    //Block starter = new Block(0,0,"Tunnel");
    //Block ender = new Block(1,0,"Tunnel");
    //System.out.println(starter.getData());
    //Tunnel fish = new Tunnel(starter, ender, a);
    //System.out.println(fish.getLength());
    //System.out.println(fish.getStartBlock().toString());
    //System.out.println(fish.getEndBlock().toString());
    //System.out.println(fish.toString());
    //Testing nextToRoom

  }
}
