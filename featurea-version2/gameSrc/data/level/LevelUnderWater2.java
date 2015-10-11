package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelUnderWater2 extends Level {
	public LevelUnderWater2(){
		this(4);
	}
	public LevelUnderWater2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_underwater_2_1(),  graphs.level_under_water_2_1()),
				new Path(points.level_underwater_2_2(),  graphs.level_under_water_2_2()),
		   }, 
		   new Background(sprites.level_under_water_2, points.point240x160x0()),
		   points.level_underwater_2_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_under_water_2;
    }
}