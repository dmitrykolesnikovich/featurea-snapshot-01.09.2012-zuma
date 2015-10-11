package featurea;

import java.util.ArrayList;

public class Screen{
	public final ArrayList<Animation> children = new ArrayList<Animation>();
	
	// Add
	public boolean add(Animation animation){
		if (!children.contains(animation)){
			addThisAndFollowersOf(animation);
			return true;
		}
		return false;	
	}	
	private void addThisAndFollowersOf(Animation lead){
		children.add(lead);
		lead.screen = this;
		for (Animation follower : lead.followers){				
			addThisAndFollowersOf(follower);
		}			
	}
	
	// Remove
	public boolean remove(Object object){
		if (!(object instanceof Animation)){return false;}
		removeThisAndFollowersOf((Animation)object);
		return true;
	}		
	private void removeThisAndFollowersOf(Animation lead){
		children.remove(lead);
		lead.screen = null;
		for (Animation follower : lead.followers){
			removeThisAndFollowersOf(follower);
		}			
	}
	

	public void clear() {
		for (Animation animation : children){
			animation.screen = null;
		}
		children.clear();
	}
	//
	public String toString() {
		String str = "";
		for (Animation animation : children){
			str += animation.sprite1().png + ", ";
		}
		return str;
	};	
}