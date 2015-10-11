package featurea;

public class Sprite1 {  
    
    public Object resource; // TODO: make 'package' visibility
    public final String png;
    
    public Sprite1(String png){ // TODO: public Sprite1(String png, Object resource)
        this.png = png;
    }

    @Override
    public String toString() {    	
    	return this.png;    	
    }
    
    public float width;
    public float height;
    
}