package features.flynut;

import featurea.Mode;
import featurea.Mode.Shift;
import type.Level;
import type.Nut;
import areas.FlyNut;
import data.values;
import featurea.Feature;
import featurea.Vector3;
import featurea.game;

public class scratNutFly extends Feature {

    public FlyNut flyNut[] = new FlyNut[1];

    @Override
    public boolean check() {
        if (flyNut[0].joiningTime < values.burstNutJoiningTime) {
            return false;
        }
        if (flyNut[0].nut == null) {
            return false;
        }
        if (!game.play.children.contains(flyNut[0].nut)){
            return false;
        }
        if (flyNut[0].nut.chain() != null) {
            return false;
        }
        return true;
    }

    @Override
    public void make() {
        final double time = featurea.game.frameTime;
        final double frameShift = values.strikevelocity * time;

        // Trick: index of 'strikeGraph' (see 'step1_CreateFlyNut' feature) must be '1'-------
        if (flyNut[0].nut.graph.index != 1) {
            flyNut[0].nut.mode = new Mode(new Shift[]{new Shift(time, frameShift / time, 0), null}, 0, 0);
            return;
        }
        // -----------------------------------------------------------------------------------

        // Trick:
        double minShift = frameShift;       
        Vector3 B = flyNut[0].nut.graph.vector().copy();
        // Trick: ------------------------------------------------------        
        // Axis.rotate(new Vector3(0, 0, 0), flyNut[0].nut.graph.degree, B);       
        // -------------------------------------------------------------        
        for (Nut nut : ((Level) game.play).nutsInChain()) {
            Vector3 A = new Vector3(nut.vector.dx() - flyNut[0].nut.vector.dx(),
                    nut.vector.dy() - flyNut[0].nut.vector.dy(), nut.vector.dz() - flyNut[0].nut.vector.dz());
            double rad = Vector3.rad(A, B);
            if (rad <= Math.PI / 2 && rad >= -Math.PI / 2) {
                double tempShift = Math.cos(rad) * A.dlength;
                double C = Math.sqrt(Math.pow(A.dlength, 2) - Math.pow(tempShift, 2));
                if (tempShift < minShift
                        && // Trick:
                        C < values.nutdiameter) {
                    minShift = tempShift;                                 
                }
            }
        }
        flyNut[0].nut.mode = new Mode(new Shift[]{new Shift(time, minShift / time, 0), null}, 0, 0);       
    }
}