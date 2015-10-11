package featurea;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import featurea.Sound;

public abstract class Loader {

	public Loader(){
		try{
			for (final Field field : data.sprites.class.getFields()){
				String fieldName = field.getName();	        	        		        	
				Sprite1[] sprite1 = (Sprite1[])field.get(null);
				if (sprite1.length>1){
					String index;
					for (int i=0; i<sprite1.length; i++){
						if (i<=9){
							index = "0"+i;
						}else{
							index = ""+i;
						}
						sprite1[i] = new Sprite1(fieldName+"_"+index);	        			
					}	 
				}else if (sprite1.length==1){
					sprite1[0] = new Sprite1(fieldName);	   
				}
				Log.d("GameActivity", "Init sprite = "+fieldName);
				field.set(null, sprite1);	     
			}   
			for (final Field field : data.sounds.class.getFields()){ 
				String fieldName = field.getName();	       
				Log.d("GameActivity", "Init sound = "+fieldName);
				field.set(null, new Sound(fieldName));			               
			}
			for (final Field field : data.musics.class.getFields()){      
				String fieldName = field.getName();	       
				Log.d("GameActivity", "Init music = "+fieldName);
				field.set(null, new Music(fieldName));			               
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public abstract void exitGame();
	protected void load(Music music){
		if (device.audio.musics.contains(music)){
			return;
		}else{
			device.audio.musics.add(music);
		}
	};
	protected void load(Sound sound){
		if (device.audio.sounds.contains(sound)){
			return;
		}else{
			device.audio.sounds.add(sound);
		}
	};	
	protected void load(Sprite1 sprite1){
		if (device.painter.sprites.contains(sprite1)){
			return;
		}else{
			device.painter.sprites.add(sprite1);
		}
	};
	protected void release(Music music){
		device.audio.musics.remove(music);
	};
	protected void release(Sound sound){
		device.audio.sounds.remove(sound);
	};	
	protected void release(Sprite1 sprite1){
		device.painter.sprites.remove(sprite1);
	};	

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
	public void release(Sprite1[]... sprites){		
		if (sprites!=null){
			for (Sprite1[] sprite : sprites){	
				for (Sprite1 sprite1 : sprite){
					release(sprite1);
				}				
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
	public void load(Sprite1[]... sprites){
		if (sprites!=null){
			for (Sprite1[] sprite : sprites){	
				for (Sprite1 sprite1 : sprite){
					load(sprite1);
				}				
			}
		}
	}			
	public void releaseAll(){
		// to avoid 'ConcurentModificationException'
		List<Sound> soundResources2 = new ArrayList<Sound>();
		soundResources2.addAll(device.audio.sounds);		
		List<Music> musicResources2 = new ArrayList<Music>();
		musicResources2.addAll(device.audio.musics);		
		List<Sprite1> spriteResources2 = new ArrayList<Sprite1>();
		spriteResources2.addAll(device.painter.sprites);
		
		for (Sound sound : soundResources2){
			release(sound);
		}
		for (Music music : musicResources2){
			release(music);
		}
		for (Sprite1 sprite1 : spriteResources2){
			release(sprite1);
		}
		device.audio.sounds.clear();
		device.audio.musics.clear();
		device.painter.sprites.clear();
	}
}