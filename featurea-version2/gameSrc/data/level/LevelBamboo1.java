package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelBamboo1 extends Level {
	public LevelBamboo1(){
		this(4);
	}
	public LevelBamboo1(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_bamboo_1(),  graphs.level_bamboo_1()),	
		   }, 
		   new Background(sprites.level_bamboo_1, points.point240x160x0()),
		   points.level_bamboo_1_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_bamboo_1;
    }
}
