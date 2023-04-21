import java.util.Hashtable;
import javafx.geometry.Point3D;

public class FloorPlan {
    Hashtable<Point3D,Location> rooms=new Hashtable<>();
    Location currentLoc;
    Point3D currentCoor; 

    public FloorPlan(int x, int y, int z){
        Point3D currentCoor = new Point3D(x, y, z);
        this.currentCoor=currentCoor;
        
    }
    public void printRooms(){
        System.out.println(rooms);
    }
    public void addRoom(Point3D p, Location room){
        rooms.put(p,room);
    }

    public Location getARoom(int x, int y, int z){
        Point3D coordinates = new Point3D(x, y, z);
        currentCoor=coordinates;
        currentLoc=rooms.get(currentCoor);
        return rooms.get(coordinates);
    }

    public Location getARoom(Point3D p){
        currentCoor=p;
        currentLoc=rooms.get(currentCoor);
        return rooms.get(p);
    }

    public Location getCurrentRoom(){
        currentLoc=rooms.get(currentCoor);
        return currentLoc;
    }

    public Point3D getCurrentCoor(){
        return currentCoor;
    }
    
}
