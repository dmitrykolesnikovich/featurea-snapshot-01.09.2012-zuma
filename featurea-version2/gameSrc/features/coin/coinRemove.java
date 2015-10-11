package features.coin;

import areas.Coin;
import data.values;
import featurea.Feature;
import featurea.game;

public class coinRemove extends Feature{

    public Coin coin[] = new Coin[1];
    
    @Override
    public boolean check() {        
        if (game.play==null){return false;}
        return coin[0].lifeTime >= 10000;
    }

    @Override
    public void make() {
    	game.play.remove(coin[0]);        
        values.isCoinInLevel = false;
        values.timeCoinIsNotInLevel = 0;
    }
    
}