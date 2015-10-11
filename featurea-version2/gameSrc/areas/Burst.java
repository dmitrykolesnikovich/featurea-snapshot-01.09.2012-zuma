package areas;

import data.sprites;
import featurea.Animation;
import featurea.Vector3;

public class Burst extends Animation {

	/** 'CleanBurst' */
	public long lifeTime = 0; // milliseconds
	
	public Burst(Vector3 vector){
		super(sprites.burst, vector, null, null, null, null);
	}
}
