import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Point3D;

public class Parser {
     Scanner sc = new Scanner(System.in);
    boolean gameover=false;
    private  Item item;
    private  Location current;
    String command;
    ArrayList<Item> itemz;
    FloorPlan map;
    Character you;
    boolean useItem=false;
    public Parser(Character you,String command){
        this.you=you;
        map=you.getFloorPlan();
        current=you.getLoc();
        //System.out.println(current.getContents());
        itemz=current.getContents();
        this.command=command;
        
    }

    public void printInventory(){
        System.out.println(you.inventory);
    }
    public String response(){
        //check command for action words
        if (command.contains("walk")||command.contains("go")||command.contains("exit")||command.contains("move")){
            try{
                updateLocation();
                return you.getLoc().getName()+you.getLoc().getDecription();
            }catch(Exception e){
                return(e.getMessage()); 
            }
            //you.getLoc().getName();
        }
        if (command.contains("print")&&command.contains("inventory")){
            printInventory();
            
            return "printed";
        }

        //use/pick up/put down item
        //check command for items in inventory
        for (int i=0;i<you.getInventory().size();i++){
            //System.out.println(you.getInventory().get(i).getName());
            if(command.contains(you.getInventory().get(i).getName())){
                item=you.getInventory().get(i);//item  is one mentioned in command
                useItem=true;
            }
        }
        //check command for items in room
        for (int i=0;i<itemz.size();i++){
            //System.out.println(itemz.get(i).getName());
            if(command.contains(itemz.get(i).getName())){
                item=itemz.get(i);//item  is one mentioned in command
                useItem=true;
            }
        }
        if (command.contains("take")||command.contains("grab")){
                if(useItem){
                    you.grab(item);
                    return "you add "+item.getName()+" to your inventory";
                }
            return "that item is not here";
            
        }
        if (command.contains("remove")||command.contains("drop")){
            if(useItem){
                you.drop(item);
                return "you dropped "+item.getName();
            }
            return "that item is not in your inventory";
        }
        if (command.contains("break")){
            if(useItem){
                item.breakIt();
                //put brokn item in room description
                return item.getName()+" is broken";
            }
        }
        if (command.contains("burn")){
            if(useItem){
                item.burnIt();
                //put burnt item in room description
                return item.getName()+ " is burnt";
            }
        }
        if (command.contains("open")){
            if(useItem){
                item.openIt();
                //put opened item in room description
                return item.getName()+ " is opened";
            }
        }
        if (command.contains("close")||command.contains("shut")){
            if(useItem){
                item.closeIt();
                //put closed item in room description
                return item.getName()+" is closed";
            }
        }
        return "request not understood";

    }

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

    public String describe(){
        return "room description";
    }

}
