package game;

import data.level.*;
import type.Level;

public class levels {	
    public static Level getLevel(int index){     
    	if (index<0){index=0;}    	
        int loopIndex = index%53;
        switch(loopIndex){
                // 4 colors.		
                case 4:{return new LevelAutumn(4); }
                case 0:{return new LevelAutumn2(4); }
                case 2:{return new LevelAztec2(4); }
                case 3:{return new LevelBamboo1(4); }
                case 1:{return new LevelBamboo2(4); }
                case 5:{return new LevelBeach(4); }
                case 6:{return new LevelBeach2(4); }
                case 7:{return new LevelForest1(4); }
                case 8:{return new LevelForest2(4); }
                case 9:{return new LevelLateAutumn(4); }
                case 10:{return new LevelLateAutumn2(4); }
                case 11:{return new LevelLava1(4); }
                case 12:{return new LevelLava2(4); }
                case 13:{return new LevelMountain2(4); }
                case 14:{return new LevelMountains(4); }
                case 15:{return new LevelSky1(4); }
                case 16:{return new LevelWinter2(5); }
                case 17:{return new LevelSpring(4); }
                case 18:{return new LevelSpring2(4); }
                case 19:{return new LevelStump(4);}
                case 20:{return new LevelSummerAutumn(4); }
                case 21:{return new LevelTreasureCave2(5); }
                case 22:{return new LevelUnderWater(5); }       
                case 23:{return new LevelTreasureCave1(4); }
                case 24:{return new LevelTreasureCave2(4); }
                case 25:{return new LevelUnderWater(4); }
                case 26:{return new LevelUnderWater2(4); }
                case 27:{return new LevelWinter1(4); }
                case 28:{return new LevelWinter2(4); }

                // 5 colors.
                case 29:{return new LevelAutumn(5); }
                case 30:{return new LevelAutumn2(5); }
                case 31:{return new LevelAztec2(5); }
                case 32:{return new LevelBamboo1(5); }
                case 33:{return new LevelBamboo2(5); }
                case 34:{return new LevelBeach(5); }
                case 35:{return new LevelBeach2(5); }
                case 36:{return new LevelForest1(5); }
                case 37:{return new LevelForest2(5); }
                case 38:{return new LevelLateAutumn(5); }
                case 39:{return new LevelLateAutumn2(5); }
                case 40:{return new LevelLava1(5); }
                case 41:{return new LevelLava2(5); }
                case 42:{return new LevelMountain2(5); }
                case 43:{return new LevelMountains(5); }
                case 44:{return new LevelSky1(5); }
                case 45:{return new LevelSky2(5); }
                case 46:{return new LevelSpring(5); }
                case 47:{return new LevelSpring2(5); }
                case 48:{return new LevelStump(5); }
                case 49:{return new LevelSummerAutumn(5); }              
                case 50:{return new LevelUnderWater2(5); }
                case 51:{return new LevelWinter1(5); }
                case 52:{return new LevelTreasureCave1(5); }
        }
        return null;
    }	
}
