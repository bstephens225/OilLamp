
/** Lamp class
@author bethany stephens
*/
public class Lamp extends Item{
    //int brightness, not used in the end
    int brightness=255;

    //lamp constructor 
    public Lamp(){
        super("lamp","a small oil lamp",false,true,false);
    }

    /** breaking the lamp
     * @throws RuntimeException that ends the game
    */
    public void breakIt(){
        throw new RuntimeException("gameover");
    }

    /** dims the lamp, not used in the end
     * @throws RuntimeException that ends the game
    */
    public int dim(){
        brightness-=5;
        if (brightness==0){
            throw new RuntimeException("gameover");
        }
        return brightness;
    }

}
