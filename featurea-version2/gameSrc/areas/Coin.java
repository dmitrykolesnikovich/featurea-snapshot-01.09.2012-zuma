package areas;

import data.sprites;
import featurea.Animation;
import featurea.Vector3;

public class Coin extends Animation {
    public long lifeTime;
    public Coin(Vector3 vector){
    	super(sprites.coin, vector, null, null, null, null);
	}
}