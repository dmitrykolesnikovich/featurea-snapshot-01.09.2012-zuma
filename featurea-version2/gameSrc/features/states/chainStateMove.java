package features.states;

import areas.Chain;
import data.parameters;
import data.values;
import featurea.Feature;

public class chainStateMove extends Feature{

    public Chain chain[] = new Chain[1];
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (!chain[0].isfirst()){return false;}        
        return (chain[0].state() == values.state_back || chain[0].state() == values.state_stop);
    }

    @Override
    public void make() {
        chain[0].state(values.state_move);              
    }

}