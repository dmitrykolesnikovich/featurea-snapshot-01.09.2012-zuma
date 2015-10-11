package features.flynut;

import areas.Scrat;
import data.colors;
import data.parameters;
import data.screen.Hud;
import featurea.Feature;
import featurea.game;
import game.navigation;
import type.Level;


public class scratNutChooseColor extends Feature {

    public Scrat scrat[] = new Scrat[1];

    @Override
    public boolean check() {
        if (game.play==null){return false;}
        if (scrat[0].nut.color == colors.NO_COLOR) {return true;}
        if (navigation.hud.addMoreNuts()){return false;}
        Level level = (Level) game.play;
        int[] existingcolors = level.existingcolors();
        boolean flag = false;
        for (int existingcolor : existingcolors) {
            if (existingcolor == scrat[0].nut.color) {
                flag = true;
            }
        }
        return flag == false;
    }

    @Override
    public void make() {        
        int color = colors.NO_COLOR;
        if (navigation.hud.addMoreNuts()) {
            if (parameters.colorsCount == 4) {
                color = colors.randomfrom(colors.BLUE, colors.GREEN, colors.RED, colors.YELLOW);
            } else if (parameters.colorsCount == 5) {
                color = colors.randomfrom(colors.BLUE, colors.GREEN, colors.RED, colors.YELLOW, colors.PURPLE);
            }
        } else {
            Level level = (Level) game.play;
            color = colors.randomfrom(level.existingcolors());            
        }
        scrat[0].nut.setColor(color);
    }
    
}