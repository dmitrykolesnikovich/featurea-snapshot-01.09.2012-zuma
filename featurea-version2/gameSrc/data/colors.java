package data;

public class colors {
    public static final int NO_COLOR = -1;
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int YELLOW = 3;
	public static final int PURPLE = 4;
	public static final int WHITE = 5;
	public static final int PINK = 6;
	
	public static int randomfrom(int... colors){
            if (colors.length==0){
                return NO_COLOR;
            }
            int index = (int) (Math.random() * colors.length);		 
            return colors[index];
	}
}
 