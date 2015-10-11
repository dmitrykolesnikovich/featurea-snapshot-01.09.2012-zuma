package data.screen;

import areas.ScoreupText;
import type.ScoresText;
import data.controls;
import data.modes;
import data.musics;
import data.parameters;
import data.sounds;
import data.sprites;
import data.values;
import data.controls.LifePanel;
import featurea.Animation;
import featurea.Graph;
import featurea.device;
import featurea.Vector3;
import game.navigation;
import game.settings;

public class Hud extends Animation {

	public LifePanel lifePanel;
	public Animation score;
	public boolean active = true;

	public Hud(int z) {
		super(null, null, null, null, null, null);
		lead(new Animation(sprites.header, new Vector3(240, 10, 0), null, null, null, null));

		lead(new Animation(sprites.pause_button, new Vector3(465, 10, 1), null, null, null, null ) {
			@Override protected void up() {
				if (active){sprite(sprites.pause_button);}                
			}
			@Override protected void down() {
				if (active){sprite(sprites.pause_button_down);}
			}
			@Override protected void move() {
				if (active){sprite(sprites.pause_button_down);}
			}
			@Override protected void click() {       
				if (active){
					sprite(sprites.pause_button);
					device.audio.play(sounds.click_the_button);   
					navigation.pause();
				}
			}
		});

		// Score
		this.score = new Animation(null, new Vector3(4, 18, 2), null, null, null, null);
		this.score.text = new ScoresText(settings.totalScore);
		lead(score);

		lifePanel = new controls.LifePanel(new Vector3(365, 12.5, 2), settings.lives);
		lead(lifePanel);        

		this.shift(new Vector3(0,0,z));

	}       

	
	public void scoreUp(int score, Vector3 point) {
		settings.totalScore += score;
		parameters.levelScore += score; 
		this.score.text = new ScoresText(settings.totalScore);
		device.audio.play(sounds.score_up);   

		// Scoreup text
		Vector3 tp = new Vector3(point.dx(), point.dy(), 10);        
		ScoreupText scoreupText = new ScoreupText("+" + score, tp);
		scoreupText.graph = new Graph(new Vector3[]{new Vector3(0,-20,0), null}, 0, 0);
		scoreupText.mode = modes.move();        
		navigation.level().addScoreupText(scoreupText);

	}

	public boolean addMoreNuts() {
		return parameters.levelScore < values.levelFillScore;
	}
}