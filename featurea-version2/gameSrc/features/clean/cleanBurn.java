package features.clean;

import areas.Burn;
import data.values;
import featurea.Feature;
import featurea.game;

public class cleanBurn extends Feature {

    public Burn burn[] = new Burn[1];

    @Override
    public boolean check() {
        if (game.play==null){return false;}
        burn[0].lifeTime += featurea.game.frameTime;
        return burn[0].lifeTime >= values.burnTime;
    }

    @Override
    public void make() {
    	game.play.remove(burn[0]);
    }
}