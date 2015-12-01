
public class Launcher {

	public static void main(String[] args) {
		Glyph glyph = new RoundGlyph(10);
		glyph.draw();
		Glyph glt = new NullGlyph();
		glt.draw();
	}
}
