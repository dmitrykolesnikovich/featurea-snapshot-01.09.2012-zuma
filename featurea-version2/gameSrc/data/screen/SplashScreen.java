package data.screen;

import data.points;
import data.sprites;
import featurea.Animation;
import game.navigation;

public class SplashScreen extends Animation {

    public SplashScreen() {    	
    	super(null, null, null, null, null, null);
    	lead(new Animation(null, points.point240x160x0(), null, null, null, null) {
            @Override
            protected void timer(long lifeTime) {
                if (lifeTime > 0) {                	
                	navigation.menu();
                }
            }
        });
    }    
    
}