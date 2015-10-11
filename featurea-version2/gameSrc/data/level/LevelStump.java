package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelStump extends Level {
	public LevelStump(){
		this(4);
	}
	public LevelStump(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_stump(),  graphs.level_stump()),
		   }, 
		   new Background(sprites.level_stump, points.point240x160x0()),
		   points.level_stump_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_stump;
    }	
}