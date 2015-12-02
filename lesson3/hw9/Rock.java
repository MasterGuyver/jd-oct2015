package hw67;

import java.awt.Color;
import java.awt.Graphics;

public class Rock implements Drawable, Destroyable {
	int x;
	int y;
	private BattleField battleField;
	
	public Rock(BattleField battleField) {
		this(battleField,0,0);
	}
	public Rock(BattleField battleField,int x, int y) {
		this.battleField = battleField;
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public void destroy() {
		 String coordinates = battleField.getQuadrant(x, y);
		 int separator = coordinates.indexOf("_");
		 int v = Integer.parseInt(coordinates
				.substring(0, separator));
		 int u = Integer.parseInt(coordinates
				.substring(separator + 1));
		 battleField.updateQuadrant(v, u, "");
		 x = -100;
		 y = -100;
		
	}
	
	@Override
	public void draw(Graphics g) {
					g.setColor(new Color(131, 139, 131));
					g.fillRect(x, y, 64, 64);
				}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}			
}
