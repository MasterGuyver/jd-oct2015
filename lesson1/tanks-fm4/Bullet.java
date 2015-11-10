package hw567;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int speed = 5;
	private int x;
	private int y;
	private Direction direction;
	public Bullet(int x,int y,Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
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
	
	public void destroy() {
		x = -100;
		y = -100;
	}
	
}
