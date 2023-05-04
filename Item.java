import javax.management.RuntimeErrorException;

/** Item class
@author bethany stephens
*/

public class Item {
    //name
    String name;

    //description
    String description;

    //properties
    boolean flammable;
    boolean breakable;
    boolean openable;

    //current
    boolean burnt=false;
    boolean broken=false;
    boolean open=false;
    boolean shut=false;
    Item contents=null;

    public Item(String name,String description, boolean openable, boolean breakable, boolean flammable){
        this.name=name;
        this.description=description;
        this.flammable=flammable;
        this.breakable=breakable;
        this.openable=openable;

    }

    public Item(String name,String description, Item contents,boolean openable, boolean breakable, boolean flammable){
        this.name=name;
        this.description=description;
        this.flammable=flammable;
        this.breakable=breakable;
        this.openable=openable;
        this.contents=contents;
    }

    public String getName(){
        return name;
    }

    public String getDecription(){
        if (open==true){
            return description+ ", now open";
        }else if (shut==true){
            return description+", now shut";
        }
        return description;
    }

    public void addendum(String s){
        description=description+s;
    }

    public void burnIt(){
        if(name=="perfume"){
            if(broken==true){
                throw new RuntimeException ("The house burns. Light is everywhere. You must go to it.");
            }else{
                throw new RuntimeException ("You cannot burn it in the bottle. Break it.");
            }
        }
        if (flammable==true){
            if(burnt==false){
                burnt=true;
                addendum(", now burnt");
            }else{
                throw new RuntimeException (name+" is already burnt");
            }
            
        }else{
            throw new RuntimeException ("you cannot burn "+name);
        }
        
    }

    public void breakIt(){
        if (breakable==true){
            if(broken==false){
                broken=true;
                addendum(", now broken");
            }else{
                throw new RuntimeException (name+" is already broken");
            }
            
        }else{
            throw new RuntimeException ("you cannot break "+name);
        }
    }

    public Item openIt(){
         if (openable==true){
            if(open==false){
                open=true;
                //addendum(", now opened");
                shut=false;
                if (contents!=null){
                    return contents;
                }else{
                    throw new RuntimeException(name+" is open and empty");
                }
                
            }else{
                throw new RuntimeException (name+" is already open");
            }
            
        }else{
            throw new RuntimeException ("you cannot open "+name);
        }
    }

    public void closeIt(){
        if (openable==true){
            if(open==true){
                open=false;
                shut=true;
            }else{
                throw new RuntimeException (name+" is already shut");
            }
            
        }else{
            throw new RuntimeException ("you cannot close "+name);
        }
    }

    
}
