package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelLava2 extends Level {
	public LevelLava2(){
		this(4);
	}
	public LevelLava2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_lava_2(),  graphs.level_lava_2()),
		   }, 
		   new Background(sprites.level_lava_2, points.point240x160x0()),
		   points.level_lava_2_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_lava_2;
    }	
}