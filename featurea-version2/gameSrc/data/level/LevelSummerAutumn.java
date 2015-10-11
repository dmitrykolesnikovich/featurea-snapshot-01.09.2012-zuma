package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelSummerAutumn extends Level {
	public LevelSummerAutumn(){
		this(4);
	}
	public LevelSummerAutumn(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_summer_autumn(),  graphs.level_summer_autumn()),
		   }, 
		   new Background(sprites.level_summer_autumn, points.point240x160x0()),
		   points.level_summer_autumn_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_summer_autumn;
    }	
}
