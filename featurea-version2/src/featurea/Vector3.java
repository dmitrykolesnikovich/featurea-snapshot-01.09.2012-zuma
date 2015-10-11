package featurea;

import java.text.DecimalFormat;

public class Vector3 {	
    
    private double dx;
    private double dy;
    private double dz;  
    public double dlength;
   
    public Vector3(double dx, double dy, double dz){
        this.dx = dx;
        this.dy = dy;        
        this.dz = dz;
        updatedlength();
    }
    public Vector3 copy(){
		return new Vector3(dx, dy, dz);
	}
    
    public double dx(){
        return dx;
    }
    public double dy(){
        return dy;
    }
    public double dz(){
        return dz;
    }
    public void dz(double dz){
        this.dz = dz;
        updatedlength();
    }
    
    public void dx(double dx){
        this.dx = dx;
        updatedlength();
    }
    
    public void dy(double dy){
        this.dy = dy;
        updatedlength();
    }
    
    private void updatedlength(){
    	this.dlength = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
    }
        
    public void plus(Vector3 vector){
        dx += vector.dx;
        dy += vector.dy;
        dz += vector.dz;
        updatedlength();
    }
    
    public void plus(double dx, double dy, double dz){
        this.dx += dx;
        this.dy += dy;
        this.dz += dz;
        updatedlength();
    }
    
    public void plus(Vector3 direction, double module){    	
    	if (direction.dlength != 0){
    		double koeff = module / direction.dlength;
            dx += direction.dx * koeff;
            dy += direction.dy * koeff;
            dz += direction.dz * koeff;
            updatedlength();
    	}    	
    }
    
    public void plus(double dx, double dy, double dz, double module){    	
    	double dlength = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
		double koeff = module / dlength;
        this.dx += dx * koeff;
        this.dy += dy * koeff;
        this.dz += dz * koeff;
        updatedlength();
	    	
    }
    
    public void minus(Vector3 vector){
        this.dx -= vector.dx;
        this.dy -= vector.dy;
        this.dz -= vector.dz;
        updatedlength();
    }
    public void minus(double dx, double dy, double dz){
        this.dx -= dx;
        this.dy -= dy;
        this.dz -= dz;
        updatedlength();
    }

    public float distance(Vector3 vector){
        return (float)(Math.sqrt(Math.pow(vector.dx-this.dx, 2)+Math.pow(vector.dy-this.dy, 2)+Math.pow(vector.dz-this.dz, 2)));
    }

	public void revert(){
		this.dx *= -1;
		this.dy *= -1;
		this.dz *= -1;
	}
	
	// Util
	public static double rad(Vector3 vector1, Vector3 vector2) {
		double x1 = vector1.dx;
		double y1 = vector1.dy;
		double x2 = vector2.dx;
		double y2 = vector2.dy;		
		double cos = (x1*x2 + y1*y2) / Math.sqrt ((x1*x1 + y1*y1) * (x2*x2 + y2*y2));
		return Math.acos(cos);
	}	
	public static double degree(Vector3 vector1, Vector3 vector2) {
		double rad = rad(vector1, vector2);		
		return rad / Math.PI * 180;
	}

	//  
    @Override
    public String toString(){
    	DecimalFormat df = new DecimalFormat("#.##");
    	String x = df.format(dx);
    	String y = df.format(dy);
    	String z = df.format(dz);
    	return "("+x+","+y+","+z+")";
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3)){return false;}
        Vector3 v = (Vector3)obj;
        return dx==v.dx()&&dy==v.dy()&&dz==v.dz();
    }
        
        
}