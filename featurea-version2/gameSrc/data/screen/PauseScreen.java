package data.screen;

import static featurea.device.audio;
import data.points;
import data.sounds;
import data.sprites;
import featurea.Animation;
import featurea.Vector3;
import featurea.device;
import game.navigation;

public class PauseScreen extends Animation {

    public PauseScreen(int z) {    	
    	super(null, null, null, null, null, null);
        lead(new Animation(sprites.go_to_menu_button, points.point50x20x0(), null, null, null, null) {

            @Override
            protected void up() {
                sprite(sprites.go_to_menu_button);
            }

            @Override
            protected void down() {
                sprite(sprites.go_to_menu_button_down);
            }

            @Override
            protected void click() {
                device.audio.play(sounds.click_the_button);
                navigation.menu();     
            }
        });
        lead(new Animation(sprites.continue_button, points.point260x160x0(), null, null, null, null ) {
            @Override
            protected void up() {
                sprite(sprites.continue_button);
            }
            @Override
            protected void down() {
                sprite(sprites.continue_button_down);
            }
            @Override
            protected void click() {   
            	audio.play(sounds.click_the_button);
                navigation.resume();          
            }
        });
        
        this.shift(new Vector3(0,0,z));
    }
    
    
}