package data.screen;

import data.musics;
import data.points;
import data.sounds;
import data.sprites;
import featurea.Animation;
import featurea.device;
import game.levels;
import game.navigation;
import game.settings;

public class LevelUpScreen extends Animation {

    public LevelUpScreen() { 
    	super(null, null, null, null, null, null);
        // menu_background
        lead(new Animation(sprites.menu_background, points.menu_background_point(), null, null, null, null));

        // play_button
        lead(new Animation(sprites.continue_button, points.play_button_point(), null, null, null, null) {

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
                device.audio.play(sounds.click_the_button);
                settings.levelNumber++;
                navigation.nextLevel(levels.getLevel(settings.levelNumber));                   
            }
        });

        // sound_on_button
        lead(data.controls.soundControl());

        // exit_button
        lead(new Animation(sprites.exit_button, points.exit_button_point(), null, null, null, null) {

            @Override
            protected void up() {
                sprite(sprites.exit_button);
            }

            @Override
            protected void down() {
                sprite(sprites.exit_button_down);
            }

            @Override
            protected void click() {
                device.audio.play(sounds.click_the_button);
                navigation.exit();
            }
        });
    }
    
}
