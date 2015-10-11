package data;

import featurea.Axis;
import featurea.Axis.Turn;

public class axes{
	public static final Axis ax0() {return new Axis(0, null);}
	public static final Axis scratRotateWhileFail() {return new Axis(0, new Turn[]{new Turn(1000, -400)});}
}