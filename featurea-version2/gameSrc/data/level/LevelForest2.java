package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelForest2 extends Level {
	public LevelForest2(){
		this(4);
	}
	public LevelForest2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_forest_2(),  graphs.level_forest_2()),	
		   }, 
		   new Background(sprites.level_forest_2, points.point240x160x0()),
		   points.level_forest_2_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_forest_2;
    }
}