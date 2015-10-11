package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelSpring2 extends Level {
	public LevelSpring2(){
		this(4);
	}
	public LevelSpring2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_spring_2(),  graphs.level_spring_2()),
		   }, 
		   new Background(sprites.level_spring_2, points.point240x160x0()),
		   points.level_spring_2_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_spring_2;
    }
}