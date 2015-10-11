package data.level;

import type.Level;
import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;
import areas.Pile;
import featurea.Vector3;

public class LevelBamboo2 extends Level {
	public LevelBamboo2(){
		this(4);
	}
	public LevelBamboo2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_bamboo_2(),  graphs.level_bamboo_2()),	
		   }, 
		   new Background(sprites.level_bamboo_2, points.point240x160x0()),
		   points.level_bamboo_2_scrat_1()
		);
                
                // Trick.
                // TODO: refactor this.
                Pile pile = new Pile(new Vector3(360, 240, 1.5));
                add(pile);
                piles.add(pile);
	}
	{
	   	backgroundSprite = sprites.level_bamboo_2;
    }	
}
