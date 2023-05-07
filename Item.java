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

    /** Item class
     * @param String name of the item
     * @param String short description of the item
     * @param boolean whether the item can be opened
     * @param boolean whether the item can be broken
     * @param boolean whether the item can be burnt
    */
    public Item(String name,String description, boolean openable, boolean breakable, boolean flammable){
        this.name=name;
        this.description=description;
        this.flammable=flammable;
        this.breakable=breakable;
        this.openable=openable;

    }
    /** Item class
     * @param String name of the item
     * @param String short description of the item
     * @param Item that is inside the item
     * @param boolean whether the item can be opened
     * @param boolean whether the item can be broken
     * @param boolean whether the item can be burnt
    */
    public Item(String name,String description, Item contents,boolean openable, boolean breakable, boolean flammable){
        this.name=name;
        this.description=description;
        this.flammable=flammable;
        this.breakable=breakable;
        this.openable=openable;
        this.contents=contents;
    }

    /** 
     * @return String name of the item
    */
    public String getName(){
        return name;
    }

    /** 
     * @return String description of the item
    */
    public String getDecription(){
        if (open==true){
            return description+ ", now open";
        }else if (shut==true){
            return description+", now shut";
        }
        return description;
    }

    /** 
     * @param String add some string to the description of the item
    */
    public void addendum(String s){
        description=description+s;
    }

    /** burn item and change description
     * @throws RuntimeException if item is already burnt
     * @throws RuntimeException if item is not flammable
    */
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

    /** break item and change description
     * @throws RuntimeException if item is already broken
     * @throws RuntimeException if item is unbreakable
    */
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

    /** 
     * @return Item contents
     * @throws RuntimeException if item is empty 
     * @throws RuntimeException if item is already open
    */
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
    
    /** close item and change description
     * @throws RuntimeException if item is already closed
     * @throws RuntimeException if item is unable to be closed
    */
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
