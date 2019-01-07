public class FloorDriver{
  public static void main(String[] args){
    Floor a = new Floor(1, 15, 15);
    //System.out.println(a.toString());
    a.createRooms(1);
    System.out.println(a.toString());
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
  }
}
