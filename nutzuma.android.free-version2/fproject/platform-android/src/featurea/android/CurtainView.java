package featurea.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import featurea.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public final class CurtainView extends View {

	public CurtainView(Context context, AttributeSet set) {
		super(context, set);	
	}

	@Override
	protected void onDraw(Canvas canvas) {  
		try{  
			final long now = SystemClock.elapsedRealtime();
			if (featurea.game.curtain!=null){        
				if (featurea.game.currentTime == 0) {
					featurea.game.currentTime = now;
				}
				final long frameTime = now - featurea.game.currentTime;
				featurea.game.frameTime = frameTime;
				featurea.game.currentTime = now;				
				Animator.update();
				AndroidPainter.canvas = canvas;
				// paint curtain
			}
		}catch(Exception ex){	
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
		}		
	}

	

}