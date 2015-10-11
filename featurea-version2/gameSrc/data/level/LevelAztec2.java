package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelAztec2 extends Level {
	public LevelAztec2(){
		this(4);
	}
	public LevelAztec2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_aztec_2(),  graphs.level_aztec_2()),				
		   }, 
		   new Background(sprites.level_aztec_2, points.point240x160x0()),
		   points.level_aztec_2_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_aztec_2;
    }
}