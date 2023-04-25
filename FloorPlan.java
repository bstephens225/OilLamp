/** FloorPlan class
@author bethany stephens
*/
import java.util.Hashtable;
import javafx.geometry.Point3D;

public class FloorPlan {
    Hashtable<Point3D,Location> rooms=new Hashtable<>();
    Location currentLoc;
    Point3D currentCoor; 

    /** construct
     * @param Point3d coordinates of current loc
    */
    public FloorPlan(int x, int y, int z){
        Point3D currentCoor = new Point3D(x, y, z);
        this.currentCoor=currentCoor;
        
    }

    //print list of rooms
    public void printRooms(){
        System.out.println(rooms);
    }

    /** 
    @param Point3D point where room is added
    @param Location room to be added at point
    */
    public void addRoom(Point3D p, Location room){
        rooms.put(p,room);
    }
    /** 
     * @param int x coord
     * @param int y coord
     * @param int z coord
     * @return Location room with those coord
    */
    public Location getARoom(int x, int y, int z){
        Point3D coordinates = new Point3D(x, y, z);
        currentCoor=coordinates;
        currentLoc=rooms.get(currentCoor);
        return rooms.get(coordinates);
    }

    /** 
     * @param Point3D coordinates
     * @return Location room with those coord
    */
    public Location getARoom(Point3D p){
        currentCoor=p;
        currentLoc=rooms.get(currentCoor);
        return rooms.get(p);
    }
    /** 
     * @return Location current room 
    */
    public Location getCurrentRoom(){
        currentLoc=rooms.get(currentCoor);
        return currentLoc;
    }
    /** 
     * @return Point3D coord of current room
    */
    public Point3D getCurrentCoor(){
        return currentCoor;
    }
    
}
