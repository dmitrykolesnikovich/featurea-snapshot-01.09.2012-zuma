package areas;

import type.ScoresText;
import featurea.Animation;
import featurea.Vector3;

public class ScoreupText extends Animation{
    public long lifeTime;
    public ScoreupText(String string, Vector3 point){
    	super(null, point, null, null, null, null);
    	this.text = new ScoresText(string);    	
    }
}