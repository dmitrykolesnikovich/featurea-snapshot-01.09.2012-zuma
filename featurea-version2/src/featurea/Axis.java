package featurea;

public class Axis {

    public double degree;
    /* package */ double oldDegree; 
    public Turn[] turns;
    public int index;
    public float progress;
    
    public Axis(double degree, Turn[] turns) {
        this.degree = degree;
        this.turns = turns;
    }    

    public Turn turn() {
        return turns[index];
    }

    public String toString() {
        return "axis=(" + degree + "," + turns + ");";
    }

    public static class Turn {
        public final float time;
        public final float degree;
        public Turn(float time, float degree) {
            this.time = time;
            this.degree = degree;
        }
    }

    /**  
     * rotation in xOy surface
     * */
    /*package*/ static void rotate(final Vector3 center, final double degree, final Vector3 vector) {
        double B = (-degree * (Math.PI / 180)); // radians
        double x0 = center.dx();
        double y0 = center.dy();
        double x = vector.dx();
        double y = vector.dy();

        /* 1. Move origin to x0, y0:
         * x1 = x - x0
         * y1 = y - y0 
         * */
        double x1 = x - x0;
        double y1 = y - y0;

        /*
         *  2. Rotate about origin by an angle of B:			
         *	x2 = x1 cos B - y1 sin B
         *	y2 = x1 sin B + y1 cos B
         * */
        double x2 = (x1 * Math.cos(B) - y1 * Math.sin(B));
        double y2 = (x1 * Math.sin(B) + y1 * Math.cos(B));

        /*
         *  3. Move origin back to its original position, from x0, y0			
         *	x3 = x2 + x0
         *	y3 = y2 + y0
         * */
        vector.dx(x2 + x0);
        vector.dy(y2 + y0);
    }

    public Axis copy() {       
        return new Axis(degree, turns);
    }
}