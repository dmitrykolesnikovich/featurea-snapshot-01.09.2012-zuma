package featurea.android;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import featurea.Audio;
import featurea.Music;
import featurea.Sound;

public class AndroidAudio extends Audio {
	
	/*package*/ SoundPool soundPool;
	public AndroidAudio(){
		soundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
	}
	
	// Sound 
	@Override
	public void play(Sound sound) {
		try{
			((AndroidSound)sound.resource).streamID = soundPool.play(((AndroidSound)sound.resource).soundID, volume, volume, 1, 0, 1);			
		}catch(Exception ex){
			Log.d("AndroidAudio.play", "sound = "+sound);
			Log.d("AndroidAudio.play", "sound.resource = "+sound.resource);
			Log.d("AndroidAudio.play", "((AndroidSound)sound.resource).streamID = "+((AndroidSound)sound.resource).streamID);
			Log.d("AndroidAudio.play", "soundPool = "+soundPool);
			Log.d("AndroidAudio.play", "((AndroidSound)sound.resource).soundID = "+((AndroidSound)sound.resource).soundID);
			ex.printStackTrace();
		}
	}
	@Override
	public void stop(Sound sound) {
		try{
			soundPool.stop(((AndroidSound)sound.resource).streamID);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
	@Override
	public void pause(Sound sound) {
		try{	      
			soundPool.pause(((AndroidSound)sound.resource).streamID);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
	@Override
	public void resume(Sound sound) {	
		try{	
			soundPool.resume(((AndroidSound)sound.resource).streamID);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
	@Override
	public void volume(Sound sound, float volume) {		
		try{
			soundPool.setVolume(((AndroidSound)sound.resource).streamID, volume, volume);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	// Music
	@Override
	public void play(Music music) {		
		try{
			MediaPlayer mediaplayer = (MediaPlayer)music.resource;
			mediaplayer.setLooping(true);
			mediaplayer.setVolume(volume, volume);
			mediaplayer.start();			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
	@Override
	public void pause(Music music) {
		try{
			MediaPlayer mediaplayer = (MediaPlayer)music.resource;
			mediaplayer.pause();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	@Override
	public void resume(Music music) {
		try{
			MediaPlayer mediaplayer = (MediaPlayer)music.resource;
			mediaplayer.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	@Override
	public void stop(Music music) {
		try{
			MediaPlayer mediaplayer = (MediaPlayer)music.resource;
			mediaplayer.stop();
		}catch(Exception ex){
			ex.printStackTrace();
		}					
	}
	@Override
	public void volume(Music music, float volume) {
		try{
			MediaPlayer mediaplayer = (MediaPlayer)music.resource;
			mediaplayer.setVolume(volume, volume);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
}