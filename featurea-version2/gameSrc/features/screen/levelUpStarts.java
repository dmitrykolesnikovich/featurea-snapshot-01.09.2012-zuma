package features.screen;

import data.parameters;
import type.Level;
import featurea.Feature;
import featurea.game;
import game.navigation;
import game.settings;
import areas.Path;

public class levelUpStarts extends Feature {

    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play==null){return false;}
        Level level = (Level) (game.play);
        if (level.hasScoreupText()){return false;}
        Path[] paths = level.paths;
        for (Path path : paths) {
            if (!path.chains.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void make() {
    	//parameters.levelUpStarts = true;   	
    	// TODO: implement 'bonus scores' in second version of Featurea
    	// where 'gameplay' can have 'TextControl' objects and 'Animation' class has 'tick' method!    	
        parameters.levelUpStarts = false;        
        navigation.levelUp();    	
    }
}