package featurea;

import java.util.List;
import featurea.FollowerList;
import static featurea.device.display;

public class Animation {

    /*package*/ Screen screen;
    public Text text;
    /*package*/ Sprite1[] sprite;
    /*package*/ int index = 0;   
    /*package*/ boolean loop;
    /*package*/ long progress;
    
    public void sprite(Sprite1[] sprite){
        this.sprite = sprite;
        this.index = 0;
    }
    public Sprite1 sprite1() {
    	if (sprite==null){
    		return null;
    	}
        return sprite[index];
    }
    public Sprite1[] sprite() {     
        return sprite;
    }
    public int index(){
    	return index;
    }    
    protected void index(int index) {}    
    
    // 'Follow'
    public FollowerList followers = new FollowerList(this);
    /*package*/ Animation lead;
    public Animation lead(){
    	return lead;
    }  
    public void lead(Animation... array){
    	for (Animation follower : array){
    		followers.add(follower);
    	}    	
    }
    public void unlead(Animation... array){
    	for (Animation follower : array){
    		followers.remove(follower);
    	}    	
    } 
    public void unlead(List<Animation> list){
    	for (Animation follower : list){
    		followers.remove(follower);
    	}    	
    } 
    public void follow(Animation lead){	
    	lead.followers.add(this);
    }
    public void unfollow(){
    	lead.followers.remove(this);    	
    }
    
    // Motion
    // Vector3
    /* package */ Vector3 oldVector = new Vector3(0, 0, 0);
    public Vector3 vector;
    public void vector(Vector3 vectorTo){
        if (vectorTo==null){return;}
        if (this.vector==null){this.vector=new Vector3(0,0,0);} 
        // Shift
        Vector3 from = this.vector.copy();
        Vector3 toCopy = vectorTo.copy(); 
        Vector3 delta = toCopy; 
        delta.minus(from);
        shift(delta);
    }
    public void shift(Vector3 delta){        
        moveLeadWithFollowers(this, delta);      
    }
    private void moveLeadWithFollowers(final Animation lead, final Vector3 delta) {
        lead.vector.plus(delta);
        lead.oldVector = lead.vector; 
        for (Animation follower : lead.followers){            
            moveLeadWithFollowers(follower, delta);
        }
    }
    
    
    // Graph
    public Graph graph;
    public void move(double delta) {
        Vector3 shift = graph.vector(delta);
        vector.plus(shift);
        graph = graph.graph(delta);
    }
    public void moveAnimation(Animation animation, double delta) {
        Vector3 shift = this.graph.vector(delta);
    	// before.vector
        animation.vector(this.vector.copy());
        animation.vector.plus(shift);
    	// before.graph
        animation.graph = this.graph.graph(delta);
    }
    
    // Axis, degree
    public Axis axis;
    public void degree(double degree) {
        double delta = degree - this.axis.degree;
        turn(delta);
    }
    public void turn(double delta) {
        // turn lead:
        this.axis.degree += delta;
        this.axis.oldDegree += delta;
        // turn followers:
        turnFollowers(this, delta);
    }
    private void turnFollowers(final Animation lead, final double delta) {
        for (int i = 0; i < lead.followers.size(); i++) {
            Animation follower = lead.followers.get(i);
            follower.axis.degree += delta;
            follower.axis.oldDegree += delta;
            Axis.rotate(lead.vector, delta, follower.vector);
            turnFollowers(follower, delta);
        }
    }

    // Mode
    public Mode mode;
    
    // Pulse
    public Pulse pulse;
    
    // Blink
    public Animation(Sprite1[] sprite, Vector3 point, Graph graph, Mode mode, Axis axis, Pulse pulse) {
        this.sprite = sprite;
        this.vector = point;
        this.graph = graph;
        this.mode = mode;
        this.axis = axis;
        this.pulse = pulse;
        if (this.vector==null){
        	this.vector = new Vector3(0,0,0);
        }
        if (this.axis==null){
        	this.axis = new Axis(0,null);
        }
        if (this.pulse==null){
        	this.pulse = new Pulse(1,null);
        }
    }

