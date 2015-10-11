package featurea.android;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import dalvik.system.PathClassLoader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.Log;
import featurea.Music;
import featurea.Sound;
import featurea.Sprite1;
import featurea.Loader;

import static featurea.device.audio;

public class AndroidResourceLoader extends Loader{


	private final static Map<String, Integer> soundById = new HashMap<String, Integer>();
	private final static Map<String, Integer> animationById = new HashMap<String, Integer>();
	public AndroidResourceLoader() {
		try {
			String packageName = featurea.android.GameActivity.instance.getApplicationContext().getPackageName();
			String rawClassName = packageName+".R$raw";
			String drawableClassName = packageName+".R$drawable";
			String apkName = featurea.android.GameActivity.instance.getPackageManager().getApplicationInfo(packageName, 0).sourceDir;
			PathClassLoader myClassLoader = new dalvik.system.PathClassLoader(apkName, ClassLoader.getSystemClassLoader());

			Class<?> rawClass = Class.forName(rawClassName, true, myClassLoader);
			for (Field field : rawClass.getFields()) {
				soundById.put(field.getName(), (Integer) field.get(null));
			}

			Class<?> drawableClass = Class.forName(drawableClassName, true, myClassLoader);
			for (Field field : drawableClass.getFields()) {
				animationById.put(field.getName(), (Integer) field.get(null));

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// Sound
	@Override
	protected void load(Sound sound) {
		super.load(sound);		
		sound.resource = new AndroidSound();	
		((AndroidSound)sound.resource).resourceID = soundById.get(sound.ogg);
		((AndroidSound)sound.resource).soundID = ((AndroidAudio)audio).soundPool.load(featurea.android.GameActivity.context, ((AndroidSound)sound.resource).resourceID, 1);
		Log.d("AndroidResourceLoader", "Load sound = "+sound+", "+((AndroidSound)sound.resource));
	}
	@Override
	protected void release(Sound sound) {	
		super.release(sound);
		((AndroidAudio)audio).soundPool.unload(((AndroidSound)sound.resource).soundID);
		Log.d("AndroidResourceLoader", "Release sound = "+sound);
	}

	// Music
	@Override
	protected void load(Music music) {
		super.load(music);		
		music.resource = MediaPlayer.create(featurea.android.GameActivity.context, soundById.get(music.ogg));
		Log.d("AndroidResourceLoader", "Load music = "+music);
	}
	@Override
	protected void release(Music music) {
		super.release(music);		
		music.resource = null;
		Log.d("AndroidResourceLoader", "Release music = "+music);
	}

	// Sprite1
	@Override
	protected void load(Sprite1 sprite1) {
		super.load(sprite1);
		Integer id = animationById.get(sprite1.png);
		if (id!=null){
			sprite1.resource = (Bitmap)BitmapFactory.decodeResource(featurea.android.GameActivity.res, id);
			sprite1.width = ((Bitmap) sprite1.resource).getWidth();
			sprite1.height = ((Bitmap) sprite1.resource).getHeight();
		}		
		Log.d("AndroidResourceLoader", "Load sprite = "+sprite1);
	}
	@Override
	protected void release(Sprite1 sprite1) {
		super.release(sprite1);
		sprite1.resource = null;
		sprite1.width = 0;
		sprite1.height = 0;		
		Log.d("AndroidResourceLoader", "Release sprite = "+sprite1);
	}

	// game.navigation
	@Override
	public void exitGame() {
		featurea.android.GameActivity.instance.finish();
	}

	@Override
	public void releaseAll() {		
		super.releaseAll();
		((AndroidAudio)audio).soundPool.release();		
	}

}
