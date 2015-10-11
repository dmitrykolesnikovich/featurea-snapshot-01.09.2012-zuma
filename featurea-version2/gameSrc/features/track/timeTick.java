package features.track;

import areas.Chain;
import areas.Path;
import type.Level;
import data.parameters;
import data.values;
import featurea.Feature;
import featurea.game;

public class timeTick extends Feature {
    
    @Override
    public boolean check() {     
        if (game.play==null){return false;}
        return true;
    }

    @Override
    public void make() {           
        parameters.currentThrowDelay += featurea.game.frameTime;	
        values.levelLifeTime += featurea.game.frameTime;
        
        if (!values.isCoinInLevel){
            values.timeCoinIsNotInLevel += featurea.game.frameTime;
        }
        
        Level level = (Level)game.play;
        for (Path path : level.paths){
            for (Chain chain : path.chains){                
                if (chain.state() == values.state_back){
                    System.out.println("state_back:"+chain.lastnut().mode.velocity);
                }
            }
        }
    }
}