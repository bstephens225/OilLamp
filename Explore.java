/** main
 * constructs building
 * runs commands
@author bethany stephens
*/

import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Point3D;


public class Explore {
    
    public static void main(String[] arguments) {
      boolean gameover=false;
      //living room
      Item sofa=new Item("sofa","an old green velvet sofa", false ,false, false);
      Item chair=new Item("green chair","an old green velvet chair which matches the sofa", false, false, false);
      Item coffeeTable=new Item("coffee table","a short wooden coffee table",false, false, true);
      ArrayList<Item> livingRoomContents=new ArrayList<>();
          livingRoomContents.add(sofa);
          livingRoomContents.add(chair);
          livingRoomContents.add(coffeeTable);
      
      Location livingRoom=new Location("Living Room: ", "You are in a room with faded, peeling wallpaper. On the north wall is a doorway. On the south wall is an empty brick fireplace.", livingRoomContents, true, false, false, false, false, false);

      //dining room
      Item diningTable=new Item("table","a large rectangular table", false ,false, false);
      Item plate=new Item("plate","blue and white ceramic plates", false, true, false);
      Item woodChair=new Item("chair","normal brown chairs",false, false, true);
      ArrayList<Item> diningRoomContents=new ArrayList<>();
          diningRoomContents.add(diningTable);
          diningRoomContents.add(woodChair);
          diningRoomContents.add(plate);
      
      Location diningRoom=new Location("Dining Room: ", "You are in a dining room. There is a curtain hiding a door to the south. To the east is a doorway.", diningRoomContents,false,true,false, true, false, false);
      
      //entryway room
      Item boots=new Item("boots","a pair of yellow wellingtons", false ,false, false);
      ArrayList<Item> entrywayContents=new ArrayList<>();
          entrywayContents.add(boots);
      
      Location entryWay=new Location("Entryway: ", "You are in the foyer. There is a grand staircase. There are doorways to the west and south.", entrywayContents,false,true,true,false,true,false);
      
      //kitchen room
      Item cake=new Item("cake","a tasty piece of chocolate cake",false, true, true);
      Item fridge=new Item("refrigerator","a refrigerator", cake, true ,false, false);
      Item cabinet=new Item("cabinet","a short wooden cabinet painted blue", true, false, false);
      ArrayList<Item> kitchenContents=new ArrayList<>();
      kitchenContents.add(fridge);
      kitchenContents.add(cabinet);
      //kitchenContents.add(cake);
      
      Location kitchen=new Location("Kitchen: ", "You are in the kitchen. The floor is cold tiling. There are two cabinets on the west wall and a refrigerator on the south wall. There is a brick fireplace on the east wall. There is a small door to the north.", kitchenContents,true,false,false,true,false,false);

      //hall
      ArrayList<Item> hallContents=new ArrayList<>();
      Location hall=new Location("Hall: ", "You are in an upstairs hallway. There are doors to the south and west. There are stairs leading down.", hallContents, false, true, true, false, false, true);

      //master bedroom
      Item clock=new Item("clock","a tall wooden grandfather clock", true ,false, false);
      Item box=new Item("box","a small brass box", true, false, false);
      Item stand=new Item("nightstand","a curved black nightstand",false, true, true);
      ArrayList<Item> masterContents=new ArrayList<>();
      masterContents.add(clock);
      masterContents.add(box);
      masterContents.add(stand);
      
      Location masterBed=new Location("Master Bedroom: ", "You are in a bedroom with one large bed. There are wooden doors to the north, south, and east.", masterContents,true,true,false,true,false,false);
      
      //kids bedroom
      Item doll=new Item("doll","a cloth doll with yarn hair", false ,true, true);
      Item horse=new Item("rocking horse","a wooden rocking horse", false, false, true);
      Item twinBed=new Item("twin bed","two twin beds with light blue sheets",false, true, true);
      ArrayList<Item> kidsContents=new ArrayList<>();
      kidsContents.add(doll);
      kidsContents.add(horse);
      kidsContents.add(twinBed);
      
      Location kidBedroom=new Location("Children's Bedroom: ", "You are in a bedroom with two twin beds. There are doors to the north and west.",kidsContents,true,false,true,false,false,false);

      //bathroom
      Item sink=new Item("basin","a counter and a metal basin", false ,false, false);
      Item tub=new Item("tub","a large claw-footed ceramic tub", false, false, false);
      Item perfume=new Item("perfume","a medium bottle of rosy smelling perfume",false, true, true);
      ArrayList<Item> bathContents=new ArrayList<>();
      bathContents.add(sink);
      bathContents.add(tub);
      bathContents.add(perfume);
      
      Location bathroom=new Location("Bathroom: ", "You are in a small bathroom. There are doors to the north and east.",bathContents,true,false,false,true,false,false);
      
      //office
      Item locket=new Item("locket","a small gold locket containing a picture of a peaceful woman", true, false, false);
      Item desk=new Item("desk","a long wooden desk",locket, true ,false, false);
      Item notebook=new Item("notebook","a notebook with a dark red binding",false, true, true);
      ArrayList<Item> officeContents=new ArrayList<>();
      officeContents.add(desk);
      //officeContents.add(locket);
      officeContents.add(notebook);
      
      Location office=new Location("Office: ", "You are in an office. The wall has a portrait of two girls. To the south is a wooden door.",officeContents,false,true,false,false,false,false);

      //put house together
      FloorPlan map=new FloorPlan(1, 0, 0);
      Point3D p=new Point3D(0, 0, 0);
      Point3D p1=p.add(1,0,0);
      map.addRoom(p1, livingRoom);//living
      Point3D p2 =p.add(0,1,0);
      map.addRoom(p2, diningRoom);//dining
      Point3D p3=p.add(1,1,0);
      map.addRoom(p3, entryWay);//entry
      Point3D p4=p.add(0,0,0);
      map.addRoom(p4, kitchen);//kitchen
      Point3D p5=p.add(1,1,1);
      map.addRoom(p5, hall);//hall
      Point3D p6=p.add(0,1,1);
      map.addRoom(p6, masterBed);//master bedroom
      Point3D p7=p.add(1,0,1);
      map.addRoom(p7, kidBedroom);//kids bedroom
      Point3D p8=p.add(0,0,1);
      map.addRoom(p8, bathroom);//bathroom
      Point3D p9=p.add(0,2,1);
      map.addRoom(p9, office);//office

      //make player character
      Character you=new Character(map);
      

     
      new GUI(you);
      
  }
}
