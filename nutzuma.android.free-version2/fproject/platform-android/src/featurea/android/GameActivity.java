package featurea.android;

import java.util.HashMap;
import java.util.Map;

import featurea.device;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import nutzuma.android.free.R;

/*
1. 'EXIT' button
	onStop
	onDestroy
2. launch icon
	onCreate
3. 'BACK' button
	onBackPressed
	onStop
	onDestroy
4. launch icon
	onCreate
5. 'HOME' button
	onStop
6. launch icon
	onStart
*/

public class GameActivity extends Activity {
	private final String PREFS_NAME = "NutsAgainPreferences";	
	
	/*package*/ static Context context;
	/*package*/ static Resources res;
	/*package*/ static GameActivity instance;
	/*package*/ static float density;	    

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d("GameActivity", "onCreate");
        
        // Global
        GameActivity.context = getApplicationContext();
        GameActivity.res = getResources();                
        GameActivity.instance = this;
        DisplayMetrics dm = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(dm);
    	GameActivity.density = dm.density;
    	
        // GameView
        setContentView(R.layout.main);
    	GameView view = (GameView)findViewById(R.id.GameView);
    	view.invalidate();   
    }
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("GameActivity", "onStart");
		initDevice();		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Map<String, ?> map = settings.getAll();
        if (map!=null){
        	game.navigation.readSettings(map);
        }else{
        	game.navigation.readSettings(new HashMap<String, Object>());
        }        
		game.navigation.start();
	}	
	
	@Override
    protected void onStop() {	
    	super.onStop();   	  
    	// 'game.navigation.onStop'
    	game.navigation.stop();    	
    }
    @Override
    protected void onDestroy() {    	
        super.onDestroy();
        
        // 'game.navigation.onDestroy'        
    	Map<String, Object> map = new HashMap<String, Object>();
    	game.navigation.writeSettings(map);           
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	SharedPreferences.Editor editor = settings.edit();
    	for (String key : map.keySet()){
    		Object value = map.get(key);
    		if (value instanceof Integer){
    			editor.putInt(key, (Integer)value);
    		}else if (value instanceof Long){
    			editor.putLong(key, (Long)value);
    		}else if (value instanceof String){
    			editor.putString(key, (String)value);
    		}else if (value instanceof Boolean){
    			editor.putBoolean(key, (Boolean)value);
    		}else if (value instanceof Float){
    			editor.putFloat(key, (Float)value);
    		}     
    	}
        editor.commit();   
       
    }
    
    private void initDevice(){
		device.audio = new AndroidAudio();
		device.painter = new AndroidPainter();
        device.loader = new AndroidResourceLoader();
        device.display = new AndroidDisplay();
	}
   
    
}