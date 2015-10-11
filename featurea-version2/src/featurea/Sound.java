package featurea;

public class Sound {
	
	public String ogg;
	public Object resource;
	public Sound(String ogg){
		this.ogg = ogg;
	}
	@Override
	public String toString() {
		return ogg;
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Sound)){return false;}
		Sound sound = (Sound)o;
		return sound.ogg.equals(ogg);
	}
	
}