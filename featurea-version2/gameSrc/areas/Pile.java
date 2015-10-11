package areas;

import data.sprites;
import featurea.Animation;
import featurea.Vector3;

public class Pile extends Animation{
    public Pile(Vector3 point){
    	super(sprites.pile, point, null, null, null, null);         
    }
}