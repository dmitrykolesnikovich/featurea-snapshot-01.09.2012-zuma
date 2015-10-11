package data.screen;

import data.points;
import data.sounds;
import data.sprites;
import featurea.Animation;
import featurea.device;
import featurea.Vector3;
import game.levels;
import game.navigation;
import game.settings;

public class GameOverScreen extends Animation {

    public GameOverScreen() {
    	super(null, null, null, null, null, null);
        // menu_background
        lead(new Animation(sprites.game_over_screen, points.menu_background_point(), null, null, null, null));

        // play_button
        lead(new Animation(sprites.play_button, new Vector3(234.0, 140.0, 1.0), null, null, null, null) {

            @Override
            protected void up() {
                sprite(sprites.play_button);
            }

            @Override
            protected void down() {
                sprite(sprites.play_button_down);
            }

            @Override
            protected void click() {
                device.audio.play(sounds.click_the_button);                                    
                navigation.nextLevel(levels.getLevel(settings.levelNumber));                   
            }
        });

        // exit_button
        lead(new Animation(sprites.exit_button, new Vector3(234.0, 210.0, 1.0), null, null, null, null) {

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
        
        navigation.hud.lifePanel.reset(settings.lives);
    }
    
}