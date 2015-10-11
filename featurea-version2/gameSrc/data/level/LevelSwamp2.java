package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelSwamp2 extends Level {
	public LevelSwamp2(){
		this(4);
	}
	public LevelSwamp2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_swamp_2(),  graphs.level_swamp_2()),
		   }, 
		   new Background(sprites.level_swamp_2, points.point240x160x0()),
		   points.level_swamp_2_scrat()
		);
	}
	{
	  	backgroundSprite = sprites.level_swamp_2;
    }	
}