package features.scratjump;

import data.parameters;
import areas.Scrat;
import featurea.Feature;
import featurea.game;
import static featurea.game.touch;
import static featurea.Touch.Type;

public class scratInputDown extends Feature{

    public Scrat scrat[] = new Scrat[1];
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play == null){return false;}
        if (touch == null){return false;}
         return (touch.type == Type.DOWN) && touch.cross(scrat[0]) && !scrat[0].downed; 
    }

    @Override
    public void make() {
        scrat[0].downed = true;
    }

}