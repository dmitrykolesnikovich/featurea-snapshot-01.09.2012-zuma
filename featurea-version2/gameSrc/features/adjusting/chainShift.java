package features.adjusting;

import areas.FlyNut;
import featurea.Feature;
import featurea.game;
import game.navigation;

public class chainShift extends Feature {

    public FlyNut flyNut[] = new FlyNut[1];
     
    @Override
    public boolean check() {
    	// if (parameters.gameOverAnimationStarts){return false;}
        if (flyNut[0].nut == null) {
            return false;
        }
        if (flyNut[0].joiningTime <= 0) {
            return false;
        }
        if (flyNut[0].nut.chain() == null) {
            return false;
        }
        if (!game.play.children.contains(flyNut[0].nut)){
            return false;
        }
        return true;
    }

    @Override
    public void make() {                
        flyNut[0].joiningTime -= featurea.game.frameTime;
        /*double step = values.hForShift * (flyNut[0].joiningTime / values.burstNutJoiningTime);        
        Chain chain = flyNut[0].nut.chain();        
        final int indexinchain = flyNut[0].nut.indexinchain();
        if (chain.state == values.state_stop || chain.state == values.state_move){
            int index = indexinchain - 1;
            Nut nut = null;
            if (index >=0){
                nut = chain.nut(index);
            }else{
                nut = chain.nut(1);
            }
            Vector3 direction = nut.graph.vector().copy();
            direction = new Vector3(direction.dy(), -direction.dx(), 0);

            double A = Math.abs(direction.dx()) / Math.abs(direction.dy());
            double C = Math.pow(step, 2);

            double stepY = Math.sqrt(C/(1+Math.pow(A, 2)));
            double stepX = stepY * A;

            stepX = Math.abs(stepX) * Math.signum(direction.dx());  
            stepY = Math.abs(stepY) * Math.signum(direction.dy());

            Vector3 shiftvector = new Vector3(-stepX, -stepY, 0);
            flyNut[0].setShift(shiftvector);            
            
            // Dealing with rest of nuts
            if (indexinchain!=0){
                for (int i=index+1; i<chain.nuts.size(); i++){
                    Nut x = chain.nuts.get(i);                
                    Vector3 shiftx = x.graph.shift(-step);
                    x.vector.plus(shiftx);
                    x.graph = x.graph.copy(-step);
                }
            }else{
                System.out.println("a");
            }
            
        }else if (chain.state == values.state_back){
            // flyNut[0].nut.vector.plus(new Vector3(-step, -step, 0));
        } 
         
         */
    }
}