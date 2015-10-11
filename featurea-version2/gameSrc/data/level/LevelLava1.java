package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelLava1 extends Level {
	public LevelLava1(){
		this(4);
	}
	public LevelLava1(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_lava_1(),  graphs.level_lava_1()),
		   }, 
		   new Background(sprites.level_lava_1, points.point240x160x0()),
		   points.level_lava_1_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_lava_1;
    }	
}