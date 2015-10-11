package areas;

import data.values;
import featurea.Vector3;
import type.Nut;

public class FlyNut {
	public FlyNut(Nut nut) {
		this.nut = nut;
		joiningTime = values.burstNutJoiningTime;                
	}
	public double joiningTime;
	public Nut nut;
	public int up;
	
	public Nut crossNut; // For 'BurstNutCrossChain' feature
	
	
	public boolean isJoining(){
		if (nut == null){return false;}
		return nut.chain()!=null;
	}
        public Vector3 shift;
    public void setShift(Vector3 shiftvector) {
        this.shift = shiftvector;
        nut.vector.plus(shiftvector);
    }

}
