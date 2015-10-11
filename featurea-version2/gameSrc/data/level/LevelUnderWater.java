package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelUnderWater extends Level {
	public LevelUnderWater(){
		this(4);
	}
	public LevelUnderWater(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_underwater(),  graphs.level_under_water()),				
		   }, 
		   new Background(sprites.level_under_water, points.point240x160x0()),
		   points.level_underwater_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_under_water;
    }
}