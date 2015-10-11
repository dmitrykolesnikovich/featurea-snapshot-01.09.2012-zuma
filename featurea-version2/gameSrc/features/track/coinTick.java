package features.track;

import areas.Coin;
import featurea.Feature;
import featurea.game;

public class coinTick extends Feature {
    
    public Coin coin[] = new Coin[1];
    
    
    @Override
    public boolean check() {   
        if (game.play==null){return false;}
        return true;
    }

    @Override
    public void make() {           
        coin[0].lifeTime += featurea.game.frameTime;
    }
}
