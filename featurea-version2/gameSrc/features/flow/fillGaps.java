package features.flow;

import type.Nut;
import areas.Chain;
import areas.Path;
import data.parameters;
import data.values;
import data.screen.Hud;
import featurea.Animation;
import featurea.Feature;
import featurea.Graph;
import featurea.Mode;
import featurea.Vector3;
import featurea.game;
import game.navigation;

public class fillGaps extends Feature {

    public Path path[] = new Path[1];

    @Override
    public boolean check() {
    	if (parameters.gameOverAnimationStarts){return false;}
        Chain firstChain = path[0].firstchain();
        if (firstChain == null) {
            return false;
        }
        Nut firstnut = firstChain.firstnut();
        if (firstnut == null) {
            return false;
        }
        if (firstnut.mode==null){
            return false;
        }
        return navigation.hud.addMoreNuts() && !firstnut.cross(path[0].startfornuts, false);
    }

    @Override
    public void make() {
        Chain firstchain = path[0].firstchain();
        Animation firstnut = firstchain.firstnut().copy();
                
        while (!firstnut.cross(path[0].startfornuts, false)) {
            Vector3 point = firstnut.vector.copy();
            Vector3 shift = firstnut.graph.vector(-values.nutdiameter);
            point.plus(shift);
            
            // graph, mode, color
            Graph trajectory = firstnut.graph.graph(-values.nutdiameter);
            Mode mode = firstnut.mode.copy();
            int color = (int) (Math.random() * parameters.colorsCount);
            Nut nut = new Nut(color, point, trajectory, mode, null, null);
            firstchain.nuts.add(0, nut);
            game.play.add(nut);

            // update 'current' for following check
            firstnut = nut.copy();
        }
    }
}