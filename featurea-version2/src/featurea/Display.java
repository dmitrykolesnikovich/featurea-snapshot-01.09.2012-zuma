package featurea;

public abstract class Display{
	
    public abstract double width();
    public abstract double height();
    public abstract double density();
    public double ratio(){
    	double width = 0;
        double height = 0;
        if (height()>width()){
        	height = 480;
        	width = 320;
        }else{
        	width = 480;
        	height = 320;	
        }        
        double ratioWidth = width()/width;
        double ratioHeight = height()/height;
        return Math.min(ratioWidth, ratioHeight);
    }
    
}