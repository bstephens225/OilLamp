/** Location class
@author bethany stephens


Stores place name
Stores place description
Has inventory
has exits
*/
import java.util.ArrayList;
import java.util.Hashtable;

import javax.management.RuntimeErrorException;

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

    public String getName(){
        return name;
    }
    public ArrayList<Item> getContents(){
        return contents;
    }
    public String getDecription(){
        return description;
    }

    public Hashtable<String,Boolean> getExits(){
        return exitKey;
    }

    public boolean canExit(String direction){
        if(exitKey.containsKey(direction)){
            return exitKey.get(direction);
        }else{
            throw new RuntimeException( "not a real direction");
        }
    }


}
