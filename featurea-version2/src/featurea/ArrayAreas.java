package featurea;

public class ArrayAreas {
	
	private Object[][] elements = new Object[900][];
	private int last = 0;	
    
    
	public int indexOf(Object[] element){
    	int index = -1;
    	for (int i=0; i<last; i++){
    		if (element == elements[i]){
    			index = i;
    		}
    	}
    	return index;
	}
	
	
	public Object[] element(int index){return elements[index];}
	public Object[][] elements(){return elements;}
	
   
    
    public void add(Object[] element) {
        add(last, element);
    }

    public void add(int index, Object[] element) {
    	if (element!=null){
            
            // shift to right
        	for (int i=last+1; i>index; i--){
        		elements[i] = elements[i-1];
        	}
        	elements[index] = element;
        	last++;
    	}
        
    }
    
    public void remove(Object[] element) {
    	int index = indexOf(element);
    	// shift to left
    	if (index!=-1){
    		for (int i=index; i<last-1; i++){
    			elements[i] = elements[i+1];
    		}
    		elements[last-1] = null;
        	last--;
    	}
    }
    
    
    public void clear() {   
    	for (int i=0; i<last; i++){
    		elements[i] = null;    		
    	}
    	last = 0;
    }
}
