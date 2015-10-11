package features.clean;

import areas.Fire;
import areas.Burn;
import data.values;
import areas.Burst;
import featurea.Feature;
import featurea.game;

public class cleanFire extends Feature {

    public Fire fire[] = new Fire[1];

    @Override
    public boolean check() {
        if (game.play==null){return false;}
        fire[0].lifeTime += featurea.game.frameTime;
        return fire[0].lifeTime >= values.burnTime;
    }

    @Override
    public void make() {
    	game.play.remove(fire[0]);
    }
}