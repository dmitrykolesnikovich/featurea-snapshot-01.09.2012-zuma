package game;

import java.util.Map;
import type.Level;
import type.ScoresText;
import data.musics;
import data.parameters;
import data.sounds;
import data.sprites;
import data.screen.*;
import featurea.Animation;
import featurea.Screen;
import featurea.device;
import featurea.game;
import static featurea.device.loader;
import static featurea.device.audio;
import static featurea.game.play;

public class navigation {
	
	// Screens:
	public final static Animation pauseScreen = new PauseScreen(30);	
	public final static Animation menuScreen = new MenuScreen(20);
	public final static Hud hud = new Hud(19);
	public final static Animation gameOver = new GameOverScreen();
	public final static Animation levelUpScreen = new LevelUpScreen();
	public final static Screen loadingScreen = new LoadingScreen();
	
	// getters-setters
	public static Level level(){return (Level)game.play;}	

	// Settings:
	public static void readSettings(Map<String, ?> map) {
		settings.levelNumber((Integer)map.get("levelNumber"));
		settings.lives((Integer)map.get("lives"));
		settings.totalScore((Integer)map.get("totalScore"));		
        game.DEBUG = true;
	}
	public static void writeSettings(Map<String, Object> map) {
		map.put("levelNumber", settings.levelNumber);
		map.put("lives", settings.lives);
		map.put("totalScore", settings.totalScore);		
	}
	
	// start & stop
	public static void start() {		
		loader.load(sounds.click_the_button);
		loader.load(sounds.flynut_contacts_nut);
		loader.load(sounds.game_over);
		loader.load(sounds.level_is_comlpeated);
		loader.load(sounds.nut_contacts_coin);
		loader.load(sounds.score_up);
		loader.load(sounds.scrat_throw_nut);
		loader.load(musics.loop);
		loader.load(musics.nuts_are_moving);
		loader.load(sprites.bornarea_debug);
		loader.load(sprites.burn);
		loader.load(sprites.burst);
		loader.load(sprites.coin);
		loader.load(sprites.continue_button);
		loader.load(sprites.continue_button_down);
		loader.load(sprites.dot);
		loader.load(sprites.exit_button);
		loader.load(sprites.exit_button_down);
		loader.load(sprites.featurea_logo);
		loader.load(sprites.fire);
		loader.load(sprites.game_over_screen);
		loader.load(sprites.go_to_menu_button);
		loader.load(sprites.go_to_menu_button_down);
		loader.load(sprites.header);
		loader.load(sprites.icon);
		loader.load(sprites.life);
		loader.load(sprites.menu_background);
		loader.load(sprites.nut_blue);
		loader.load(sprites.nut_blue_burn);
		loader.load(sprites.nut_red);
		loader.load(sprites.nut_red_burn);
		loader.load(sprites.nut_yellow);
		loader.load(sprites.nut_yellow_burn);
		loader.load(sprites.nut_green);
		loader.load(sprites.nut_green_burn);
		loader.load(sprites.nut_purple);
		loader.load(sprites.nut_purple_burn);
		loader.load(sprites.pause_button);
		loader.load(sprites.pause_button_down);
		loader.load(sprites.pile);
		loader.load(sprites.play_button);
		loader.load(sprites.play_button_down);
		loader.load(sprites.scrat);
		loader.load(sprites.sound_off_button);
		loader.load(sprites.sound_off_button_down);
		loader.load(sprites.sound_on_button);
		loader.load(sprites.sound_on_button_down);
		
		if (game.screen==null && game.play==null){
			game.screen = new featurea.Screen();
			navigation.menu();
		}else{
			// as is
			if (game.play!=null){
				loader.load(level().backgroundSprite);
			}		
		}
	}
	public static void stop() {			
		pause();
		audio.stopAll();
        device.loader.releaseAll();
	}		
	
	// --------------------------------------------------------------------------------------
	public static void nextLevel(Level levelScreen) {
		audio.stop(sounds.game_over);
		audio.stop(sounds.level_is_comlpeated);
        audio.play(musics.loop);
        audio.play(musics.nuts_are_moving);
		if (level()!=null){
			loader.release(level().backgroundSprite);
		}
		game.play = levelScreen;
		level().alive = true;    
		loader.load(level().backgroundSprite);		
		
		game.screen.clear();
        game.screen.add(hud);
        hud.active = true;
        hud.score.text = new ScoresText(settings.totalScore);
	}	
	public static void pause() {
		if (level()!=null){
			audio.pause(musics.loop);
			game.screen.add(pauseScreen);
			level().alive = false;
			hud.active = false;
		}else{
			// as is
		}		
	}
	public static void resume() {		
        audio.resumeAll();
		game.screen.remove(pauseScreen);
		level().alive = true;        		
		hud.active = true;
	}
	public static void gameOver() {
		audio.stop(musics.loop);
        audio.stop(musics.nuts_are_moving);
		game.screen.clear();
		game.screen.add(gameOver);
		play = null;
	}
	public static void levelUp() {
		audio.stop(musics.loop);
        audio.stop(musics.nuts_are_moving);
        audio.play(sounds.level_is_comlpeated);	        
		game.screen.clear();
		game.screen.add(levelUpScreen);
		play = null;
	}
	public static void menu() {
		game.screen.clear();
		game.screen.add(menuScreen);
		settings.totalScore -= parameters.levelScore;
		play = null;
	}

	public static void exit() {
		featurea.device.loader.exitGame();		
	}
	
	
	
}


