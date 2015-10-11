package featurea;

import static featurea.device.display;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Painter {
	
	public List<Sprite1> sprites;
	public Painter(){
		sprites = new ArrayList<Sprite1>();
	}	
	
	private List<Animation> buffer;
	public void paint(){
		List<Animation> temp = new ArrayList<Animation>();		
		if (game.play==null  &&  game.screen!=null){
			temp.addAll(game.screen.children);
		}else if (game.play!=null  &&  game.screen==null){
			temp.addAll(game.play.children);
		}else if (game.play!=null  &&  game.screen!=null){
			temp.addAll(game.play.children);
			temp.addAll(game.screen.children);
		}
		List<Animation> animations19 = animations19(temp);
		
		// Log animations
		/*if (game.DEBUG){
			if (!animations19.equals(buffer)){
				buffer = animations19;
				System.out.println("'Painter' animations19 -----------------------");				
				for (Animation a : animations19){
					System.out.println(a);
				}				
				System.out.println("----------------------------------------------");
			}
		}*/
		
		// 
		final double ratio = display.ratio();
		final double density = display.density();
		double w = 0;
		double h = 0;
		if (display.width()<display.height()){
			w = 320;
			h = 480;
		}else{
			w = 480;
			h = 320;            	
		}     
		w *= ratio;
		h *= ratio;
		for (Animation animation : animations19) {
			if (animation != null && animation.vector != null) {				
				double x = animation.vector.dx() * ratio;
				double y = animation.vector.dy() * ratio;
				if (game.play!=null && game.play.children.contains(animation)){
					double cameraX = game.play.camera.dx() * ratio;
					double cameraY = game.play.camera.dy() * ratio;
					x -= cameraX;
					y -= cameraY;
				}
				if (animation.sprite() != null){
					if (animation.sprite1()!=null && animation.sprite1().resource==null){
						System.out.println("Painter: "+animation.sprite1()+" not loaded.");
					}
					double width = animation.sprite1().width * ratio / density;
					double height = animation.sprite1().height * ratio / density;
					double left = x - width / 2;
					double top = y - height / 2;
					double right = x + width / 2;
					double bottom = y + height / 2;							
					if (left > w || right < 0 || top > h || bottom < 0) {} 
					else {
						paint(animation, x, y, left, top, right, bottom);
					}   
				}
				if (animation.text != null){					
					// System.out.println("Painter: text = "+animation.text);		
					double size = animation.text.size;
					double k = ratio / density;
					size = size * k;
					paint(animation.text, x, y, (int)size);
				}				
			}
		}
	}
	private static List<Animation> animations19(List<Animation> temp) {         
        Collections.sort(temp, new Comparator<Animation>() {
       	@Override
    		public int compare(Animation animation1, Animation animation2) {			
    			if (animation1 == animation2){
    				return 0;
    			}     			
    			if (animation1 == null){ // null < whatever
    				return -1;
    			}
    			if (animation2 == null){ // whatever > null 
    				return 1;
    			}
    			if (animation1.vector.dz() == animation2.vector.dz()){ 
                    return 0; // return 1; => Error: 'Comparison method violates its general contract'
                }
    			if (animation1.vector.dz() > animation2.vector.dz()){
    				return 1;
    			}else { // if (area1.vector.dz < area2.vector.dz)
    				return -1;
    			}						
    		}
        });
        return temp;
    } 	
	
	protected abstract void paint(Animation animation, double x, double y, double left, double top, double right, double bottom);
	protected abstract void paint(Text text, double x, double y, int size);
}
