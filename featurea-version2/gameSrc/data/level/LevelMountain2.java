package data.level;

import type.Level;

import data.graphs;
import data.points;
import data.sprites;
import areas.Background;
import areas.Path;

public class LevelMountain2 extends Level {
	public LevelMountain2(){
		this(4);
	}
	public LevelMountain2(int colorsCount) {		
		super(colorsCount, 0,
		   new Path[]{
				new Path(points.level_mountain_2(),  graphs.level_mountain_2()),
		   }, 
		   new Background(sprites.level_mountain_2, points.point240x160x0()),
		   points.level_mountain_2_scrat()
		);
	}
	{
	   	backgroundSprite = sprites.level_mountain_2;
    }
}