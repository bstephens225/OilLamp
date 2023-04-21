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
    
    public Parser(Character you){
        this.you=you;
        map=you.getFloorPlan();
        current=you.getLoc();
        //System.out.println(current.getContents());
        itemz=current.getContents();
        command=sc.nextLine();
        
    }

    public String response(){
        //check command for items in inventory
        for (int i=0;i<you.getInventory().size();i++){
            if(command.contains(you.getInventory().get(i).getName())){
                item=you.getInventory().get(i);//item  is one mentioned in command
            }
        }
        //check command for items in room
        for (int i=0;i<itemz.size()-1;i++){
            if(command.contains(itemz.get(i).getName())){
                item=itemz.get(i);//item  is one mentioned in command
            }
        }
        if (command.contains("walk")||command.contains("go")||command.contains("exit")||command.contains("move")){
            updateLocation();
            return you.getLoc().getName()+you.getLoc().getDecription();
        }
        if (command.contains("take")||command.contains("grab")){
            addInvent();
            return "you add "+item+" to your inventory";
        }
        if (command.contains("remove")||command.contains("drop")){
            dropInvent();
            return "you dropped "+item+" from your inventory";
        }
        if (command.contains("break")){
            item.breakIt();
            //put brokn item in room description
            return "";
        }
        if (command.contains("burn")){
            item.burnIt();
            //put burnt item in room description
            return "";
        }
        if (command.contains("open")){
            item.openIt();
            //put opened item in room description
            return "";
        }
        if (command.contains("close")||command.contains("shut")){
            item.closeIt();
            //put closed item in room description
            return "";
        }
        return "request not understood";

    }

    public void updateLocation(){
        if(command.contains("north")){
            you.walk("north");
        }else if(command.contains("south")){
            you.walk("south");
        }else if(command.contains("east")){
            you.walk("east");
        }else if(command.contains("west")){
            you.walk("west");
        }else if(command.contains("up")){
            you.walk("up");
        }else if(command.contains("down")){
            you.walk("down");
        }
        //change current location
    }

    public void addInvent(){
        for (int i=0;i<itemz.size();i++){
            if(command.contains(itemz.get(i).getName())){
                you.grab(itemz.get(i));
                item= itemz.get(i);
            }
        }
    }
    public void dropInvent(){
        for (int i=0;i<you.getInventory().size();i++){
            if(command.contains(you.getInventory().get(i).getName())){
                you.drop(you.getInventory().get(i));
                item=you.getInventory().get(i);
            }
        }
    }

    public String describe(){
        return "room description";
    }

}
