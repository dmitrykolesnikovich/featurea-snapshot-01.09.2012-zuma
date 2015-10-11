package features.coin;

import featurea.Vector3;
import featurea.game;
import areas.Coin;
import data.parameters;
import data.sounds;
import data.values;
import featurea.Feature;
import static featurea.device.audio;

public class coinAdd extends Feature{

    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play==null){return false;}
        if (values.COINS_IN_LEVEL_TO_APPEAR==0){return false;}
        if (values.isCoinInLevel){return false;}       
        if (values.timeCoinIsNotInLevel<20000){return false;}
        return Math.random() <= values.appearing_probability;
    }

    @Override
    public void make() {
    	game.play.add(new Coin (new Vector3(460, 300, values.DZ_CROSS_UPPER)));
        values.COINS_IN_LEVEL_TO_APPEAR--;
        values.isCoinInLevel = true;
        audio.play(sounds.nut_contacts_coin);
    }
    
}