package features.flynut;

import areas.Scrat;
import data.parameters;
import type.Nut;
import featurea.Feature;
import featurea.game;

public class scratNutCreate extends Feature {

    public Scrat scrat[] = new Scrat[1];

    @Override
    public boolean check() {      
    	if (parameters.gameOverAnimationStarts){return false;}
        if (game.play == null){return false;}
        return scrat[0].nut == null; // See 'step1_CreateFlyNut' 'make': scrat[0].nut = null;
    }

    @Override
    public void make() {
        scrat[0].isLeftHandActive = !scrat[0].isLeftHandActive;
        scrat[0].nut = new Nut(scrat[0].isLeftHandActive, scrat[0].leftHand, scrat[0].rightHand);     
        scrat[0].lead(scrat[0].nut);
        
        System.out.println("scrat[0].vector 	= "+scrat[0].vector);
        System.out.println("scrat[0].nut.vector = "+scrat[0].nut.vector);
    }
    
}