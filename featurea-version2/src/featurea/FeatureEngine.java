package featurea;

// * - the main trick that makes family type 'List<Subject[]>[] family' possible (2 points in code).
/*package*/ final class FeatureEngine {
    // private static final String TAG = "FeatureEngine";

    private static long criticalTime = 300;
    public static boolean SHOULD_LOG = false;
    private static int counter = 0;

    /** Featurea engine. */
    public static void update(Play play) {
        Feature[] features = play.stack;
        play.updateFamily();
        for (Feature feature : features) {            
            feature.check = false;
            feature.make = false;
            feature.maxtime = 0;
            if (feature.figures.length == 0) { 
                checkMake(feature);
            } else if (feature.family != null) {				
                for (Object[] cluster : feature.family.elements()) {
                    if (cluster != null) {
                        Object[][] groups = feature.figures;
                        int index = 0;
                        for (Object[] group : groups) { // *			
                            for (int i = 0; i < group.length; i++) {
                                group[i] = cluster[index];
                                index++;
                            }
                        }
                        checkMake(feature);
                    }
                }
            }
            play.updateFamily();	
        }        
        
        // Log
        if (game.DEBUG){
        	 counter++;
             if (SHOULD_LOG) {
                 System.out.println("-----------------[" + counter + "]-------------------");
             }
             SHOULD_LOG = false;
        }       
    }   

    private static void checkMake(Feature feature) {
        String className = feature.getClass().getSimpleName();
        feature.check = true;
        if (feature.check()) {
            final long past = System.currentTimeMillis();
            feature.make();
            feature.make = true;
            final long maxtime = System.currentTimeMillis() - past;
            if (maxtime > feature.maxtime) {
                feature.maxtime = maxtime;     
                // Log 'maxtime'
                if (game.DEBUG){            	
                	if (feature.maxtime > criticalTime) {
        	        	System.out.println("FeatureEngine: " + className + ": " + feature.maxtime + "ms");            
        	        }
                }
            }
            
            // Log 'make'
            if (game.DEBUG){     
                System.out.println(className + " MAKE");
		        SHOULD_LOG = true;
            }
            
        }
    }
}