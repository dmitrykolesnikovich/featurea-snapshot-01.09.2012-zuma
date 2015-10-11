package areas;

import featurea.Animation;
import featurea.Vector3;

public class Burn extends Animation {

	/** 'CleanBurst' */
	public long lifeTime = 0; // milliseconds
	
	public Burn(Vector3 vector){
		super(null, vector, null, null, null, null);	
	}
}
