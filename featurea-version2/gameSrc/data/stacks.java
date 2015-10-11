package data;

import featurea.Feature;
import features.clean.*;
import features.screen.*;
import features.flow.*;
import features.track.*;
import features.flynut.*;
import features.states.*;
import features.adjusting.*;
import features.coin.*;
import features.dots.*;
import features.scratjump.*;

public class stacks {

    public final static Feature[] stack_version2 = new Feature[]{
        // Tick --------------------------------------
        new timeTick(),
        // -------------------------------------------
        
        // Fill gaps ---------------------------------
        new fillFirst(),
        new fillGaps(),
        // -------------------------------------------
                
        // Chain state -------------------------------
        new chainStateShouldChange(),
        new chainStateChange(),                
        new chainStateStop(),
        new chainStateMove(),
        new chainStateBack(),
        // -------------------------------------------

        // ?------------------------------------------
        // (**) after 'Animator' -------------------------------------------------------
        new chainAlign(), // 1
        // ------------------------------------------------------------------------
        new scratNutFly(), // 1 
        new flyNutCrossChain(), // 1            (* AFTER)
        new chainShift(), // 1 (Commented)      (* BEFORE)
        // ------------------------------------------
        
        // Chain breaking & gluing ------------------
        new chainsJoined(),
        new burst(),
        // ------------------------------------------
        
        // Fire -------------------------------------
        new fireNutCrossChain(),
        // ------------------------------------------
        
        // Navigation -------------------------------
        new gameOverAnimationStart(),
        new gameOverAnimationComplete(),
        new gameOverAnimationNutDissapear(), // 1 - show, 2 - 'gameOverAnimationComplete'          
        
        new levelUpStarts(),        
        // new levelUpComplete(),
        // -------------------------------------------
        
        // Throw scrat nut with dots -----------------
        new scratNutCreate(),
        new scratNutChooseType(),
        new scratNutChooseColor(),    
        new scratNutDirect(),
        new scratNutThrow(),
        new NoTouches(),
        new dotsHide(),    
        new dotsShow(),     
        // -------------------------------------------
        
        // Moving scrat ------------------------------
        new scratInputDown(),
        new scratInputMove(),
        new scratInputUp(),
        // -------------------------------------------
        
        // Coin -------------------------------------
        new coinAdd(),
        new coinTick(),
        new coinRemove(),
        new scratNutCrossCoin(), 
        // ------------------------------------------
        
        // Clean -------------------------------------
        new cleanBurst(),
        new cleanBurn(),
        new cleanFire(),
        new cleanScoreupText(),
        new cleanScratNut(),
        // -------------------------------------------
        
        // (**) before 'Painter' -----------------------------------------------
        new chainAlign(),
        // ----------------------------------------------------------------
        
        // TapForTap
        // new TapForTapShow(),
    };
}
