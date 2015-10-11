package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelTreasureCave1 extends Level {
	public LevelTreasureCave1(){
		this(4);
	}
	public LevelTreasureCave1(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_tresure_cave_1(),  graphs.level_tresure_cave_1()),
		   }, 
		   new Background(sprites.level_treasure_cave_1, points.point240x160x0()),
		   points.level_tresure_cave_1_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_treasure_cave_1;
    }
}
