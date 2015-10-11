package features.track;

import type.Level;
import data.parameters;
import data.values;
import featurea.Feature;
import featurea.game;

public class chainStateShouldChange extends Feature {
    @Override
    public boolean check() {    
        if (game.play==null){return false;}
        return values.levelLifeTime >= ((Level)game.play).changemodetime && !parameters.shouldChangeState;
    }

    @Override
    public void make() {
        parameters.shouldChangeState = true;
    }
}
