package featurea.android;

import java.util.HashMap;
import java.util.Map;
import featurea.Animation;
import featurea.Text;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

public class AndroidPainter extends featurea.Painter{
	public static Canvas canvas;
	private RectF rect = new RectF();
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Map<String, Typeface> typefaces = new HashMap<String, Typeface>();
	public AndroidPainter(){
		AssetManager am = featurea.android.GameActivity.instance.getApplicationContext().getAssets();
		typefaces.put("main", Typeface.createFromAsset(am, "fonts/main.ttf"));
	}
	
	@Override
	protected void paint(Animation animation, double x, double y, double left, double top, double right, double bottom) {	
		// 1. Sprite
		if (animation.sprite1().resource != null) {          
			Bitmap bitmap = (Bitmap) animation.sprite1().resource;
			if (animation.axis!=null && animation.axis.degree != 0) {
				canvas.save();
				canvas.rotate((float)-animation.axis.degree, (float)x, (float)y);
			}
			rect.left = (float)left;
			rect.top = (float)top;
			rect.right = (float)right;
			rect.bottom = (float)bottom;
			canvas.drawBitmap(bitmap, null, rect, null);
			if (animation.axis!=null && animation.axis.degree != 0) {
				canvas.restore();
			}                                
		}	
		
	}

	@Override
	protected void paint(Text text, double x, double y, int size) {
		paint.setTypeface(typefaces.get(text.font));
		paint.setTextSize(size);
		paint.setColor(text.color);			
		canvas.drawText(text.string, (int)x, (int)y, paint);
	}

}


