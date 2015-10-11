package featurea.android;

public class AndroidSound{
	public int resourceID = -1;
	public int soundID = -1;
	public int streamID = -1;		
	
	@Override
	public String toString() {		
		return "("+resourceID+", "+soundID+", "+streamID+")";
	}
}
