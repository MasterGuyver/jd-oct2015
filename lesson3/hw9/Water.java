package hw67;

import java.awt.Color;
import java.awt.Graphics;

public class Water implements Drawable{
	int x;
	int y;
	private BattleField battleField;
	
	public Water(BattleField battleField) {
		this(battleField,0,0);
	}
	public Water(BattleField battleField,int x, int y) {
		this.battleField = battleField;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw(Graphics g) {
					g.setColor(new Color(0, 0, 255));
					g.fillRect(x, y, 64, 64);
				}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}