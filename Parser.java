/** Parser class
 * read input and respond
 * move character and items as appropriate
@author bethany stephens
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
     //Scanner sc = new Scanner(System.in);
    boolean gameover=false;
    private  Item item;
    private  Location current;
    String command;
    ArrayList<Item> itemz;
    FloorPlan map;
    Character you;
    boolean roomItem=false;
    boolean yourItem=false;

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
    */
    public String printInventory(){
        String invent= "---INVENTORY---";
        for (int i=0;i<you.getInventory().size();i++){
            invent+="\n"+you.getInventory().get(i).getName()+": "+you.getInventory().get(i).getDecription();
        }
        return invent;
    }

    /**glitch */
    public void glitch(){
        double random=Math.random();
        if(random<.25){
            you.dimLamp();
        }else if(random<.5){
            //add item to inventory
        }else if(random<.75){
            //add item to room
        }else{
            //
        }

    }
    /** looks for action words and items in the command
     * @return String response to the request
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
        if (command.contains("take")||command.contains("grab")){
                if(roomItem){
                    you.grab(item);
                    map.getCurrentRoom().removeItem(item);
                    return "you add "+item.getName()+" to your inventory";
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
        //you cant' eat
        if (command.contains("eat")){
            return "you are not corporeal enough to eat or drink. try something else.";
        }
        //you cant sleep
        if (command.contains("rest")||command.contains("sleep")){
            return "you are not corporeal enough to sleep. try something else.";
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
        //return for no action words found
        return "request not understood";

    }
    //try walking and return error if you cant go that way or if it is not a direction
    public void updateLocation(){
        if(command.contains("north")){
            try{
                you.walk("north");
            }catch(Exception e){
                System.err.println(e.getMessage()); 
            }
        }else if(command.contains("south")){
            try{
                you.walk("south");
            }catch(Exception e){
                System.err.println(e.getMessage()); 
            }
        }else if(command.contains("east")){
            try{
                you.walk("east");
            }catch(Exception e){
                System.err.println(e.getMessage()); 
            }
        }else if(command.contains("west")){
            try{
                you.walk("west");
            }catch(Exception e){
                System.err.println(e.getMessage()); 
            }
        }else if(command.contains("up")){
            try{
                you.walk("up");
            }catch(Exception e){
                System.err.println(e.getMessage()); 
            }
        }else if(command.contains("down")){
            try{
                you.walk("down");
            }catch(Exception e){
                System.err.println(e.getMessage()); 
            }
        }else{
            throw new RuntimeException("that is not a direction");
        }
        //change current location
    }

    //describe the room you are in
    public String describe(){
        return you.getLocName()+you.getLoc().getDecription();
    }

}
