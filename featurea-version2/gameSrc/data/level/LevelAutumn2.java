package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelAutumn2 extends Level {
	public LevelAutumn2(){
		this(4);
	}
	public LevelAutumn2(int colorsCount) {
            super(colorsCount, 0,
               new Path[]{
                    new Path(points.level_autumn_2(),  graphs.level_autumn_2()),
               },
               new Background(sprites.level_autumn_2, points.point240x160x0()),
               points.level_aztec_2_scrat()
            );
	}
	{
	   	backgroundSprite = sprites.level_autumn_2;
    }

}