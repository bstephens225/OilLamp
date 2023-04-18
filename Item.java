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

    public Item(String name,String description, boolean openable, boolean breakable, boolean flammable){
        this.name=name;
        this.description=description;
        this.flammable=flammable;
        this.breakable=breakable;
        this.openable=openable;

    }

    public String getName(){
        return name;
    }

    public String getDecription(){
        return description;
    }

    public void addendum(String s){
        description=description+s;
    }

    public String burnIt(){
        if (flammable==true){
            if(burnt==false){
                burnt=true;
                addendum(" now burnt");
                return "you burnt "+name;
            }else{
                return name+" is already burnt";
            }
            
        }
        return "you cannot burn "+name;
    }

    public String breakIt(){
        if (breakable==true){
            if(broken==false){
                broken=true;
                addendum(" now broken in pieces");
                return "you broke "+name;
            }else{
                return name+" is already broken";
            }
            
        }
        return "you cannot break "+name;
    }

    public String openIt(){
        if (openable==true){
            if(open==false){
                open=true;
                return "you opened "+name;
            }else{
                return name+" is already opened";
            }
            
        }
        return "you cannot open "+name;
    }

    public String closeIt(){
        if (openable==true){
            if(open==true){
                open=false;
                return "you closed "+name;
            }else{
                return name+" is already closed";
            }
            
        }
        return "you cannot close "+name;
    }
}