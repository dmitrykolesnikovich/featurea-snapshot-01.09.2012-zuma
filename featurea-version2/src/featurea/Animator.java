package featurea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static featurea.Axis.Turn;
import static featurea.Mode.Shift;

public class Animator {
	private static final long ONE_CADR_TIME = 90l;

	public static void update() {
		// tick
		if (game.screen!=null){
			processTicks(game.screen.children, game.frameTime);
			updateSprites(game.screen.children, game.frameTime);
			updateMotions(game.screen.children, game.frameTime);
		}    	
		if (game.play!=null && game.play.alive){
			processTicks(game.play.children, game.frameTime);
			updateSprites(game.play.children, game.frameTime);
			updateMotions(game.play.children, game.frameTime);
			game.play.update();
		}    	
	}    

	private static void processTicks(List<Animation> list, long frameTime){    	
		for (Animation animation : list) {
			animation.lifeTime += frameTime;
			// Callback ------------------------------------------------------
			animation.timer(animation.lifeTime);
			// ---------------------------------------------------------------
		}
	}

	private static void updateSprites(List<Animation> list, final long duration) {
		for (Animation animation : list) {
			if (animation != null && animation.sprite() != null) {
				int buffer = (int) (animation.progress / ONE_CADR_TIME);
				while (buffer >= animation.sprite.length) {
					animation.progress -= animation.sprite.length * ONE_CADR_TIME;
					buffer = (int) (animation.progress / ONE_CADR_TIME);
				}
				animation.index = buffer;
				// Callback ------------------------------
				animation.index(animation.index);
				// ---------------------------------------
				animation.progress += duration;

				// trick with 'stop-animation' feature -------------------------------
				if (animation.sprite1().resource == null && animation.index != 0){
					animation.index--;
					animation.progress -= duration; //!
				}
				// --------------------------------------------------------------------

			}
		}
	}

	private static void updateMotions(List<Animation> list, final long finalFrameTime) {
		// (2)
		for (Animation animation : list) {        	
			long frametime = finalFrameTime;
			if (animation != null && animation.vector!=null && animation.mode != null && animation.mode.shifts != null && animation.graph != null && animation.graph.vectors != null) {
				float frameStep = 0;

				// evaluate 'pathLength'
				while (true) {
					Shift shift = animation.mode.shift();
					if (shift != null) {
						final double time = shift.time * (1 - animation.mode.progress);
						if (frametime > time) {
							frametime -= time;

							frameStep += (float) (shift.acceleration * Math.pow(time, 2) / 2 + shift.velocity * time);
							animation.mode.index++;
							if (animation.mode.index >= animation.mode.shifts.length) {
								animation.mode.index = 0;
							}
							animation.mode.progress = 0;

							if (animation.mode.shift() != null) {
								animation.mode.velocity = animation.mode.shift().velocity;
								animation.mode.acceleration = animation.mode.shift().acceleration;
							}
						} else {
							frameStep += (float) (shift.acceleration * Math.pow(frametime, 2) / 2 + shift.velocity * frametime);
							// pathLength += (float)(shift.velocity * frametime);

							animation.mode.progress += frametime / animation.mode.shift().time;
							animation.mode.velocity += shift.acceleration * frametime;
							break;
						}
					} else {
						// mode completed.
						animation.mode.index = animation.mode.shifts.length - 2;
						animation.mode.progress = 1;
						// area.mode.velocity = (area.mode.shift().velocity + area.mode.shift().acceleration * area.mode.shift().time); 
						animation.mode.velocity = animation.mode.shift().velocity;
						// area.mode.acceleration = area.mode.shift().acceleration;	
						break;
					}
				}

				/** update 'area.vector' with 'area.graph' and 'frameStep' */
				if (frameStep != 0) {
					double sign = Math.signum(frameStep);
					frameStep = Math.abs(frameStep);
					while (true) {
						Vector3 vector = animation.graph.vectors[animation.graph.index];
						if (sign > 0) { // direct motion
							if (vector != null) {
								double length = vector.dlength * (1 - animation.graph.progress);
								if (frameStep > length) {
									frameStep -= length;

									move(animation, vector, length);

									animation.graph.index++;
									if (animation.graph.index >= animation.graph.vectors.length) {
										animation.graph.index = 0;
									}
									animation.graph.progress = 0;
								} else {
									move(animation, vector, frameStep);

									animation.graph.progress += frameStep / animation.graph.vector().dlength;
									break;
								}
							} else { // graph finished
								animation.graph.index = animation.graph.vectors.length - 2;
							animation.graph.progress = 1;
							break;
							}
						} else if (sign < 0) { // rollback
							if (vector != null) {
								double length = vector.dlength * (animation.graph.progress);
								if (frameStep > length) {
									frameStep -= length;

									move(animation, vector, -length);

									animation.graph.index--;
									if (animation.graph.index < 0) {
										animation.graph.index = animation.graph.vectors.length - 1;
									}
									animation.graph.progress = 1;
								} else {
									move(animation, vector, -frameStep);

									animation.graph.progress -= frameStep / animation.graph.vector().dlength;
									break;
								}
							} else { // graph finished.							
								animation.graph.index = 0;
							animation.graph.progress = 0;
							break;
							}
						}
					}
				}
			}

			/** Rotation. */
			if (animation != null && animation.axis != null && animation.axis.turns != null) {
				// evaluate 'degree' relative to 'frametime'
				while (true) {
					Turn turn = animation.axis.turn();
					if (turn != null) {
						float koeff = (1 - animation.axis.progress);
						final float time = koeff * animation.axis.turn().time;
						if (frametime > time) {
							frametime -= time;

							animation.axis.degree += koeff * animation.axis.turn().degree;

							animation.axis.index++;
							if (animation.axis.index >= animation.axis.turns.length) {
								animation.axis.index = 0;
							}
							animation.axis.progress = 0;
						} else {
							animation.axis.progress = frametime / animation.axis.turn().time;
							animation.axis.degree += animation.axis.progress * animation.axis.turn().degree;
							break;
						}
					} else {
						// rotation completed.
						animation.axis.index = animation.axis.turns.length - 2;
						animation.axis.progress = 1;
						break;
					}
				}
			}

		}

		knownLeads.clear();
		while(knownLeads.size()!=list.size()){
			for (Animation animation : list) {
				if (animation.lead==null || knownLeads.contains(animation.lead)){
					knownLeads.add(animation);
					processFollowerWithKnownLead(animation);        			
				}
			}
		}
		for (Animation animation : list) {
			animation.oldVector = animation.vector;
			if (animation.axis != null) {
				animation.axis.oldDegree = animation.axis.degree;
			}
		}
	}
	private static Set<Animation> knownLeads = new HashSet<Animation>();
	private static void processFollowerWithKnownLead(Animation follower){
		Animation lead = follower.lead;
		if (lead != null && follower.vector!=null) {
			// 1. vector
			try{
				follower.vector.plus(lead.vector.dx() - lead.oldVector.dx(), lead.vector.dy() - lead.oldVector.dy(), lead.vector.dz() - lead.oldVector.dz());
			}catch(Exception ex){
				ex.printStackTrace();
			}                
			// 2. axis				
			double delta = lead.axis.degree - lead.axis.oldDegree;
			Axis.rotate(lead.vector, delta, follower.vector);
			if (follower.axis != null) {
				follower.axis.degree += delta;
			}
		}
	}

