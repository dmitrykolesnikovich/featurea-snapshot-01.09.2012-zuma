package featurea;

public class Music {
	public String ogg;
	public Object resource;
	public Music(String ogg){
		this.ogg = ogg;
	}
	@Override
	public String toString() {
		return ogg;
	}
}
