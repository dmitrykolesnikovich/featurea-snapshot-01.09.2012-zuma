package areas;


import data.sprites;
import featurea.Animation;
import featurea.Vector3;

public class Dot extends Animation {    
    public Dot(Vector3 point){
    	super(sprites.dot, point, null, null, null, null);       
    }
}