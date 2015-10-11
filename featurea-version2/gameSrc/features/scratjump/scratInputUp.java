package features.scratjump;

import areas.Pile;
import areas.Scrat;
import data.parameters;
import featurea.Feature;
import featurea.Vector3;
import static featurea.game.touch;
import static featurea.Touch.Type;

public class scratInputUp extends Feature{

    public Scrat scrat[] = new Scrat[1];
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (touch == null){return false;}
        return (touch.type == Type.UP) && scrat[0].downed; 
    }

    @Override
    public void make() {
        Pile pile = parameters.scratCrossPile(scrat[0]);
        if (pile!=null){
            scrat[0].vector(new Vector3(pile.vector.dx(), pile.vector.dy(), scrat[0].vector.dz()));
            scrat[0].pile = pile;
        }else{
            pile = scrat[0].pile;
            scrat[0].vector(new Vector3(pile.vector.dx(), pile.vector.dy(), scrat[0].vector.dz()));            
        }
        scrat[0].downed = false;
    }

}