package features.clean;

import type.Level;
import data.values;
import areas.Burst;
import featurea.Feature;
import featurea.game;

public class cleanBurst extends Feature {

    public Burst burst[] = new Burst[1];

    @Override
    public boolean check() {
        if (game.play==null){return false;}
        burst[0].lifeTime += featurea.game.frameTime;
        return burst[0].lifeTime >= values.burstTime;
    }

    @Override
    public void make() {
        Level level = (Level)game.play;
        level.removeBurst(burst[0]);        
    }
}