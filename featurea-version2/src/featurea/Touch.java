package featurea;

import static featurea.device.display;

public class Touch{    	
    public float x;
    public float y;    
    
    public static enum Type{
    	DOWN, MOVE, UP
    }
    public Type type;    
    
    public boolean cross (Animation animation){
        Sprite1 sprite = animation.sprite1();
        if (sprite==null){return false;}
        double w = sprite.width / display.density();
        double h = sprite.height / display.density();
        double top = animation.vector.dy() - h/2; 
        double bottom = animation.vector.dy() + h/2;
        double left = animation.vector.dx() - w/2; 
        double right = animation.vector.dx() + w/2;
        return x>left && x<right && y>top && y<bottom;
    }
    
    /*package*/ boolean onScreen = false;
    
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    } 
    // TODO: make 'DEFAULT_DELAY_CONTROL_HAS_PUSHED' and 'delayControlHasPushed' package visibility
    // public static final long DEFAULT_DELAY_CONTROL_HAS_PUSHED = 500; // milliseconds
    // public static long lastTimeControlHasBeenUp = 0; // milliseconds
}