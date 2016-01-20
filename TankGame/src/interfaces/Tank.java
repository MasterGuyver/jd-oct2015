package interfaces;

import tanks.Bullet;
import enums.*;


public interface Tank extends Drawable, Destroyable {
	
	public void stepTank();
	
	public Action getAction();
	
	public void setAction(Action action);
	
	public void move();

	public Bullet fire();

	public int getX();

	public int getY();
	
	public Direction getDirection();
	
	public void updateX(int x);

	public void updateY(int y);
	
	public int getSpeed();
	
	public int getMovePath();

	public boolean hasDestroyed();
	
	public void turn(Direction direction);

	public boolean isTankBullet(Bullet bullet);
	
}
