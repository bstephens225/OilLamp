/** Location class
@author bethany stephens


Stores place name
Stores place description
Has inventory
has exits
*/
import java.util.ArrayList;
import java.util.Hashtable;

public class Location {

    //possible directions
    Hashtable<String,Boolean> exitKey=new Hashtable<>();

    //name
    String name;

    //description
    String description;

    //objects in the room
    public ArrayList<Item> contents= new ArrayList<Item>();

    public Location(String name, String description, ArrayList<Item> contents, boolean north, boolean south, boolean west, boolean east, boolean up,boolean down){
        this.name=name;
        this.description=description;
        this.contents=contents;
        
        exitKey.put("north",north);
        exitKey.put("south",south);
        exitKey.put("west",west);
        exitKey.put("east",east);
        exitKey.put("up",up);
        exitKey.put("down",down);
    }

    /** 
     * @return String name of location
    */
    public String getName(){
        return name;
    }

    /** 
     * @return ArrayList<Item> items in the location
    */
    public ArrayList<Item> getContents(){
        return contents;
    }

    /** 
     * @param Item item to be removed
    */
    public void removeItem(Item item){
        contents.remove(item);
    }

    /** 
     * @param Item item to be added
    */
    public void addItem(Item item){
        contents.add(item);
    }

    /** 
     * @return String description of the location
    */
    public String getDecription(){
        return description;
    }
    /** 
     * @return Hashtable<String,Boolean> possible exits of the room
    */
    public Hashtable<String,Boolean> getExits(){
        return exitKey;
    }

    /**
     * @param String direction to test 
     * @return boolean whether you can exit that way
     * @throws RuntimeException if its not even a direction
    */
    public boolean canExit(String direction){
        if(exitKey.containsKey(direction)){
            return exitKey.get(direction);
        }else{
            throw new RuntimeException( "not a real direction");
        }
    }


}
