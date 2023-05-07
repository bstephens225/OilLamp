/** Character class
@author bethany stephens
*/

import java.util.ArrayList;
import javafx.geometry.Point3D;


public class Character {
    //character has a location in a floor plan, an inventory, and a lamp
    Location location;
    private FloorPlan map=new FloorPlan(1,0,0);
    public ArrayList<Item> inventory= new ArrayList<Item>();
    Lamp lamp=new Lamp();

    /** constructer
    @param FloorPlan map of house
    */
    boolean lockedDoor=true;
    public Character(FloorPlan map){
        this.map=map;
        this.location=map.getCurrentRoom();
        
        grab(lamp);
    }
    public void dimLamp(){
        lamp.dim();
    }

    /** 
     * @return Location current loc of character
    */
    public Location getLoc(){
        return location;
    }
    /** 
     * @return FloorPlan map that character is in
    */
    public FloorPlan getFloorPlan(){
        return map;
    }

    /** 
     * @return String name of current loc of character
    */
    public String getLocName(){
        return location.getName();
    }
    /** constructer
     * @return ArrayList<Item> inventory of character
    */
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    /** 
     * print inventory of character
     * not used in game
    */
    public void printInventory(){
        System.out.println("Current Inventory:");
        for (int i=0;i<inventory.size();i++){
            System.out.println(inventory.get(i).getName());
        }
        
    }

    /** 
     * @param Item item to be picked up
     * @return String saying that item has been grabbed
    */
    public void grab(Item item){
        inventory.add(item);
    }
    
    /** 
     * @param Item item to be dropped
     * @return String saying that item has been dropped
     * @throws RuntimeException if you dont have the item you try to drop
    */
    public String drop(Item item){
        if(inventory.contains(item)==true){
            inventory.remove(item);
            return item+"dropped";
        }else{
            throw new RuntimeException("you aren't carrying this item");
        }
    }

    /** 
     * @return boolean whether player has the key
    */
    public boolean hasKey(){
        for (int i=0;i<inventory.size();i++){
            if (inventory.get(i).getName()=="key"){
                return true;
            }
        }
        return false;
    }

    /** 
     * unlock the office door
     * @throw RuntimeException if you dont have key or if you have aklready unlocked the door
    */
    public void unlock(){
        if(lockedDoor){
            if(hasKey()){
                lockedDoor=false;
            }else{
                throw new RuntimeException("you do not have the key");
            }
            
        }else{
            throw new RuntimeException("this door is not locked");
        }
        
    }

    /** 
     * @param String direction to go
    */
    public void changeLocation(String direction){
        if (location.canExit(direction)){
            int x=0;
            int y=0;
            int z=0;
            if(direction=="north"){
                y++;
            }else if(direction=="south"){
                y--;
            }else if(direction=="east"){
                x++;
            }else if(direction=="west"){
                x--;
            }else if(direction=="up"){
                z++;
            }else if(direction=="down"){
                z--;
            }
            Point3D newCoor=map.getCurrentCoor().add(x,y,z);
            location=map.getARoom(newCoor);
            
        }
    }
    /** 
     * @param String direction to walk
     * @throw RuntimeException if you can't walk that way
     * @throw RuntimeException if the door is locked
    */
    public void walk(String direction){
        if(location.getName()=="Master Bedroom: "&&lockedDoor&&direction=="north"){
            throw new RuntimeException("that door is locked");
        }else{
            if (location.canExit(direction)){
                changeLocation(direction);
            }else{
                throw new RuntimeException("you cannot go that way");
            }
        }
        
    }




    public static void main(String[] args) {
        /** 
        Character you= new Character();
        Item apple=new Item("apple","a red apple",false,false,false);
        you.use(apple);
        you.grab(apple);
        Item book=new Item("book","a blank notepad",false,true,true);
        you.grab(book);
        you.printInventory();
        you.drop(apple);
        //you.undo();
        you.printInventory();
        apple.burnIt();
        book.burnIt();
        System.out.println(book.getDecription());
        */
        
    }
    
}