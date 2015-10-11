package features.flynut;

import type.Nut;
import data.sounds;
import areas.FlyNut;
import areas.Chain;
import data.modes;
import data.parameters;
import data.values;
import featurea.device;
import featurea.Feature;
import featurea.Vector3;

public class flyNutCrossChain extends Feature {

    public FlyNut flyNut[] = new FlyNut[1];
    public Chain chain[] = new Chain[1]; // crossChain

    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        if (flyNut[0].nut == null) {
            return false;
        }
        if (flyNut[0].nut.type != Nut.FLY) {
            return false;
        } 
        if (flyNut[0].joiningTime < values.burstNutJoiningTime) {
            return false;
        }
        if (flyNut[0].nut.chain() != null) {
            return false;
        }         
        for (Nut nut : chain[0].nuts) {
            // if (nut.vector().dz()==values.DZ_CROSS_UPPER || nut.vector().dz()==values.DZ_CROSS_UNDER){ // check!
                double overlap = flyNut[0].nut.crossBalls(nut);
                if (overlap > 0) {
                    // 'crossNut' is buffer between 'check' and 'make'
                    flyNut[0].crossNut = nut;
                    return true;
                }
            // }            
        }
        return false;
    }

    // Add 'nut' to 'crossChain'
    @Override
    public void make() {
        // I. Cross
        Nut crossNut = flyNut[0].crossNut;
        Nut nut = flyNut[0].nut;
        Vector3 vector1 = crossNut.graph.vector().copy();
        Vector3 vector2 = new Vector3(nut.vector.dx() - crossNut.vector.dx(),
                nut.vector.dy() - crossNut.vector.dy(), nut.vector.dz() - crossNut.vector.dz()); // from 'crossNut' to 'flyNut'
        double degree = Vector3.degree(vector1, vector2);
        int crossNutIndexShift = 0;
        if (Math.abs(degree) < 90) {
            if (chain[0].state() == values.state_move || chain[0].state() == values.state_stop) {
                crossNutIndexShift = 1;
            } else {
                crossNutIndexShift = 0;
            }
        } else {
            if (chain[0].state() == values.state_move || chain[0].state() == values.state_stop) {
                crossNutIndexShift = 0;
            } else {
                crossNutIndexShift = 1;
            }
        }

        int flyNutIndex = chain[0].indexOf(crossNut) + crossNutIndexShift;
        chain[0].nuts.add(flyNutIndex, nut);

        // Sound
        device.audio.play(sounds.flynut_contacts_nut);

        // II. Join
        switch (chain[0].state()) {
            case values.state_move:
            case values.state_stop: {
                if (nut.isfirst()) {                    
                    Nut second = chain[0].nut(1);
                    Nut first = nut;
                    Vector3 shift = second.graph.vector(-values.nutdiameter);
                    first.vector(second.vector.copy());
                    first.vector.plus(shift);
                    first.graph = second.graph.graph(-values.nutdiameter);                    
                    // Trick:
                    if (chain[0].state()==values.state_move){first.mode = modes.move();}
                    if (chain[0].state()==values.state_stop){first.mode = modes.stop();}
                } else {
                    for (int i = flyNutIndex; i < chain[0].nuts.size() - 1; i++) {
                        Nut before = chain[0].nut(i - 1);
                        Nut after = chain[0].nut(i);
                        Vector3 shift = before.graph.vector(values.nutdiameter);
                        after.vector(before.vector.copy());
                        after.vector.plus(shift);
                        after.graph = before.graph.graph(values.nutdiameter);
                    }
                }
                break;
            }
            case values.state_back: {
                if (nut.islast()) {
                    Nut prelast = chain[0].nut(chain[0].nuts.size() - 2);
                    Nut last = nut;
                    Vector3 shift = prelast.graph.vector(values.nutdiameter);
                    last.vector(prelast.vector.copy());
                    last.vector.plus(shift);
                    last.graph = prelast.graph.graph(values.nutdiameter);
                    // Trick:
                    last.mode = modes.rollBack();
                } else {
                    for (int i = flyNutIndex; i >= 0; i--) {
                        Nut after = chain[0].nut(i + 1);
                        Nut before = chain[0].nut(i);
                        Vector3 shift = after.graph.vector(-values.nutdiameter);
                        before.vector(after.vector.copy());
                        before.vector.plus(shift);
                        before.graph = after.graph.graph(-values.nutdiameter);
                    }
                }
                break;
            }
        }
    }
}