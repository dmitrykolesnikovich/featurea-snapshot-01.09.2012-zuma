package featurea;

public class Text {
    public String style;
    public int color;
    public int size;
    public String font;
    public String string;
    public Text(String string, String font,  int size, int color, String style) {
        this.string = string;
        this.font = font;
        this.size = size;
        this.color = color;
        this.style = style;
    }	
}