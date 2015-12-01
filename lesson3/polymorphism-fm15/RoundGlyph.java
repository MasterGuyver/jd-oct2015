
public class RoundGlyph extends Glyph {
	private int radius = 5;
	
	public RoundGlyph(int radius) {
		System.out.println("Round#Glyph#constructor");
		draw();
		this.radius = radius;
	}
	
	public void draw() {
		System.out.println("RoundGlyph#draw()");
		System.out.println("RoundGlyph [radius: "+radius+ "]");
	}
	
}
