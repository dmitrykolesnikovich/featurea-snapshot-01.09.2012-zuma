package features.adjusting;

import areas.Chain;
import data.values;
import featurea.Feature;
import type.Nut;

public class chainAlign extends Feature {

    public Chain chain[] = new Chain[1];

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void make() {
        switch (chain[0].state()) {
            case values.state_back: {
                for (int i = chain[0].size() - 2; i >= 0; i--) {
                    Nut after = chain[0].nut(i+1);
                    Nut before = chain[0].nut(i);                    
                    after.moveAnimation(before, -values.nutdiameter);
                }               
                break;
            }
            case values.state_move:
            case values.state_stop: {                
                for (int i = 0; i < chain[0].size() - 1; i++) {
                    Nut first = chain[0].nut(i);
                    Nut second = chain[0].nut(i + 1);                    
                    first.moveAnimation(second, values.nutdiameter);                    
                }
                break;
            }
        }
    }
}