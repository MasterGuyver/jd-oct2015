package tanks;

import java.awt.Color;
import java.awt.Graphics;
import interfaces.*;
import enums.Direction;

public class Bullet implements Drawable, Destroyable {
	private int speed = 5;
	private int x;
	private int y;
	private Direction direction;
	boolean destroyed;
	public Bullet(int x,int y,Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		destroyed = false;
	}
	public int getSpeed() {
		return speed;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Direction getDirection() {
		return direction;
	}
	public void updateX(int d) {
		x+=d;
	}
	public void updateY(int d) {
		y+=d;
	}
	
	public void draw(Graphics g) {
		if(!destroyed) {
		g.setColor(new Color(255, 255, 0));
		g.fillOval(this.getX(), this.getY(), 12, 12); 
		}
	}
	
	public void destroy() {
		destroyed = true;
	}
	
	public boolean hasDestroyed() {
		return destroyed;
	}
	
}
