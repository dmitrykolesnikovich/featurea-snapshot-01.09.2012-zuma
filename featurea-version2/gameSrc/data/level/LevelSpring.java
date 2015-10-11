package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelSpring extends Level {
	public LevelSpring(){
		this(4);
	}
	public LevelSpring(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_spring(),  graphs.level_spring()),
		   }, 
		   new Background(sprites.level_spring, points.point240x160x0()),
		   points.level_spring_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_spring;
    }
}