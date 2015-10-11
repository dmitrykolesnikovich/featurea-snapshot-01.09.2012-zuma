package data;

import featurea.Animation;
import featurea.device;
import featurea.Sprite1;
import featurea.Vector3;

public class controls {

	public final static Animation soundControl() {
		Sprite1[] sprite = null;
		if (device.audio.volume==0){
			sprite = sprites.sound_off_button;
		}else{
			sprite = sprites.sound_on_button;
		}
		return new Animation(sprite, points.sound_button_point(), null, null, null, null) {

			@Override
			protected void up() {
				if (this.sprite() == sprites.sound_on_button_down) {
					this.sprite(sprites.sound_on_button);
				} else if (this.sprite() == sprites.sound_off_button_down) {
					this.sprite(sprites.sound_off_button);
				}
			}

			@Override
			protected void down() {
				if (this.sprite() == sprites.sound_on_button) {
					this.sprite(sprites.sound_on_button_down);
				} else if (this.sprite() == sprites.sound_off_button) {
					this.sprite(sprites.sound_off_button_down);
				}
			}

			@Override
			protected void click() {
				if (this.sprite() == sprites.sound_on_button) {
					device.audio.volumeAll(0);
					device.audio.volume = 0f;
					this.sprite(sprites.sound_off_button);
				} else if (this.sprite() == sprites.sound_off_button) {
					device.audio.volumeAll(0.2f);
					device.audio.volume = 0.2f;
					device.audio.play(sounds.click_the_button);
					this.sprite(sprites.sound_on_button);
				}
			}
		};
	}

	public static class LifePanel extends Animation{
		public LifePanel(Vector3 point, int lifes){
			super(null, point, null, null, null, null);
			reset(lifes);
		}
		public void reset(int lifes){ // lifes = [0..3]
			this.followers.clear();
			if (lifes >= 1){
				lead(new Animation(sprites.life, vector.copy(), null, null, null, null));     
				if (lifes >= 2){
					Vector3 p2 = vector.copy();
					p2.plus(25, 0, 0);
					lead(new Animation(sprites.life, p2, null, null, null, null));    
					if (lifes >= 3){
						Vector3 p3 = p2.copy();
						p3.plus(25, 0, 0);
						lead(new Animation(sprites.life, p3, null, null, null, null));
					} 
				}
			}  
		}		
	}
}