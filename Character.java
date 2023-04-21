/** Character class
@author bethany stephens
*/

import java.util.ArrayList;

import javafx.geometry.Point3D;


public class Character {
    //public ArrayList<String> inventory= new ArrayList<String>();
    boolean north = true;
    boolean south = true;
    public Location location;
    private FloorPlan map=new FloorPlan(1,0,0);
    public Integer height=5;
    public Integer health=10;
    public ArrayList<Item> inventory= new ArrayList<Item>();

    public ArrayList<String> lastAction= new ArrayList<String>();
    
    public Character(FloorPlan map){
        this.map=map;
        location=map.getCurrentRoom();
    }
    
    public Location getLoc(){
        return location;
    }
    public FloorPlan getFloorPlan(){
        return map;
    }
    public String getLocName(){
        return location.getName();
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public void printInventory(){
        System.out.println("Current Inventory:");
        for (int i=0;i<inventory.size();i++){
            System.out.println(inventory.get(i).getName());
        }
        
    }

    public void printActions(){
        System.out.println(lastAction);
    }

    public void grab(Item item){
        inventory.add(item);
        
        lastAction.add("grab");
        lastAction.add(item.getName());
    }

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

    public void examine(Item item){
        System.out.println("this item is completely normal");
    }

    public void use(Item item){
        lastAction.add("use");
        lastAction.add(item.getName());
        
    }

    public void changeLocation(String direction){
        if (location.canExit(direction)){
            int x=0;
            int y=0;
            int z=0;
            if(direction=="north"){
                x++;
            }else if(direction=="south"){
                x--;
            }else if(direction=="east"){
                y++;
            }else if(direction=="west"){
                y--;
            }else if(direction=="up"){
                z++;
            }else if(direction=="down"){
                z--;
            }

            Point3D newCoor=map.getCurrentCoor().add(x,y,z);
            location=map.getARoom(newCoor);
        }
    }

    public boolean walk(String direction){
        if (location.canExit(direction)){
            changeLocation(direction);
            return true;
        }else{
            throw new RuntimeException("that's not a direction in this world");

        }
    }

    public boolean fly(int x, int y){
        //System.out.println("you can't fly silly! you're not a bird");
        return false;
    }

    public Number shrink(){
        height=height/2;
        
        lastAction.add("shrink");
        lastAction.add("null");
        return height;
    }


    public void undo(){
        int last=lastAction.size()-1;
        if (lastAction.get(last-1)=="walk"){
            if (lastAction.get(last)=="north"){
                removeIt(last);
                walk("south");
                removeIt(last);
            }else{
                removeIt(last);
                walk("north");
                removeIt(last);
            }

        }else if(lastAction.get(last-1)=="grab"){
            //drop(lastAction.get(last));
            removeIt(last);
            removeIt(last);
        }else if (lastAction.get(last-1)=="drop"){
            //grab(lastAction.get(last));
            removeIt(last);
            removeIt(last);
        }else if (lastAction.get(last-1)=="use"){
            removeIt(last);
        }else if (lastAction.get(last-1)=="rest"){
            if(lastAction.get(last-1)=="yay"){
                health--;
            }
            removeIt(last);
        }

    }

    private void removeIt(int last){
        lastAction.remove(last);
        lastAction.remove(last-1);
    }


    public static void main(String[] args) {

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
        
    }
    
}