package features.flynut;

import areas.Scrat;
import data.parameters;
import data.values;
import featurea.Feature;
import featurea.game;
import static featurea.game.touch;
import static featurea.Touch.Type;

public class scratNutDirect extends Feature{

    public Scrat scrat[] = new Scrat[1];
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play== null){return false;}
        if (touch==null){return false;}        
        return (touch.type == Type.DOWN || touch.type == Type.MOVE) &&
                parameters.currentThrowDelay >= values.THROW_DELAY &&       
                !touch.cross(scrat[0]);
    }

    @Override
    public void make() {
        double Ox = scrat[0].vector.dx();
    	double Oy = scrat[0].vector.dy();
    	double Ax = Ox + 10;
    	double Ay = Oy;
    	double Bx = touch.x;
    	double By = touch.y;    	
    	double x1 = Ax - Ox;
    	double y1 = Ay - Oy;
    	double x2 = Bx - Ox;
    	double y2 = By - Oy;    	
    	double cos = (x1*x2 + y1*y2) / Math.sqrt ((x1*x1 + y1*y1) * (x2*x2 + y2*y2));
    	double rad =  Math.acos(cos);
    	double degree = (180 / Math.PI) * rad;
    	if (y2 > 0){
    		degree = -degree;
    	}       
    	scrat[0].degree(degree);    	
    }
    
    
}