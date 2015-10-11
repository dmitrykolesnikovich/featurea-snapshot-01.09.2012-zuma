package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelTreasureCave2 extends Level {
	public LevelTreasureCave2(){
		this(4);
	}
	public LevelTreasureCave2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_tresure_cave_2(),  graphs.level_tresure_cave_2()),
		   }, 
		   new Background(sprites.level_treasure_cave_2, points.point240x160x0()),
		   points.level_tresure_cave_2_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_treasure_cave_2;
    }
}