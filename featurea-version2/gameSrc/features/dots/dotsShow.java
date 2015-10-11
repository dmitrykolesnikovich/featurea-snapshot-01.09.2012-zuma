package features.dots;

import data.parameters;
import areas.Dot;
import areas.Scrat;
import featurea.Feature;
import featurea.Vector3;
import featurea.game;
import type.Level;
import static featurea.game.touch;
import static featurea.Touch.Type;

public class dotsShow extends Feature {

    public Scrat scrat[] = new Scrat[1];
    
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play == null){
            return false;
        }
        if (touch == null) {return false;}
        if (scrat[0].downed){
            return false;
        }
        return (touch.type == Type.DOWN || touch.type == Type.MOVE);
    }

    @Override
    public void make() {
        double degree = -scrat[0].axis.degree;
        double rad = degree/180*Math.PI;
        Vector3 point = scrat[0].vector.copy();
        double l = 30;
        
        Level level = (Level)game.play;
        for (int i=1; i<=8; i++){
            Vector3 dotPoint = new Vector3(point.dx()+i*l*Math.cos(rad), point.dy()+i*l*Math.sin(rad), point.dz());
            Dot dot = new Dot (dotPoint);
            level.addDot(dot);                        
        }
        
        
       
        
    }
}