package features.states;

import areas.Chain;
import data.parameters;
import data.values;
import featurea.Feature;

public class chainStateChange extends Feature{

    public Chain chain[] = new Chain[1];
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (!chain[0].stateNotChangedYet){return false;}
        if (!chain[0].isfirst()){return false;}
        return parameters.shouldChangeState;
    }

    @Override
    public void make() {
        chain[0].stateNotChangedYet = false;
        chain[0].state(values.state_move);
    }
    
}