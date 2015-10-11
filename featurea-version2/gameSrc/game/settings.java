package game;

public class settings {
	
    public static Integer levelNumber = 0;
    public static void levelNumber(Integer i){if (i!=null){levelNumber = i;}}
    
    public static Integer lives = 3;
    public static void lives(Integer i){if (i!=null){lives = i;}}
    
    public static Integer totalScore = 0;  
    public static void totalScore(Integer i){if (i!=null){totalScore = i;}}
    
}