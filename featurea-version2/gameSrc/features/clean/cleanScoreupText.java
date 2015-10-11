package features.clean;

import type.Level;
import featurea.game;
import areas.Burn;
import areas.ScoreupText;
import data.values;
import areas.Burst;
import featurea.Feature;

public class cleanScoreupText extends Feature {

    public ScoreupText scoreupText[] = new ScoreupText[1];

    @Override
    public boolean check() {
        if (game.play==null){return false;}
        scoreupText[0].lifeTime += featurea.game.frameTime;
        return scoreupText[0].lifeTime >= values.scoreupTime;
    }

    @Override
    public void make() {
    	Level level = (Level)game.play;
    	level.removeScoreupText(scoreupText[0]);       
    }
}