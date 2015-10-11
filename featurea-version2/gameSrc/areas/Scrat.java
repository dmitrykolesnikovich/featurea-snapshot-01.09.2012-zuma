package areas;

import type.Nut;
import data.axes;
import data.sprites;
import featurea.Sprite1;
import featurea.Animation;
import featurea.Axis;
import featurea.Graph;
import featurea.Mode;
import featurea.Pulse;
import featurea.Vector3;

public class Scrat extends Animation {    
    public Pile pile;
    public boolean downed;
    public boolean isLeftHandActive = false;
    public final Vector3 leftmotion = new Vector3(35, 35, 0);
    public final Vector3 rightmotion = new Vector3(35, -35, 0);
    // It's important that (z==0) -------------------------------------------------------------------------------
    public final LeftHand leftHand = new LeftHand(null, new Vector3(0, -35, 2.0), null, null, axes.ax0(), null);
    public final RightHand rightHand = new RightHand(null, new Vector3(0, 35, 2.0), null, null, axes.ax0(), null);
    public final Nose nose = new Nose(null, new Vector3(35, 0, 2.0), null, null, axes.ax0(), null);
    // ----------------------------------------------------------------------------------------------------------    
    
    public Nut nut; // Not 'FlyNut' because it should be in 'followers'
    
    public Scrat(Vector3 scratPoint) {  
    	super(sprites.scrat, null, null, null, null, null);
        lead(leftHand);   
        lead(rightHand);
        lead(nose);
        this.vector(scratPoint);
    }

    public static class LeftHand extends Animation {
        public LeftHand(Sprite1[] sprite, Vector3 point, Graph graph, Mode mode, Axis axis, Pulse pulse) {
            super(sprite, point, graph, mode, axis, pulse);
        }
    }

    public static class RightHand extends Animation {
        public RightHand(Sprite1[] sprite, Vector3 point, Graph graph, Mode mode, Axis axis, Pulse pulse) {
            super(sprite, point, graph, mode, axis, pulse);
        }
    }

    public static class Nose extends Animation {
        public Nose(Sprite1[] sprite, Vector3 point, Graph graph, Mode mode, Axis axis, Pulse pulse) {
            super(sprite, point, graph, mode, axis, pulse);
        }
    }
    
}