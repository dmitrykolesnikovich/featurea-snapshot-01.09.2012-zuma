package features.clean;

import areas.FlyNut;
import data.parameters;
import featurea.Feature;
import featurea.game;
import type.Level;

public class cleanScratNut extends Feature {

    public FlyNut flyNut[] = new FlyNut[1];

    @Override
    public boolean check() {
        if (game.play==null){return false;}
        switch(parameters.reason_to_clean(flyNut[0])){
            case parameters.OUT_OF_SCREEN:{
                return true;
            }
            case parameters.OUT_OF_TIME:{
                return true;
            } 
            case parameters.NUT_WITHOUT_CHAIN:{
                return true;
            } 
            case parameters.NUT_OUT_OF_GAMEPLAY:{
                return true;
            }
        }  
        return false;   
    }

    @Override
    public void make() {        
        Level level = (Level)game.play;
        switch(parameters.reason_to_clean(flyNut[0])){
            case parameters.OUT_OF_SCREEN:{
                level.removeFlyNut(flyNut[0]);                
                game.play.remove(flyNut[0].nut);
                break;
            }
            case parameters.OUT_OF_TIME:{
                level.removeFlyNut(flyNut[0]);          
                break;
            }
            case parameters.NUT_WITHOUT_CHAIN:{
               level.removeFlyNut(flyNut[0]);          
                break;
            } 
            case parameters.NUT_OUT_OF_GAMEPLAY:{
                level.removeFlyNut(flyNut[0]);          
                break;
            } 
        }    
        
    }
    
}