package featurea;

public class Pulse {
    
    public float scale;
    public Zoom[] zooms;
    public int index;
    
    public Pulse(float scale, Zoom[] zooms){
        this.scale = scale;
        this.zooms = zooms;
    }
    public Pulse copy() {
    	Pulse pulse = new Pulse(this.scale, this.zooms);
    	pulse.index = this.index;
		return pulse;
	}
    
    public Zoom zoom(){
        return zooms[index];
    }
    
    public static class Zoom {    
        public final float time;
        public final float scale;
        public Zoom(float time, float scale){
            this.time = time;
            this.scale = scale;
        }
    }

	
    
}