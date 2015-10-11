package features.flow;

import data.parameters;
import data.screen.Hud;

import java.util.List;
import java.util.ArrayList;
import areas.Chain;
import areas.FlyNut;
import featurea.Feature;
import featurea.game;
import game.navigation;
import type.Level;
import type.Nut;

public class burst extends Feature {

    public FlyNut flyNut[] = new FlyNut[1];

    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (flyNut[0].nut == null) {
            return false;
        } // 'step1_StrikeNut' feature should make firstly. 
        if (flyNut[0].nut.chain() == null) {
            return false;
        } // 'step1_Cross' feature should make firstly.
        if (flyNut[0].joiningTime > 0) {
            return false;
        } // 'step2_Joining' feature must be finished firstly.
        Nut nut = flyNut[0].nut;
        final Chain chain = nut.chain();
        int color = nut.color;
        int jointindex = chain.indexOf(nut);
        // 'tailindex'
        chain.tailindex = jointindex;
        Nut tailnut = chain.nut(chain.tailindex);
        while (tailnut != null && tailnut.color == color) {
            chain.tailindex--;
            if (chain.tailindex < 0) {
                break;
            }
            tailnut = chain.nut(chain.tailindex);
        }
        chain.tailindex++;

        // 'headindex'
        chain.headindex = jointindex;
        Nut headnut = chain.nut(chain.headindex);
        while (headnut != null && headnut.color == color) {
            chain.headindex++;
            if (chain.headindex >= chain.size()) {
                break;
            }
            headnut = chain.nut(chain.headindex);
        }
        chain.headindex--;

        // 'up'
        flyNut[0].up = (chain.headindex - chain.tailindex);        
        return flyNut[0].up >= 2;
    }

    @Override
    public void make() {       
        Chain chain = flyNut[0].nut.chain();
        
        List<Nut> nutsToRemove = new ArrayList<Nut>();        
        for (int i = chain.tailindex; i <= chain.headindex; i++) {            
            nutsToRemove.add(chain.nut(i));           
        }
        
        // (*) Case 'nut.indexinchain == 0'        
        if (navigation.hud.addMoreNuts() && chain.indexinpath()==0){
            for (Nut nut : nutsToRemove){
                if (nut.indexinchain()==0){
                    parameters.shouldAddFirstChainInPathWithOnlyNut = true;
                    parameters.whatChain = chain;
                    break;
                }
            }
        }
                
        Level level = (Level)game.play;
        for (Nut nut : nutsToRemove){
            Level.removeNutFromChainAndGameplay(nut);            
            level.addBurst(new areas.Burst(nut.vector));                
        }         
        
        //  Sound
        int score = (flyNut[0].up + 1) * 100;
        navigation.hud.scoreUp(score, flyNut[0].nut.vector);
        
        // ??
        flyNut[0].nut = null; 
        
    }
    
    
}