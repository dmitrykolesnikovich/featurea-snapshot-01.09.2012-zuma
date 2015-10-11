package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelMountains extends Level {
	public LevelMountains(){
		this(4);
	}
	public LevelMountains(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_mountains(),  graphs.level_mountains()),
		   }, 
		   new Background(sprites.level_mountains, points.point240x160x0()),
		   points.level_mountains_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_mountains;
    }
}