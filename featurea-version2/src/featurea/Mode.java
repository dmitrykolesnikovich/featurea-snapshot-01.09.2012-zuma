package featurea;

public class Mode {
	
    public final Shift[] shifts;    
    public int index;
    public double progress;        
    public double velocity;
    public float koeff = 1;
    public double acceleration;
    
    
    public Mode(Shift[] shifts, int index, double progress){
        this.shifts = shifts;
        this.index = index;
        this.progress = progress;
        if (shift()!=null){
            this.velocity = shift().velocity;
            this.acceleration = shift().acceleration;
        }         
    }
    public Mode copy(){
    	Mode mode = new Mode(shifts, index, progress);
    	mode.velocity = velocity;
    	mode.koeff = koeff;
    	mode.acceleration = acceleration;
    	return mode;
    }
    
    
    public double velocitykoeff(){
        return velocity * koeff;
    };
    public Shift shift(){ 
        return shifts[index];
    }

    public String toString(){
        String sh = "";
        for (Shift shift : shifts){
            if (shift==null){
                sh += "null";
            }else{
                sh += "("+shift.time+","+shift.velocity+","+shift.acceleration+"), ";
            }            
        }
        return "["+sh+"], "+index+", "+progress; 
    }

    public boolean finished() {
        return (shifts[shifts.length-1] == null) && (index == shifts.length-2 && progress == 1);
    }

    public static class Shift {    
        public final double time;
        public final double velocity;    
        public final double acceleration;    

        public Shift(double time, double velocity, double acceleration){
            this.time = time;
            this.velocity = velocity;   
            this.acceleration = acceleration;   
        }
        
        @Override
        public boolean equals(Object obj) {
        	if (!(obj instanceof Shift)){return false;}
        	Shift shift = (Shift)obj;        	
        	return time==shift.time && velocity==shift.velocity && acceleration==shift.acceleration;
        }
    }
    
    @Override
    public boolean equals(Object object) {
    	if (!(object instanceof Mode)){return false;}
    	Mode mode = (Mode)object;
    	if (this.shifts.length!=mode.shifts.length){return false;}
    	for (int i=0; i<this.shifts.length; i++){
    		Shift sh1 = this.shifts[i];
    		Shift sh2 = mode.shifts[i];
    		if (!sh1.equals(sh2)){
    			return false;
    		}
    	}
    	return true;
    }
}
