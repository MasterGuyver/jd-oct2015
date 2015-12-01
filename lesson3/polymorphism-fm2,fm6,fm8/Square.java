package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends Rectangle {
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0));
		g.fillRect(200, 400, 200, 200);
	}
}
