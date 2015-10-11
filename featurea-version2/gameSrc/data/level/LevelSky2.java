package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelSky2 extends Level {
	public LevelSky2(){
		this(4);
	}
	public LevelSky2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_sky_2(),  graphs.level_sky_2()),
		   }, 
		   new Background(sprites.level_sky_2, points.point240x160x0()),
		   points.level_sky_2_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_sky_2;
    }
}