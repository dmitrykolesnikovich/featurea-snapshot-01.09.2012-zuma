package features.screen;

import data.parameters;
import type.Level;
import areas.Chain;
import areas.Path;
import data.screen.Hud;
import featurea.Feature;
import featurea.game;
import game.levels;
import game.navigation;
import game.settings;

public class gameOverAnimationComplete extends Feature {

    @Override
    public boolean check() {    	
    	if (!parameters.gameOverAnimationStarts){return false;}
    	for (Path path : ((Level)game.play).paths){
    		for (Chain chain : path.chains){
    			if (!chain.isEmpty()){
    				return false;
    			}
    		}    		
    	}
        return true; 
    }

    @Override
    public void make() {
    	 parameters.gameOverAnimationStarts = false;
    	 if (settings.lives>=1){
         	settings.lives--;
             navigation.hud.lifePanel.reset(settings.lives);       
             navigation.nextLevel(levels.getLevel(settings.levelNumber));
         }else{             
             navigation.gameOver();
             settings.levelNumber = 0;
             settings.lives = 3;
             settings.totalScore = 0;
             parameters.levelScore = 0;             
         }
    }
}