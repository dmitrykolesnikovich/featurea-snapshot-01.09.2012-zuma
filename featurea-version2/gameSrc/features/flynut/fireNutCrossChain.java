package features.flynut;

import type.Level;
import type.Nut;
import areas.FlyNut;
import areas.Chain;
import areas.Fire;
import data.parameters;
import data.values;
import data.screen.Hud;
import featurea.Feature;
import featurea.game;
import game.navigation;

import java.util.ArrayList;
import java.util.List;

public class fireNutCrossChain extends Feature {

    public FlyNut flyNut[] = new FlyNut[1];
    public Chain chain[] = new Chain[1]; // crossChain

    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (flyNut[0].nut == null) {            
            return false;
        }
        if (flyNut[0].nut.type != Nut.FIRE) {
            return false;
        } 
        if (flyNut[0].joiningTime < values.burstNutJoiningTime) {
            return false;
        }
        if (flyNut[0].nut.chain() != null) {
            return false;
        }         
        for (Nut nut : chain[0].nuts) {
            // if (nut.vector().dz()==values.DZ_CROSS_UPPER || nut.vector().dz()==values.DZ_CROSS_UNDER){ // check!
                double overlap = flyNut[0].nut.crossBalls(nut);
                if (overlap > 0) {
                    // 'crossNut' is buffer between 'check' and 'make'
                    flyNut[0].crossNut = nut;
                    return true;
                }
            // }
        }
        return false;
    }

    @Override
    public void make() {                
        Fire fire = new areas.Fire(flyNut[0].crossNut.vector);        
        List<Nut> nutsToBurn = new ArrayList<Nut>();       
        for (Nut nut : ((Level)game.play).nutsInChain()){
            if (nut.crossBalls(fire)>0){
                nutsToBurn.add(nut);
            }
        }
        
        // (*) Case 'nut.indexinchain == 0'        
        if (navigation.hud.addMoreNuts() && chain[0].indexinpath()==0){
            for (Nut nut : nutsToBurn){
                if (nut.indexinchain()==0){
                    parameters.shouldAddFirstChainInPathWithOnlyNut = true;
                    parameters.whatChain = chain[0];
                    break;
                }
            }
        }
        
        game.play.add(fire);
        game.play.remove(flyNut[0].nut);                   
        // gameplay.add(new areas.Burn(flyNut[0].nut.vector()));
        
        // burn
        for (Nut nut : nutsToBurn){
            Level.removeNutFromChainAndGameplay(nut);
            // gameplay.add(new areas.Burn(nut.vector()));       
        }
        
        // (for now) score up:
        int score = (nutsToBurn.size() + 1) * 100;
        navigation.hud.scoreUp(score, fire.vector);
        
    }
}