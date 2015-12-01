package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Triangle extends Shape {
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g.setColor(new Color(0,192,255));
		int[] x = new int[] {300,500,600};
		int[] y = new int[] {100,300,100};
		g.drawPolygon(x,y,3);
	}
}
