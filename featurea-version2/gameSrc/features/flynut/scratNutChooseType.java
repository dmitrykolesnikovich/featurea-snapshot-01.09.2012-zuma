package features.flynut;

import areas.Scrat;
import data.parameters;
import type.Nut;
import featurea.Feature;
import featurea.game;


public class scratNutChooseType extends Feature {

    public Scrat scrat[] = new Scrat[1];

    @Override
    public boolean check() {
        if (game.play == null){return false;}
        return scrat[0].nut.type == Nut.UNDEFINED; 
    }

    @Override
    public void make() {
        if (parameters.isBurnNut()){
            scrat[0].nut.type = Nut.FIRE;
        }else{
            scrat[0].nut.type = Nut.FLY;
        }
    }
}