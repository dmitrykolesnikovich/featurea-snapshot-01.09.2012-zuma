package data;

public class values {

    public static final int nutdiameter = 22;
    // Just for debug 'AddNutToPath' feature ------------------------------------------
    public static final double runvelocity = 0.3f; // == modes.run.velocity	
    // public static final double flynutjointvelocity = 0.04f;	
    // --------------------------------------------------------------------------------
    public static final double veryfast = 1000f;
    // Static fields for features.
    public static boolean firstNutHasBeenBursted = false;
    public static boolean moveAlready = false;
    public static boolean runAlready = false;
    public static long changemodetime = 3000;
    public final static double rollbackvelocity = -0.002f;
    public static long levelLifeTime = 0;
    public static final long burstTime = 1050;
    public static final long burnTime = 300;
    public static final long scoreupTime = 800;
    public static final double movevelocity = 0.020;
    public static final double scoreupvelocity = 0.085;
    public static final double burstingvelocity = 0.0002;
    public static final double stopvelocity = 0;
    public static final double waitvelocity = 0.0001;
    // public static final long burstNutJoiningTime = 750;    
    public static final long burstNutJoiningTime = 200;    
    public final static double strikevelocity = 0.6f;
    public final static double max_strikevelocity_debug = 2f;
    public final static long THROW_DELAY = 250;
    
    // 4 states for chain:
    public final static int state_stop = 0;
    public final static int state_move = 1;
    public final static int state_back = 2;
   
    public final static int levelFillScore = 4000;
    
    // DZ
    public final static int DZ_BACKGROUND_UNDER = 0;
    public final static int DZ_NO_CROSS = 1;
    public final static int DZ_CROSS_UNDER = 2;
    public final static int DZ_BACKGROUND_UPPER = 3;
    public final static int DZ_CROSS_UPPER = 4;
    
    // coins in level
    public static int COINS_IN_LEVEL_TO_APPEAR = 5;
    public static boolean isCoinInLevel;
    public final static double appearing_probability = 0.0018;
    public static long timeCoinIsNotInLevel = 0;
    public final static double hForShift = 19.05; // Math.sqrt(Math.pow(22, 2) - Math.pow(11, 2));
    
    public static final long AD_TIME = 2 * 60 * 1000; // every 3 minutes TapForTap (3*60*1000 milliseconds)
    public static long tapForTapLastTime = 0;
    public static boolean gamerViewAd = false;
    public static boolean tapForTapAdIsReceived = false;    
    
}
