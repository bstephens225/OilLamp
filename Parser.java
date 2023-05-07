/** Parser class
 * read input and respond
 * move character and items as appropriate
@author bethany stephens
*/

import java.util.ArrayList;

public class Parser {
    //make item to be mentioned
    private  Item item;

    //store current location of character
    private  Location current;

    //store string to be parsed
    String command;

    //list of items to be checked for an item mentioned
    ArrayList<Item> itemz;

    //store floorplan character is in
    FloorPlan map;

    //store character
    Character you;

    //store whether item in the room or in the inventory has been mentioned
    boolean roomItem=false;
    boolean yourItem=false;

    //store boolean for if game is over
    boolean gameover=false;

    /** constructor
    @param Character player
    @param String command player gives
    */
    public Parser(Character you,String command){
        this.you=you;
        map=you.getFloorPlan();
        current=you.getLoc();
        //System.out.println(current.getContents());
        itemz=current.getContents();
        this.command=command;
        
    }

    /** prints inventory
     * @return String list of item names and descriptions in inventory
    */
    public String printInventory(){
        String invent= "---INVENTORY---";
        for (int i=0;i<you.getInventory().size();i++){
            invent+="\n"+you.getInventory().get(i).getName()+": "+you.getInventory().get(i).getDecription();
        }
        return invent;
    }

    /** looks for action words and items in the command
     * @return String response to the request
     * @throws RuntimeException if game is over
    */
    public String response(){
        //check command for movement words 
        if (command.contains("walk")||command.contains("go")||command.contains("exit")||command.contains("move")){
            try{
                updateLocation();
                return you.getLoc().getName()+you.getLoc().getDecription();
            }catch(Exception e){
                return(e.getMessage()); 
            }
        }
        //check command for printing inventory
        if (command.contains("print")&&command.contains("inventory")){
            return printInventory()+"\n---------------";
        }

        //check command for items in inventory
        for (int i=0;i<you.getInventory().size();i++){
            //System.out.println(you.getInventory().get(i).getName());
            if(command.contains(you.getInventory().get(i).getName())){
                item=you.getInventory().get(i);//item  is one mentioned in command
                yourItem=true;
            }
        }
        //check command for items in room
        for (int i=0;i<itemz.size();i++){
            //System.out.println(itemz.get(i).getName());
            if(command.contains(itemz.get(i).getName())){
                item=itemz.get(i);//item  is one mentioned in command
                roomItem=true;
            }
        }
        //if command says take and then an item in the room, grab that item and remove it from the room's contents
        if (command.contains("take")||command.contains("grab")||command.contains("pick up")){
                if(roomItem){
                    you.grab(item);
                    map.getCurrentRoom().removeItem(item);
                    return "you add "+item.getName()+" to your inventory";
                }
                if(yourItem){
                    return "you already picked up that item";
                }
            return "that item is not here";
            
        }
        //if command says drop and an item in inventory, drop that item and add it to current room's contents
        if (command.contains("remove")||command.contains("drop")){
            if(yourItem){
                if(item.getName()=="lamp"){
                    throw new RuntimeException("gameover");
                }
                you.drop(item);
                map.getCurrentRoom().addItem(item);
                return "you dropped "+item.getName();
            }
            return "that item is not in your inventory";
        }
        //if command says inspect
        if (command.contains("inspect")||command.contains("look")){
            if(roomItem||yourItem){
                return item.getDecription();
            }
            if(command.contains("room")||command.contains("around")){
                return current.getDecription();
            }
        return "that item is not here";
        
        }
        //you cant' eat
        if (command.contains("eat")||command.contains("drink")){
            return "you are not corporeal enough to eat or drink. try something else.";
        }
        //you cant sleep
        if (command.contains("rest")||command.contains("sleep")){
            return "you are not corporeal enough to sleep. try something else.";
        }
        //you cant sit
        if (command.contains("sit")){
            return "you are not corporeal enough to sit down. try something else.";
        }
        //break an item
        if (command.contains("break")){
            if(yourItem||roomItem){
                try{
                    item.breakIt();
                    //put broken item in room description
                    return item.getName()+ " is broken";
                }catch(Exception e){
                    if(e.getMessage()=="gameover"){
                        throw new RuntimeException("gameover");
                    }
                    return(e.getMessage());
                }
            }
        }
        //burn an item
        if (command.contains("burn")){
            
            if(yourItem||roomItem){
                try{item.burnIt();
                    //put burnt item in room description
                    return item.getName()+ " is burnt";
                }catch(Exception e){
                    return(e.getMessage());
                }
                
            }
        }
        //open an item
        if (command.contains("open")){
            if(yourItem||roomItem){
                try{
                    Item newItem=item.openIt();
                    you.grab(newItem);
                    return item.getName()+ " is opened, you found "+newItem.getName();
                }catch(Exception e){
                    return(e.getMessage());
                }
                //put opened item in room description
                
            }
        }
        //close an item
        if (command.contains("close")||command.contains("shut")){
            if(yourItem||roomItem){
                try{item.closeIt();
                    //put burnt item in room description
                    return item.getName()+ " is closed";
                }catch(Exception e){
                    return(e.getMessage());
                }
            }
        }
        //close an item
        if (command.contains("unlock")){
            if(you.getLocName()=="Master Bedroom: "){
                try{
                    you.unlock();
                    return "you unlock the door";
                }catch(Exception e){
                    return(e.getMessage());
                }
            }else{
                return "this room doesn't have any locked doors";
            }
        }
        //return for no action words found
        return "request not understood";

    }
    
    /** try walking and return error if you cant go that way or if it is not a direction
     * @throws RuntimeException if that direction does not exist
     * @throws RuntimeException if you can't go that way
    */
    public void updateLocation(){
        if(command.contains("north")){
            try{
                you.walk("north");
            }catch(Exception e){
                throw new RuntimeException(e.getMessage()); 
            }
        }else if(command.contains("south")){
            try{
                you.walk("south");
            }catch(Exception e){
                throw new RuntimeException(e.getMessage()); 
            }
        }else if(command.contains("east")){
            try{
                you.walk("east");
            }catch(Exception e){
                throw new RuntimeException(e.getMessage()); 
            }
        }else if(command.contains("west")){
            try{
                you.walk("west");
            }catch(Exception e){
                throw new RuntimeException(e.getMessage()); 
            }
        }else if(command.contains("up")){
            try{
                you.walk("up");
            }catch(Exception e){
                throw new RuntimeException(e.getMessage()); 
            }
        }else if(command.contains("down")){
            try{
                you.walk("down");
            }catch(Exception e){
                throw new RuntimeException(e.getMessage()); 
            }
        }else{
            throw new RuntimeException("that is not a direction");
        }
        //change current location
    }

    /** describe the room you're in
     * @return String room description of current location
    */
    public String describe(){
        return you.getLocName()+you.getLoc().getDecription();
    }

}