    // Cross
    private final double MINIMUM = 0.01;
    public boolean cross(Animation crosser, boolean withFollowers) {
        return checkCrossMuchTo1(crosser, this, withFollowers);
    }
    private boolean checkCrossMuchTo1(final Animation crosser, final Animation lead, boolean withFollowers) {
        boolean result = checkCross1toMuch(crosser, lead, withFollowers);
        if (result) {
            return true;
        }        
        if (withFollowers){
            for (int i = 0; i < crosser.followers.size(); i++) {
                Animation crosserFollower = crosser.followers.get(i);
                boolean result2 = checkCrossMuchTo1(crosserFollower, lead, withFollowers);
                if (result2) {
                    return true;
                }
            }
        }
        

        return false;
    }
    private boolean checkCross1toMuch(final Animation crosser, final Animation lead, boolean withFollowers) {
        boolean result2 = checkCross2Areas(lead, crosser);
        if (result2) {
            return true;
        }
        if (withFollowers){
            for (int j = 0; j < lead.followers.size(); j++) {
                Animation area = lead.followers.get(j);
                boolean result = checkCross1toMuch(area, crosser, withFollowers);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkCross2Areas(final Animation area1, final Animation area2) {
        if (area1.sprite==null){return false;}
        if (area2.sprite==null){return false;}
        boolean result = false;
        Sprite1 s1 = area1.sprite1();
        Sprite1 s2 = area2.sprite1();        
        double density = display.density();
        double w1 = s1.width / density;
        double h1 = s1.height / density;
        double w2 = s2.width / density;
        double h2 = s2.height / density;
        if (s1 != null && s2 != null) {
            double left1 = area1.vector.dx() - w1 / 2;
            double right1 = area1.vector.dx() + w1 / 2;
            double top1 = area1.vector.dy() - h1 / 2;
            double bottom1 = area1.vector.dy() + h1 / 2;
            double left2 = area2.vector.dx() - w2 / 2;
            double right2 = area2.vector.dx() + w2 / 2;
            double top2 = area2.vector.dy() - h2 / 2;
            double bottom2 = area2.vector.dy() + h2 / 2;
            result = (left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2);
        }
        return result;
    }
    public boolean crossZ(Animation animation) {
        boolean result = false;
        Sprite1 s1 = animation.sprite1();
        Sprite1 s2 = this.sprite1();
        double density = display.density();
        double w1 = s1.width / density;
        double h1 = s1.height / density;
        double w2 = s2.width / density;
        double h2 = s2.height / density;        
        if (s1 != null && s2 != null) {
            double left1 = animation.vector.dx() - w1 / 2;
            double right1 = animation.vector.dx() + w1 / 2;
            double top1 = animation.vector.dy() - h1 / 2;
            double bottom1 = animation.vector.dy() + h1 / 2;
            double dz1 = animation.vector.dz();
            double left2 = this.vector.dx() - w2 / 2;
            double right2 = this.vector.dx() + w2 / 2;
            double top2 = this.vector.dy() - h2 / 2;
            double bottom2 = this.vector.dy() + h2 / 2;
            double dz2 = this.vector.dz();
            result = (left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2) && (Math.abs(dz2 - dz1) < MINIMUM);
        }
        return result;
    }
    public double crossBalls(Animation ball) {
        Vector3 o1 = this.vector.copy();
        Vector3 o2 = ball.vector.copy();        
        double density = display.density();
        double w1 = this.sprite1().width / density;
        double w2 = ball.sprite1().width / density;
        double radius1 = w1 / 2;
        double radius2 = w2 / 2;
        double o1o2 = o1.distance(o2);
        return (radius1 + radius2) - o1o2;
    }
    public double crossBallsZ(Animation ball) {
        if (Math.abs(this.vector.dz() - ball.vector.dz()) > MINIMUM) {
            return -1;
        }
        return crossBalls(ball);
    }
    public Animation copy() {
        Vector3 vector = null;
        Graph graph = null;
        Mode mode = null;
        Axis axis = null;
        Pulse pulse = null;
        if (this.vector != null) {
            vector = this.vector.copy();
        }
        if (this.graph != null) {
            graph = this.graph.copy();
        }
        if (this.mode != null) {
            mode = this.mode.copy();
        }
        if (this.axis != null) {
            axis = this.axis.copy();
        }
        if (this.pulse != null) {
            pulse = this.pulse.copy();
        }
        return new Animation(sprite, vector, graph, mode, axis, pulse);
    }    
    
    
    // Callbacks:
    boolean down;      
    protected void down() {}
    protected void move() {}
    protected void up() {}
    protected void click() {}
    /*package*/ long lifeTime;    
    protected void timer(final long lifeTime) {}
   
    @Override
    public String toString() {    
    	if (sprite1()==null){
    		return "";
    	}
    	return sprite1().png+"="+vector;
    }
   
}