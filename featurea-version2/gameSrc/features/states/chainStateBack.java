package features.states;

import areas.Chain;
import data.parameters;
import data.values;
import featurea.Feature;
import type.Nut;

public class chainStateBack extends Feature{

    public Chain chain[] = new Chain[1];
    
    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        Chain head = chain[0];
        Chain tail = chain[0].previous();
        if (tail == null){return false;} // if 'chain[0]' is first chain in path.
        Nut tailLastNut = tail.lastnut();
        Nut headFirstNut = head.firstnut();
        if (head.nuts.isEmpty()){return false;}
        if (tail.nuts.isEmpty()){return false;}
        if (tailLastNut==null){return false;}
        if (headFirstNut==null){return false;}
        return headFirstNut.color == tailLastNut.color && head.state() != values.state_back;
    }

    @Override
    public void make() {
        chain[0].state(values.state_back);                
    }

}