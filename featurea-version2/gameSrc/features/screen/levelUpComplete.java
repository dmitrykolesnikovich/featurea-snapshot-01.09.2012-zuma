package features.screen;

import data.parameters;
import featurea.Feature;
import game.navigation;

public class levelUpComplete extends Feature {

    @Override
    public boolean check() {
    	/*if (!parameters.levelUpStarts){return false;}
        if (gameplay==null){return false;}
        Level level = (Level) (gameplay);
        return !level.hasScoreupText();*/
    	return parameters.levelUpStarts;
    }

    @Override
    public void make() {
    	navigation.levelUp();    	
        parameters.levelUpStarts = false;
    }
}