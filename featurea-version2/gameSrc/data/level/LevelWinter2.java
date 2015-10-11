package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelWinter2 extends Level{
	public LevelWinter2(){
		this(4);
	}
	public LevelWinter2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_winter_2(),  graphs.level_winter_2()),				
		   }, 
		   new Background(sprites.level_winter_2, points.point240x160x0()),
		   points.level_winter_2_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_winter_2;
    }
}