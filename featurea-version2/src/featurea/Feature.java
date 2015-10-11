package featurea;

public abstract class Feature {
	
    public Object[][] figures;	
    public ArrayAreas family; // Trick there   
    
    public abstract boolean check();
    public abstract void make();
    
    // for Featurea Studio ----------------------
    public boolean make;
    public boolean check;
    public long maxtime;
    // ------------------------------------------
    
    @Override
    public String toString() {
        return getClass().getName();
    }     
    
    
    
    
}