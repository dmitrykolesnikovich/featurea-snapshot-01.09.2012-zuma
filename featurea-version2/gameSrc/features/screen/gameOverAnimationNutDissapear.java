package features.screen;

import java.util.ArrayList;
import java.util.List;
import data.parameters;
import type.Level;
import type.Nut;
import areas.Chain;
import areas.Path;
import featurea.Feature;
import featurea.game;

public class gameOverAnimationNutDissapear extends Feature {

    @Override
    public boolean check() {    	       
        return parameters.gameOverAnimationStarts;
    }

    @Override
    public void make() {
    	List<Nut> nutToDelete = new ArrayList<Nut>();
    	for (Path path : ((Level)game.play).paths){    		
    		for (Chain chain : path.chains){
    			for (Nut nut : chain.nuts){
    				if (nut.graph.finished()){
    					nutToDelete.add(nut);    					
    				}
    			}
    		}
    	}
    	for (Nut nut : nutToDelete){
    		game.play.remove(nut);			
    		nut.chain().nuts.remove(nut);
    	}
    }
}