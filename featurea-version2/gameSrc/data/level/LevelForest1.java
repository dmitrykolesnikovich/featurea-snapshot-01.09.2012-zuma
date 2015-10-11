package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelForest1 extends Level {
	public LevelForest1(){
		this(4);
	}
	public LevelForest1(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_forest_1(),  graphs.level_forest_1()),	
		   }, 
		   new Background(sprites.level_forest_1, points.point240x160x0()),
		   points.level_forest_1_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_forest_1;
    }
}
