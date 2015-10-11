package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelLateAutumn extends Level {
	public LevelLateAutumn(){
		this(4);
	}
	public LevelLateAutumn(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_late_autumn1(),  graphs.level_late_autumn1()),	
				new Path(points.level_late_autumn2(),  graphs.level_late_autumn2()),
		   }, 
		   new Background(sprites.level_late_autumn, points.point240x160x0()),
		   points.level_late_autumn_scrat()
		);                
		
		changemodetime = 2000;
	}
	{
	   	backgroundSprite = sprites.level_late_autumn;
    }
}
