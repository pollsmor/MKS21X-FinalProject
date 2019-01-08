public class FloorDriver{
  public static void main(String[] args){
    Floor a = new Floor(1, 15, 15);
    //System.out.println(a.toString());
    //a.createRooms(1);
    //System.out.println(a.toString());
    //a.createRoom(0, 0, 2, 2);
    //System.out.println(a.toString());
    //a.createRoom(3, 4, 10, 10);
    //System.out.println(a.toString());
    //a.createRoom(0, 9, 4, 12);
    //System.out.println(a.toString());

    //Testing Room constructors
    /*Room z = new Room(0, 0, 5, 5);
    System.out.println(z.toString());
    Room y = new Room(2, 6, 7, 8);
    System.out.println(y.toString());*/

    //Testing tooClose
    Room roomOriginal = new Room(4, 4, 7, 7);
    /*System.out.println("\tResult: "+roomOriginal.tooClose(10, 10, 11, 11)); //Should print false
    System.out.println("\tResult: "+roomOriginal.tooClose(1, 1, 3, 3)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(2, 4, 6, 6)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(4, 9, 8, 7)); //Should print false
    System.out.println("\tResult: "+roomOriginal.tooClose(3, 2, 9, 9)); //Should print false
    System.out.println("\tResult: "+roomOriginal.tooClose(8, 7, 10, 10)); //Should print true
*/
    //Testing bordering
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 4, 3)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 3, 4)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 7, 3)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(8, 4, 10, 10)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(8, 7, 10, 10)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(7, 8, 10, 10)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 3, 7)); //Should print true
    System.out.println("\tResult: "+roomOriginal.tooClose(0, 0, 4, 10)); //Should print true

  }
}
