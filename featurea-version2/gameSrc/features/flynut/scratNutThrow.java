package features.flynut;

import type.Level;
import data.sounds;
import areas.FlyNut;
import areas.Scrat;
import data.parameters;
import data.values;
import featurea.Feature;
import featurea.Graph;
import featurea.Vector3;
import featurea.game;
import static featurea.device.audio;
import static featurea.game.touch;
import static featurea.Touch.Type;

public class scratNutThrow extends Feature{

    public Scrat scrat[] = new Scrat[1];
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play == null){return false;}
        if (touch==null){return false;}    
        if (touch.type != Type.UP){return false;}           
        return  !scrat[0].downed &&
                parameters.currentThrowDelay >= values.THROW_DELAY;
    }

    @Override
    public void make() {
        double degree = scrat[0].axis.degree;
        
    	Vector3 preMotion = null;
    	if (scrat[0].isLeftHandActive){    		
    		preMotion = scrat[0].leftmotion.copy();		
    	}else{    		
    		preMotion = scrat[0].rightmotion.copy();	
    	}
    	Vector3 motion =  new Vector3(1000, 0, 0);
    	
        // 'strikeGraph'
        Graph strikeGraph = new Graph(new Vector3[]{preMotion, motion, null}, 0, degree);
    	scrat[0].nut.graph = strikeGraph;       
       
        // Make delay
    	parameters.currentThrowDelay = 0;   	
    	scrat[0].unlead(scrat[0].nut);
    	
    	FlyNut flyNut = new FlyNut(scrat[0].nut);
        
        Level level = (Level)game.play;
        level.addFlyNut(flyNut);
    	
        game.play.add(scrat[0].nut);
        
          	
    	// For 'step2_CreateBurstNut' feature
    	scrat[0].nut = null; 
        
    	// Sound
    	audio.play(sounds.scrat_throw_nut);   	
    }
    
    
}