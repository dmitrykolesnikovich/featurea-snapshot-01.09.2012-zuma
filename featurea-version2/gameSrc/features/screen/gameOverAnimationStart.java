package features.screen;

import data.modes;
import data.parameters;
import data.sounds;
import type.Level;
import type.Nut;
import areas.Chain;
import areas.Path;
import areas.Scrat;
import featurea.Axis;
import featurea.Feature;
import featurea.game;
import featurea.Axis.Turn;
import static featurea.device.audio;

public class gameOverAnimationStart extends Feature {

    public Path path[] = new Path[1];
    public Scrat scrat[] = new Scrat[1];
    
    @Override
    public boolean check() {  
    	if (parameters.gameOverAnimationStarts){return false;} // 'gameOverAnimationStart' has been made already
        if (game.play == null){return false;}
        if (path[0].firstchain() == null) {return false;}
        
        Nut lastNut = path[0].firstchain().lastnut();
        
        if (lastNut == null) {return false;}
        if (lastNut.mode == null){return false;}
        
        return lastNut.graph.finished();
    }

    @Override
    public void make() {
    	audio.play(sounds.game_over);
    	parameters.gameOverAnimationStarts = true;
    	
    	for (Path path : ((Level)game.play).paths){for (Chain chain : path.chains){
    			chain.firstnut().mode = modes.levelFailed();
    		}    		
    	}    	
    	scrat[0].axis = new Axis(scrat[0].axis.degree, new Turn[]{new Turn(1000, -400)});  
    	scrat[0].unlead(scrat[0].nut);
    	game.play.remove(scrat[0].nut);
    }
}
