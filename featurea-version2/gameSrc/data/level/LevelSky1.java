package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelSky1 extends Level {
	public LevelSky1(){
		this(4);
	}
	public LevelSky1(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_sky_1(),  graphs.level_sky_1()),
		   }, 
		   new Background(sprites.level_sky_1, points.point240x160x0()),
		   points.level_sky_1_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_sky_1;
    }
}