import java.util.ArrayList;

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
      
      Location livingRoom=new Location("Living Room", "You are in a room with faded, peeling wallpaper. There is a green sofa, and a matching chair around a small wooden table in the center of the room. A large woven rug is underfoot. On the north wall is a doorway. On the south wall is an empty brick fireplace.", livingRoomContents, true, false, false, false, false, false);

      //dining room
      Item diningTable=new Item("sofa","an old green velvet sofa", false ,false, false);
      Item woodChair=new Item("green chair","an old green velvet chair which matches the sofa", false, false, false);
      Item plate=new Item("coffee table","a short wooden table",false, false, true);
      ArrayList<Item> diningRoomContents=new ArrayList<>();
          diningRoomContents.add(diningTable);
          diningRoomContents.add(woodChair);
          diningRoomContents.add(plate);
      
      Location diningRoom=new Location("Dining Room", "You are in a dining room. A long rectangular table stretches down the middle of the room. Nine chairs surround it and nine places are set. There is a curtain on the south wall. To the east is a doorway.", diningRoomContents,false,true,true, false, false, false);
      
      //entryway room
      Item boots=new Item("sofa","an old green velvet sofa", false ,false, false);
      ArrayList<Item> entrywayContents=new ArrayList<>();
          entrywayContents.add(boots);
      
      Location entryWay=new Location("Entryway", "You are in the foyer. To the east is a door with a brass doorknob. Two pairs of boots sit next to the door. To the north is a grand staircase. There are doorways to the west and south.", entrywayContents,false,true,false,true,true,false);
      
      //put ground floor together
      FloorPlan map=new FloorPlan(1, 0, 0);
      Point3D p=new Point3D(1, 0, 0);
      map.addRoom(p, livingRoom);
      Point3D p2 =p.add(-1,1,0);
      map.addRoom(p2, diningRoom);
      Point3D p3=p.add(0,1,0);
      map.addRoom(p3, entryWay);
      Character you=new Character(map);
      //current.getContents();
      //map.getCurrentRoom().getDecription();
      
      System.out.println("what do you want to do?");
      Parser talk= new Parser(you);
      System.out.println(talk.response());
  }
}
