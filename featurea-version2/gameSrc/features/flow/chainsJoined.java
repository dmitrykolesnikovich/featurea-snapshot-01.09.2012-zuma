package features.flow;

import areas.FlyNut;
import data.parameters;
import data.values;
import type.Nut;
import data.sounds;
import areas.Chain;
import areas.Path;
import featurea.device;
import featurea.Feature;
import featurea.game;

public class chainsJoined extends Feature { // type of feature should decide combinatorics strategy!	

    public Chain chain[] = new Chain[1]; // (01 || 10) -- not (01 && 10) !

    /* Assume: 
     * 1. chain[0].isinpath()==true
     * 2. 
     * */
    
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
    	// Assume:
        Chain tailChain = chain[0];
        if (tailChain == null) {return false;}
        Chain headChain = tailChain.next();
        if (headChain == null) {return false;} // if 'tailChain' is last in path        
        if (!tailChain.isinpath()){return false;}
        if (!headChain.isinpath()){return false;}
        
        Nut firstInHead = headChain.firstnut();
        if (firstInHead == null) {return false;}
        Nut lastInTail = tailChain.lastnut();
        if (lastInTail == null) {return false;}
        if (firstInHead.graph.index<lastInTail.graph.index){return true;}
        
        // TRY: -------------------------------------------------------------------------------------------------------------------
        if (firstInHead.graph.index>lastInTail.graph.index){return firstInHead.crossBallsZ(lastInTail)>0;}
        if (firstInHead.graph.index==lastInTail.graph.index){
        	if (firstInHead.graph.progress<=lastInTail.graph.progress){return true;}
        	else{return firstInHead.crossBallsZ(lastInTail)>0;}
        }
        // ------------------------------------------------------------------------------------------------------------------------
        
        return false;
    }

    /* Remove 'headChain', place all nuts in 'tailChain' */
    @Override
    public void make() {
        // Sound
        device.audio.play(sounds.flynut_contacts_nut);

        // Init		
        Chain tailChain = chain[0];
        Chain headChain = chain[0].next();
        Path path = tailChain.path;
        Nut lastNut = tailChain.lastnut();
        Nut firstNut = headChain.firstnut();      

        int index = tailChain.indexinpath();
        // Remove	
        path.chains.remove(headChain);
        game.play.remove(headChain);
        path.chains.remove(tailChain);
        game.play.remove(tailChain);
        // Add        
        Chain chain = new Chain(path);
        chain.nuts.addAll(tailChain.nuts);
        chain.nuts.addAll(headChain.nuts);
        path.chains.add(index, chain);
        game.play.add(chain);        
        
        // State.
        chain.state(values.state_stop);
        chain.stateNotChangedYet = tailChain.stateNotChangedYet;
        
        // For burst.
        if (lastNut.color == firstNut.color){
            FlyNut flyNut = new FlyNut(lastNut);
            flyNut.joiningTime = 0;
            game.play.add(flyNut);     
        }        
        
        
    }
}