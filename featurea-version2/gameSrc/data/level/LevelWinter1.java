package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelWinter1 extends Level{
	public LevelWinter1(){
		this(4);
	}
	public LevelWinter1(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_winter_1_1(),  graphs.level_winter_1_1()),
				new Path(points.level_winter_1_2(),  graphs.level_winter_1_2()),
		   }, 
		   new Background(sprites.level_winter_1, points.point240x160x0()),
		   points.level_winter_1_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_winter_1;
    }
}