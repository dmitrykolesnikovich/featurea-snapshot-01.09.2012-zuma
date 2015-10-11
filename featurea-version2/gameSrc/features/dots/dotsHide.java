package features.dots;

import type.Level;
import featurea.Feature;
import featurea.game;
import static featurea.game.touch;
import featurea.Touch.Type;

public class dotsHide extends Feature {

    @Override
    public boolean check() {
    	// Assume:
        if (game.play==null){return false;}
        if (touch==null){return false;}
        Level level = (Level)game.play;
        if (!level.hasDots()){return false;}        
        // Check:
        return touch.type == Type.UP || touch.type == Type.MOVE;
    }

    @Override
    public void make() {    	
    	Level level = (Level)game.play;	
    	if (level.hasDots()){
            level.clearDots();
    	}    	
    }
    
}