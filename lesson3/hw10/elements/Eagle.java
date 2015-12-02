package elements;

import java.awt.Color;
import java.awt.Graphics;

import interfaces.*;
import fields.*;

public class Eagle implements Drawable, Destroyable {
	int x;
	int y;
	private BattleField battleField;
	
	public Eagle(BattleField battleField) {
		this(battleField, 256, 512);
	}
	
	public Eagle(BattleField battleField, int x, int y) {
		this.battleField = battleField;
		this.y = y;
		this.x = x;
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
		 this.x = -100;
		 this.y = -100;	
	}

	@Override
	public void draw(Graphics g) {
					g.setColor(new Color(139, 58, 58));
					g.fillRect(x, y, 64, 64);

				
	}
	
}
