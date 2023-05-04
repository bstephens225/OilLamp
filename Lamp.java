public class Lamp extends Item{
    int brightness=255;
    public Lamp(){
        super("lamp","a small oil lamp",false,true,false);
    }

    public void breakIt(){
        throw new RuntimeException("gameover");
    }
    public int dim(){
        brightness-=5;
        if (brightness==0){
            throw new RuntimeException("gameover");
        }
        return brightness;
    }

}
