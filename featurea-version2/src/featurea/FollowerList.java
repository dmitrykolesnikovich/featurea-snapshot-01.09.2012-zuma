package featurea;

import java.util.ArrayList;

public class FollowerList extends ArrayList<Animation>{

	private final Animation listHolder; 
    public FollowerList(Animation lead) {
        this.listHolder = lead;
    }
	
	@Override
	public boolean add(Animation follower) {
    	if (follower != null) {    	
    		if (follower.lead!=null){
    			follower.lead.unlead(follower); // old lead
    		}    		
    		follower.lead = listHolder; 		// new lead
    		// (1)
    		if (listHolder.screen!=null){
    			listHolder.screen.add(follower);
    		}
        }
    	return super.add(follower);	    	
    }
    
	@Override
    public boolean remove(Object object) {
		if (!(object instanceof Animation)) {return false;}
		Animation follower = (Animation)object;
    	if (follower!=null){
    		follower.lead = null;			// this lead
    		// (1)
    		if (listHolder.screen!=null){
    			listHolder.screen.remove(follower);
    		}        		 
    	}
    	return super.remove(follower);
    }

	@Override
    public void clear() {        
        for (Animation follower : this){
        	if (follower!=null){
        		follower.lead = null;			// this lead
        		// (1)
        		if (listHolder.screen!=null){
        			listHolder.screen.remove(follower);
        		}    		
        	}
        }        
        super.clear();    
    }

    @Override
    public String toString() {
        String str = "";        
        for (Animation follower : this){
        	str += follower.toString() + ";";
        }       
        return str;
    }
		    
}