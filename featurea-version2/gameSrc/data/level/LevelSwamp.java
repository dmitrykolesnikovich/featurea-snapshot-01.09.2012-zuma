package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelSwamp extends Level {
	public LevelSwamp(){
		this(4);
	}
	public LevelSwamp(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_swamp(),  graphs.level_swamp()),
		   }, 
		   new Background(sprites.level_swamp, points.point240x160x0()),
		   points.level_swamp_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_swamp;
    }
}