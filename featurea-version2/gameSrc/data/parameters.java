package data;

import areas.Chain;
import featurea.Animation;
import featurea.game;
import game.navigation;
import areas.Pile;
import areas.FlyNut;
import type.Level;

public class parameters {
    
    public static boolean shouldChangeState;
    public static long currentThrowDelay = 0;
    public static final int OUT_OF_TIME = 0;
    public static final int OUT_OF_SCREEN = 1;
    public static final int NO_REASON = 2;
    public static final int NUT_WITHOUT_CHAIN = 3;
    public static final int NUT_OUT_OF_GAMEPLAY = 4;
    
    public static int reason_to_clean(FlyNut flyNut){
        if (flyNut.joiningTime <= 0){
            return OUT_OF_TIME;
        }
        Level level = (Level)game.play;
        if ((flyNut.nut != null) && (!flyNut.nut.cross(level.background, false))){
            return OUT_OF_SCREEN;
        }        
        if (flyNut.nut != null && flyNut.joiningTime > 0 && flyNut.joiningTime < values.burstNutJoiningTime 
                && flyNut.nut.chain() == null){
            return NUT_WITHOUT_CHAIN;
        }
        if (!game.play.children.contains(flyNut.nut)){
            return NUT_OUT_OF_GAMEPLAY;
        }
        return NO_REASON;
    }
    public static boolean isBurnNut() {
        return Math.random() <= 0.045;
    }
    public static Pile scratCrossPile(Animation scrat) {        
        for (Pile pile : ((Level)game.play).piles){
            if (scrat.cross(pile, false)){
                return pile;
            }
        }
        return null;
    }    
    public static boolean shouldAddFirstChainInPathWithOnlyNut = false;
    public static Chain whatChain;    
    
    public static boolean gameOverAnimationStarts = false;    
    public static boolean levelUpStarts = false;    
    
    public static int colorsCount;    
    public static boolean ismodechanged = false;
    public static int levelScore = 0;
}