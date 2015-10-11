package featurea;

import java.util.HashSet;
import java.util.Set;

public abstract class Audio {
	
	public void load(Sound sound){
		if (!sounds.contains(sound)){
			sounds.add(sound);
			device.loader.load(sound);
		}		
	}
	
	public void release(Music... music){		
		if (music!=null){
			for (Music mus : music){					
				release(mus);
			}
		}
	}
	public void release(Sound... sounds){		
		if (sounds!=null){
			for (Sound sound : sounds){					
				release(sound);
			}
		}
	}
	public void load(Music... musics){		
		if (musics!=null){
			for (Music music : musics){					
				load(music);
			}
		}
	}
	public void load(Sound... sounds){		
		if (sounds!=null){
			for (Sound sound : sounds){					
				load(sound);
			}
		}
	}
	
	public float volume = 0.2f;
	/*package*/ Set<Sound> sounds;
	/*package*/ Set<Music> musics;
	public Audio(){
		sounds = new HashSet<Sound>();
		musics = new HashSet<Music>();
	}
	
	public abstract void play(Sound sound);
	public abstract void play(Music music);
	public abstract void pause(Sound sound);
	public abstract void pause(Music music);
	public abstract void resume(Sound sound);
	public abstract void resume(Music music);
	public abstract void stop(Sound sound);
	public abstract void stop(Music music);
	public abstract void volume(Sound sound, float volume);
	public abstract void volume(Music music, float volume);	
	// All		
	public final void pauseAll() {		
		for (Sound sound : sounds){
			pause(sound);
		}
		for (Music music : musics){
			pause(music);
		}			
	}		
	public final void resumeAll() {
		for (Sound sound : sounds){
			resume(sound);
		}
		for (Music music : musics){
			resume(music);
		}		
	}	
	
	public final void stopAll() {
		for (Sound sound : sounds){
			stop(sound);
		}
		for (Music music : musics){
			stop(music);
		}				
	}	
	
	public final void volumeAll(float volume) {
		for (Sound sound : sounds){
			volume(sound, volume);
		}
		for (Music music : musics){
			volume(music, volume);
		}				
	}
}