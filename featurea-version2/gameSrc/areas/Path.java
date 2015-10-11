package areas;

import java.util.ArrayList;
import java.util.List;

import type.Nut;
import featurea.Graph;
import featurea.Vector3;

public class Path {
	
	
	public Vector3 startPoint;
	public Graph trajectory;
	public List<Chain> chains = new ArrayList<Chain>(){
		public String toString() {
			String result = "";
			for (Chain chain : chains){
				result += chain.toString() + ";";
			}	
			return result;
		};
	};
	public StartForNuts startfornuts;
	
	public Path(Vector3 point, Graph trajectory){		
		this.startPoint = point;
		this.trajectory = trajectory;		
	}
	
	public Chain lastchain(){		
		if (chains.size() - 1>=0){
			return chains.get(chains.size() - 1);
		}
		return null;
		
	}
	
	public Chain firstchain(){	
		if (!chains.isEmpty()){
			return chains.get(0);
		}
		return null;		
	}
	public List<Nut> nuts() {
		List<Nut> nuts = new ArrayList<Nut>();
		for (Chain chain : chains){
			nuts.addAll(chain.nuts);
		}
		return nuts;
	}

	public boolean isEmpty() {		
		return chains.isEmpty();
	}

    public void add(int indexinpath, Chain chain) {
        chains.add(indexinpath, chain);
    }

    
}
