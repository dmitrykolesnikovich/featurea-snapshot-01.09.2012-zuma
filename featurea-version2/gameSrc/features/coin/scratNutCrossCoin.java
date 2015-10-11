package features.coin;

import type.Nut;
import areas.FlyNut;
import featurea.Vector3;
import areas.Coin;
import data.parameters;
import data.sounds;
import data.values;
import data.screen.Hud;
import featurea.Feature;
import static featurea.device.audio;
import featurea.game;
import game.navigation;

public class scratNutCrossCoin extends Feature{

    public FlyNut flyNut[] = new FlyNut[1];
    public Coin coin[] = new Coin[1];
    
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play==null){return false;}
        if (flyNut[0].nut == null) {            
            return false;
        }                                 
        return flyNut[0].nut.cross(coin[0], false);
    }

    @Override
    public void make() {        
    	game.play.remove(coin[0]);
    	game.play.remove(flyNut[0]);
    	game.play.remove(flyNut[0].nut);
    	game.play.add(new areas.Burst(coin[0].vector));    
        
        flyNut[0].nut.vector.minus(new Vector3(80, 0, 0));
        navigation.hud.scoreUp(2000, flyNut[0].nut.vector);
        
        values.isCoinInLevel = false;
        values.timeCoinIsNotInLevel = 0;
        
        audio.play(sounds.score_up);
    }
    
}