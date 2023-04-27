/** Character class
@author bethany stephens
*/

import java.util.ArrayList;
import javafx.geometry.Point3D;


public class Character {
    //character has a location in a floor plan and an inventory
    Location location;
    private FloorPlan map=new FloorPlan(1,0,0);
    public ArrayList<Item> inventory= new ArrayList<Item>();
    
    /** constructer
    @param FloorPlan map of house
    */
    public Character(FloorPlan map){
        this.map=map;
        this.location=map.getCurrentRoom();
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
    */
    public void printInventory(){
        System.out.println("Current Inventory:");
        for (int i=0;i<inventory.size();i++){
            System.out.println(inventory.get(i).getName());
        }
        
    }

    /** 
     * @param Item item to be picked up
    */
    public void grab(Item item){
        inventory.add(item);
        
        lastAction.add("grab");
        lastAction.add(item.getName());
    }
    
    /** 
     * @param Item item to be dropped
    */
    public String drop(Item item){
        if(inventory.contains(item)==true){
            inventory.remove(item);
            
            lastAction.add("drop");
            lastAction.add(item.getName());
            return item+"dropped";
        }else{
            throw new RuntimeException("you arent carrying this item");
        }
    }

    /** 
     * @param Item item to be used
    */
    public void use(Item item){
        lastAction.add("use");
        lastAction.add(item.getName());
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
     * @thow RuntimeException if you can't walk that way
    */
    public void walk(String direction){
        if (location.canExit(direction)){
            changeLocation(direction);
        }else{
            throw new RuntimeException("you cannot go that way");
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