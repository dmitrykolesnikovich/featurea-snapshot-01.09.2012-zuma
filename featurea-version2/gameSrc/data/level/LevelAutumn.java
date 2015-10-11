package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelAutumn extends Level {
	public LevelAutumn(){
		this(4);
	}
    public LevelAutumn(int colorsCount) {        
        super(colorsCount, 0,
                new Path[]{
                    new Path(points.level_autumn(), graphs.level_autumn()),},
                new Background(sprites.level_autumn, points.point240x160x0()),
                points.level_autumn_scrat());
    }
    {
    	backgroundSprite = sprites.level_autumn;
    }
    
}
