package areas;

import data.colors;
import data.modes;
import data.parameters;
import data.values;
import java.util.ArrayList;
import java.util.List;

import type.Nut;
import featurea.Mode;
import featurea.Vector3;

public class Chain {

    public boolean stateNotChangedYet = true;
    public Nut get(int i) {
        return nuts.get(i);
    }
    public Path path;
    public List<Nut> nuts = new ArrayList<Nut>() {

    	// For debug:
        @Override
        public String toString() {
            String string = "";
            for (int i = 0; i < size(); i++) {
                Nut nut = get(i);
                string += nut + ", ";
            }
            return string;
        }
    };
	
   
    // 'Bursting' ---------------
    public int tailindex = -1;
    public int headindex = -1;

    public Nut lastnut() {
        if (!nuts.isEmpty()) {
            return nuts.get(nuts.size() - 1);
        }
        return null;
    }
   

    public Nut firstnut() {
        if (!nuts.isEmpty()) {
            return nuts.get(0);
        }
        return null;
    }

    public Chain(Path path) {
        this.path = path;
    }

    public int indexinpath() {
        return path.chains.indexOf(this);
    }

    @Override
    public String toString() {
        String s = "";
        for (Nut nut : nuts) {
            if (nut == null) {
                s += "null, ";
            } else {
                switch (nut.color) {
                    case colors.BLUE:
                        s += "b, ";
                        break;
                    case colors.GREEN:
                        s += "g, ";
                        break;
                    case colors.PURPLE:
                        s += "p, ";
                        break;
                    case colors.RED:
                        s += "r, ";
                        break;
                    case colors.YELLOW:
                        s += "y, ";
                        break;
                }
            }
        }
        return s;
    }

    public int indexOf(Nut nut) {
        return nuts.indexOf(nut);
    }

    public Nut nut(int index) {
        return nuts.get(index);
    }

    public boolean isEmpty() {
        return nuts.isEmpty();
    }

    public int size() {
        return nuts.size();
    }

    public boolean isfirst() {
        return indexinpath() == 0;
    }

    public boolean islast() {
        return indexinpath() == path.chains.size() - 1;
    }

    public Chain next() {
        int index = indexinpath();
        if (index + 1 >= path.chains.size()) {
            return null;
        }
        return path.chains.get(index + 1);
    }

    public Chain previous() {
        int index = indexinpath();
        if (index - 1 < 0) {
            return null;
        }
        return path.chains.get(index - 1);
    }

    public void shift(double overlap) {
        for (Nut nut : nuts) {
            Vector3 shift = nut.graph.vector(overlap);
            nut.vector.plus(shift);
            nut.graph = nut.graph.graph(overlap);
        }
    }

    
    private int state = values.state_stop;
     private void setMode(Mode mode) {
        for (Nut nut : nuts) {
            nut.mode = mode.copy();
        }
    }
    public void state(int state){        
        switch(state){
            case values.state_back: {
                this.state = state;
                setMode(modes.rollBack()); 
                break;
            }
            case values.state_stop : {
                this.state = state;
                setMode(modes.stop()); 
                break;
            }
            case values.state_move : {
                this.state = state;
                if(parameters.shouldChangeState){
                    setMode(modes.move());
                }else{
                    setMode(modes.run());
                } 
                break;                   
            }
            default: {break;}
            
        }        
    }
    public void add(Nut nut) {
        nuts.add(nut);
    }

    public void remove(Nut nut) {
        nuts.remove(nut);
    }

    public int state() {
        return state;
    }

	public boolean isinpath() {		
		return indexinpath()>=0;
	}
}