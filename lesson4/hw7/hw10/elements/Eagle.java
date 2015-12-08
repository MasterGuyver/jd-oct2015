package elements;

import java.awt.Color;

public class Eagle extends Block {
	
	public Eagle() {
		this(256, 512);
	}
	
	public Eagle(int x, int y) {
		super(x, y);
		color = new Color(139,58,58);
	}
	
}
