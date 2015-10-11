package areas;

import data.sprites;
import featurea.Animation;
import featurea.Vector3;

public class Fire extends Animation {

	/** 'CleanBurst' */
	public long lifeTime = 0; // milliseconds
	
	public Fire(Vector3 vector){
		super(sprites.fire, vector, null, null, null, null);
	}
}
