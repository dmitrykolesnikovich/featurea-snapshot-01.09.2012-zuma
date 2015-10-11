package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelBeach extends Level {
	public LevelBeach(){
		this(4);
	}
	public LevelBeach(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_beach(),  graphs.level_beach()),	
		   }, 
		   new Background(sprites.level_beach, points.point240x160x0()),
		   points.level_beach_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_beach;
    }
}