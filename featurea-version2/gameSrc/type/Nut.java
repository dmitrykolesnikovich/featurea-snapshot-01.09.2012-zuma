package type;

import static data.colors.BLUE;
import static data.colors.GREEN;
import static data.colors.PURPLE;
import static data.colors.RED;
import static data.colors.YELLOW;
import static data.colors.WHITE;
import static data.colors.PINK;
import areas.Chain;
import areas.Path;
import areas.Scrat;
import data.colors;
import data.sprites;
import featurea.Animation;
import featurea.Axis;
import featurea.Graph;
import featurea.Mode;
import featurea.Pulse;
import featurea.Vector3;
import featurea.game;

public class Nut extends Animation {
    

    public int color;
    
    // Example of how to make 'dynamic' references
    public Chain chain() {
        Level level = (Level) game.play;
        for (Path path : level.paths) {
            for (Chain chain : path.chains) {
                if (chain.nuts.contains(this)) {
                    return chain;
                }
            }
        }
        return null;
    }

    public static final int UNDEFINED = -1;
    public int type = UNDEFINED;
    public final static int FLY = 0;
    public final static int FIRE = 1;
    public Nut(boolean isLeftHand, Scrat.LeftHand leftHand, Scrat.RightHand rightHand) {
        this(colors.NO_COLOR, isLeftHand, leftHand, rightHand);        
    }

    public Nut(int color, Vector3 point, Graph graph, Mode mode, Axis axis, Pulse pulse) {
    	super(null, point, graph, mode, axis, pulse);
        this.color = color;
        switch (color) {
            case RED:
                this.sprite(sprites.nut_red);
                break;
            case GREEN:
                this.sprite(sprites.nut_green);
                break;
            case BLUE:
                this.sprite(sprites.nut_blue);
                break;
            case YELLOW:
                this.sprite(sprites.nut_yellow);
                break;
            case PURPLE:
                this.sprite(sprites.nut_purple);
                break;
            case WHITE:
                this.sprite(sprites.nut_white);
                break;
            case PINK:
                this.sprite(sprites.nut_pink);
                break;
            default:
                break;
        }        
    }

    public Nut(int color, boolean isLeftHand, Scrat.LeftHand leftHand, Scrat.RightHand rightHand) {
    	super(null, null, null, null, null, null);
        // Vector
        if (isLeftHand) {
            this.vector(leftHand.vector.copy());
        } else {
            this.vector(rightHand.vector.copy());
        }
        // Color
        setColor(color);
    }

    public int indexinchain() {
        return chain().indexOf(this);
    }

    @Override
    public String toString() {
        switch (color) {
            case RED:
                return "r";
            case GREEN:
                return "g";
            case BLUE:
                return "b";
            case YELLOW:
                return "y";
            case PURPLE:
                return "p";
            case WHITE:
                return "w";
            case PINK:
                return "pink";
            default:
                return "?";
        }
    }

    public void setColor(int color) {
        this.color = color;        
        switch(type){
            case FLY: {
                if (color == colors.RED) {
                    this.sprite(sprites.nut_red);
                } else if (color == colors.GREEN) {
                    this.sprite(sprites.nut_green);
                } else if (color == colors.BLUE) {
                    this.sprite(sprites.nut_blue);
                } else if (color == colors.YELLOW) {
                    this.sprite(sprites.nut_yellow);
                } else if (color == colors.PURPLE) {
                    this.sprite(sprites.nut_purple);
                }
                break;
            }
            case FIRE: {
                if (color == colors.RED) {
                    this.sprite(sprites.nut_red_burn);
                } else if (color == colors.GREEN) {
                    this.sprite(sprites.nut_green_burn);
                } else if (color == colors.BLUE) {
                    this.sprite(sprites.nut_blue_burn);
                } else if (color == colors.YELLOW) {
                    this.sprite(sprites.nut_yellow_burn);
                } else if (color == colors.PURPLE) {
                    this.sprite(sprites.nut_purple_burn);
                }
                 break;
            }                
        }        
    }

    public boolean islast() {
        return indexinchain()== chain().nuts.size()-1;
    }

    public boolean isfirst() {
        return indexinchain()== 0;
    }
   
}
