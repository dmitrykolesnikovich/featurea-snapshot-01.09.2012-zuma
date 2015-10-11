package data.screen;

import data.sprites;
import featurea.Animation;
import featurea.Curtain;
import featurea.Vector3;

public class LoadingScreen extends Curtain{

	public Animation icon = new Animation(sprites.icon, new Vector3(10, 160, 70), null, null, null, null);
	public LoadingScreen(){		
		this.add(icon);
	}
	
	@Override
	public void progress(int percent) {
		icon.vector.plus(1,0,0);		
	}
	
}
