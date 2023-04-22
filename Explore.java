import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Point3D;
public class Explore {
    public static void main(String[] arguments) {
      //living room
      Item sofa=new Item("sofa","an old green velvet sofa", false ,false, false);
      Item chair=new Item("green chair","an old green velvet chair which matches the sofa", false, false, false);
      Item coffeeTable=new Item("coffee table","a short wooden table",false, false, true);
      ArrayList<Item> livingRoomContents=new ArrayList<>();
          livingRoomContents.add(sofa);
          livingRoomContents.add(chair);
          livingRoomContents.add(coffeeTable);
      
      Location livingRoom=new Location("Living Room: ", "You are in a room with faded, peeling wallpaper. There is a green sofa, and a matching chair around a small wooden table in the center of the room. A large woven rug is underfoot. On the north wall is a doorway. On the south wall is an empty brick fireplace.", livingRoomContents, true, false, false, false, false, false);

      //dining room
      Item diningTable=new Item("table","a large rectangular table", false ,false, false);
      Item plate=new Item("plate","blue and white ceramic plates", false, true, false);
      Item woodChair=new Item("chair","normal brown chairs",false, false, true);
      ArrayList<Item> diningRoomContents=new ArrayList<>();
          diningRoomContents.add(diningTable);
          diningRoomContents.add(woodChair);
          diningRoomContents.add(plate);
      
      Location diningRoom=new Location("Dining Room: ", "You are in a dining room. A long rectangular table stretches down the middle of the room. Nine chairs surround it and nine places are set. There is a curtain on the south wall. To the east is a doorway.", diningRoomContents,false,true,true, false, false, false);
      
      //entryway room
      Item boots=new Item("boots","a pair of yellow wellingtons", false ,false, false);
      ArrayList<Item> entrywayContents=new ArrayList<>();
          entrywayContents.add(boots);
      
      Location entryWay=new Location("Entryway: ", "You are in the foyer. Two pairs of boots sit next to the door. There is a grand staircase. There are doorways to the west and south.", entrywayContents,false,true,true,false,true,false);
      
      //kitchen room
      Item fridge=new Item("fridge","a refrigerator", true ,false, false);
      Item cabinet=new Item("cabinet","a short wooden cabinet painted blue", true, false, false);
      Item cake=new Item("cake","a tasty piece of chocolate cake",false, true, true);
      ArrayList<Item> kitchenContents=new ArrayList<>();
      kitchenContents.add(fridge);
      kitchenContents.add(cabinet);
      kitchenContents.add(cake);
      
      Location kitchen=new Location("Kitchen: ", "You are in the kitchen. The floor is cold tiling. There are two cabinets on the west wall and a refrigerator on the south wall. There is a brick fireplace on the east wall. There is a small door to the north.", kitchenContents,false,false,false,true,false,false);
      
      //put ground floor together
      FloorPlan map=new FloorPlan(1, 0, 0);
      Point3D p=new Point3D(1, 0, 0);
      map.addRoom(p, livingRoom);
      Point3D p2 =p.add(-1,1,0);
      map.addRoom(p2, diningRoom);
      Point3D p3=p.add(0,1,0);
      map.addRoom(p3, entryWay);
      Point3D p4=p.add(-1,0,0);
      map.addRoom(p4, kitchen);
      Character you=new Character(map);
      //current.getContents();
      //map.getCurrentRoom().getDecription();
      
      System.out.println("what do you want to do?");
      Scanner sc = new Scanner(System.in);
      
      for(int i=0;i<5;i++){
        String command=sc.nextLine();
        Parser talk= new Parser(you,command);
        System.out.println(talk.response());
      }
      
  }
}
