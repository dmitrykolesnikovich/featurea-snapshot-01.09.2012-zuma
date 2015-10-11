package features.flow;

import data.modes;
import data.parameters;
import type.Nut;
import areas.Chain;
import areas.Path;
import data.values;
import data.screen.Hud;
import featurea.Feature;
import featurea.game;

public class fillFirst extends Feature {

    @Override
    public boolean check() {       
    	if (parameters.gameOverAnimationStarts){return false;}
        return parameters.shouldAddFirstChainInPathWithOnlyNut;
    }

    @Override
    public void make() {
        Path path = parameters.whatChain.path;
        Chain firstChain = new Chain(path);                    
        int color = (int) (Math.random() * parameters.colorsCount);
        Nut onlynut = new Nut(color, path.startPoint.copy(), 
                path.trajectory.copy(), modes.move(), null, null);
        firstChain.nuts.add(onlynut);            
        game.play.add(onlynut);
        path.add(0, firstChain);
        game.play.add(firstChain);        

        // State
        firstChain.state(values.state_stop);                   
        firstChain.stateNotChangedYet = parameters.whatChain.stateNotChangedYet;
        
        // Reset flags
        parameters.shouldAddFirstChainInPathWithOnlyNut = false;
        parameters.whatChain = null;
    }
}