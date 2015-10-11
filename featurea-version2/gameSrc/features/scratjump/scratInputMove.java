package features.scratjump;

import data.parameters;
import areas.Scrat;
import featurea.Feature;
import featurea.Vector3;
import featurea.game;
import type.Level;
import static featurea.game.touch;
import static featurea.Touch.Type;

public class scratInputMove extends Feature{

    public Scrat scrat[] = new Scrat[1];
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play== null){return false;}
        if (touch == null){return false;}        
         return (touch.type == Type.MOVE) && scrat[0].downed && ((Level)game.play).piles.size()>=2; 
    }

    @Override
    public void make() {             
        scrat[0].vector(new Vector3(touch.x, touch.y, scrat[0].vector.dz()));        
    }

}