	private static void move(Animation animation, Vector3 directon, double length) {
		double rad = -animation.graph.degree * (Math.PI / 180);
		// rotate (x1; y1; z) to (x2; y2; z)
		double x1 = directon.dx();
		double y1 = directon.dy();
		double x2 = (x1 * Math.cos(rad) - y1 * Math.sin(rad));
		double y2 = (x1 * Math.sin(rad) + y1 * Math.cos(rad));
		animation.vector.plus(x2, y2, directon.dz(), length);
	}

	/*package*/ static List<Animation> animations91(List<Animation> temp) {         
		Collections.sort(temp, new Comparator<Animation>() {
			@Override
			public int compare(Animation animation1, Animation animation2) {			
				if (animation1 == animation2){
					return 0;
				}     			
				if (animation1 == null){ // null < whatever
					return 1;
				}
				if (animation2 == null){ // whatever > null 
					return -1;
				}
				if (animation1.vector.dz() == animation2.vector.dz()){ 
					return 0; // return 1; => Error: 'Comparison method violates its general contract'
				}
				if (animation1.vector.dz() > animation2.vector.dz()){
					return -1;
				}else { // if (area1.vector.dz < area2.vector.dz)
					return 1;
				}						
			}
		});
		return temp;
	} 	


	public static void processTouches(final Touch touch) {
		List<Animation> temp = new ArrayList<Animation>(){			
			// for debug
			@Override
			public String toString() {
				String str = "";
				for (Animation animation : this){str += animation.sprite1().png + ", ";}
				return str;
			};
		};		
		if (game.play==null  &&  game.screen!=null){
			temp.addAll(game.screen.children);
		}else if (game.play!=null  &&  game.screen==null){
			temp.addAll(game.play.children);
		}else if (game.play!=null  &&  game.screen!=null){
			temp.addAll(game.play.children);
			temp.addAll(game.screen.children);
		}
		List<Animation> animations91 = animations91(temp); 		
		for (Animation animation : animations91){ 	
			if (game.play!=null && game.play.children.contains(animation)){
				// convert gameplay areas to local coords
				animation.vector.dx(animation.vector.dx() - game.play.camera.dx());
				animation.vector.dy(animation.vector.dy() - game.play.camera.dy());
			}
		}

		switch(touch.type){
		case DOWN: {
			for (Animation animation : animations91) {        			 
				if (touch.cross(animation)) {
					animation.down = true;		
					animation.down(); checkForOnScreen(animation, touch);								
					break;
				}
			}  
			break;
		}
		case MOVE:{
			for (Animation animation : animations91) {             	 
				if (touch.cross(animation)) {
					animation.down = true;
					animation.move(); checkForOnScreen(animation, touch);					
				}else{
					if (animation.down){
						animation.up(); checkForOnScreen(animation, touch);
						animation.down = false;
					}
				}
			}
			break;
		}
		case UP:{
			for (Animation animation : animations91) {                	 
				if (animation.down) {
					animation.down = false;
					animation.up(); checkForOnScreen(animation, touch);					
					if (touch.cross(animation)) {						
						animation.click(); checkForOnScreen(animation, touch);
						break;
					}
				}                	 
			}
			break;
		}
		}    	 

		System.out.println("'Animator' touch."+touch.type);
		//
		if (game.play!=null && !touch.onScreen){
			// TODO: add 'touch' on 'play' as 'Animation'
			game.touch = touch;		
			game.touch.x += game.play.camera.dx();
			game.touch.y += game.play.camera.dy();
		}else{
			// TODO: don't add touch on 'game.play'
			game.touch = null;
		}
	}
	
	// 'touch.onScreen = true;'
	private static void checkForOnScreen(Animation animation, Touch touch){
		 if (featurea.game.screen.children.contains(animation)){
			 touch.onScreen = true;
		 }
	}
}