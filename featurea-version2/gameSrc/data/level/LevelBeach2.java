package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelBeach2 extends Level {
	public LevelBeach2(){
		this(4);
	}
	public LevelBeach2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_beach_2(),  graphs.level_beach_2()),	
		   }, 
		   new Background(sprites.level_beach_2, points.point240x160x0()),
		   points.level_beach_2_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_beach_2;
    }
}
