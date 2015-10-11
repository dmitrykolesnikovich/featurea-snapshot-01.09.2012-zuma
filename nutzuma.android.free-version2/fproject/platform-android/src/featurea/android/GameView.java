package featurea.android;

import featurea.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import featurea.Touch;
import static featurea.device.display;

public final class GameView extends View {
	
	/*package*/ static GameView instance;
	
	public GameView(Context context, AttributeSet set) {
		super(context, set);
		GameView.instance = this;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {  
		// try{  
			final long now = SystemClock.elapsedRealtime();
			if (featurea.game.curtain==null){        
				if (featurea.game.currentTime == 0) {
					featurea.game.currentTime = now;
				}
				final long frameTime = now - featurea.game.currentTime;
				featurea.game.currentTime = now;
				featurea.game.frameTime = frameTime;
				Animator.update();
				AndroidPainter.canvas = canvas;
				featurea.device.painter.paint();
			}		
	 		// Remember: 'touch' is area in 'game.play'
			if (featurea.game.touch!=null){
				if (featurea.game.touch.type==featurea.Touch.Type.UP){
					featurea.game.touch = null;
				}else if (featurea.game.touch.type==featurea.Touch.Type.DOWN){
					featurea.game.touch.type = featurea.Touch.Type.MOVE;
				} 
			}	 
		/*}catch(Exception ex){	
			ex.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			try {
				FileHandler handler = new FileHandler("/sdcard/Android/data/com.featurea/log.txt");
				Logger logger = Logger.getLogger("com.featurea");
				logger.addHandler(handler);
				logger.log(Level.INFO, sw.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}*/
		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		final double ratio = display.ratio();
		final int count = event.getPointerCount();		
		for (int i=0; i<count; i++){
			final Touch touch = new Touch();
			touch.x = (int)(event.getX()/ratio);
			touch.y = (int)(event.getY()/ratio);			
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:{
					touch.type = featurea.Touch.Type.DOWN;
					break;
				} 
				case MotionEvent.ACTION_MOVE:{
					touch.type = featurea.Touch.Type.MOVE;
					break;
				} 
				case MotionEvent.ACTION_UP:{
					touch.type = featurea.Touch.Type.UP;
					break;
				} 			
			}
			Animator.processTouches(touch);
		}
		return true;
	}
}