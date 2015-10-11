package featurea.android;

import featurea.Display;

public class AndroidDisplay extends Display{
    @Override
    public double width() {        
        return GameView.instance.getWidth();
    }

    @Override
    public double height() {        
    	return GameView.instance.getHeight();
    }

	@Override
	public double density() {
		return featurea.android.GameActivity.density;
	}
    
}