package elements;
import java.awt.Color;
import java.awt.Graphics;

import interfaces.*;

public abstract class Block implements Drawable, Destroyable {
	private int x;
	private int y;
	private boolean destroyed = false;
	
	Color color;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void destroy() {
		 destroyed = true;
	}

	public boolean hasDestroyed() {
		return destroyed;
	}
	
	@Override
	public void draw(Graphics g) {
		if(!destroyed) {
					g.setColor(color);
					g.fillRect(x, y, 64, 64);
				}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
