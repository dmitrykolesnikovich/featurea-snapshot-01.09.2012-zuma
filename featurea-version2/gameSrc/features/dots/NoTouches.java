package features.dots;

import type.Level;
import featurea.Feature;
import featurea.game;
import static featurea.game.touch;

public class NoTouches extends Feature {

    @Override
    public boolean check() {
    	// Assume:
        if (game.play==null){return false;}
        return touch == null;
    }

    @Override
    public void make() {    	
    	Level level = (Level)game.play;	
    	if (level.hasDots()){
            level.clearDots();
    	}    	
    }
    
}