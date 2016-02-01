package tanks;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import interfaces.*;
import fields.*;
import enums.*;
import elements.*;

public abstract class AbstractTank implements Tank {
	private int x;
	private int y;
	private Direction direction;
	private Action action = Action.NONE;
	protected int movePath = 1;
	protected int speed = 10;
	private BattleField battleField;
	private Image[] iDirectionsTank;
	private boolean destroyed;
	private Bullet bullet;

	public AbstractTank(BattleField battleField) {
		this(battleField, 128, 512, Direction.UP);
	}

	public AbstractTank(BattleField battleField, int x, int y, Direction direction) {
		this.battleField = battleField;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.destroyed = false;
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

	public int getSpeed() {
		return speed;
	}

	public BattleField getBattleField() {
		return battleField;
	}

	public void updateX(int x) {
		this.x += x;
	}

	public void updateY(int y) {
		this.y += y;
	}

	public void turn(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void move() {
		action = Action.MOVE;
	}

	@Override
	public Bullet fire()  {
		int bulletX = -100;
		int bulletY = -100;
		switch(direction) {
			case UP:
				bulletX = x + 25;
				bulletY = y - 25;
				break;
			case DOWN:
				bulletX = x + 25;
				bulletY = y + 25;
				break;
			case LEFT:
				bulletX = x - 25;
				bulletY = y + 25;
				break;
			case RIGHT:
				bulletX = x + 25;
				bulletY = y + 25;
				break;
		}
		bullet = new Bullet(bulletX, bulletY, direction);
		return bullet;
	}
	@Override
	public boolean isTankBullet(Bullet bullet) {
		return bullet.equals(this.bullet);
	}

	protected void setImages(String[] names) {
		iDirectionsTank = new Image[4];
		for(int i=0; i<4; i++) {
		try {
			iDirectionsTank[i] = ImageIO.read(new File(names[i]));
		} catch (IOException e) {
			System.err.println("Can't find image: "+names[i]);
		}
		}
	} 
	@Override
	public void draw(Graphics g) {
		if(!destroyed) {
		int separator = battleField.getQuadrant(this.getX(), this.getY())
				.indexOf("_");
		int v = Integer.parseInt(battleField.getQuadrant(this.getX(),
				this.getY()).substring(0, separator));
		int u = Integer.parseInt(battleField.getQuadrant(this.getX(),
				this.getY()).substring(separator + 1));
		g.drawImage(iDirectionsTank[directNumber(this.getDirection())-1], this.getX(), this.getY(), 64, 64,
				new ImageObserver() {
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y,
					int width, int height) {
				return false;
			}
		});
		if(battleField.getBlock(v,u) instanceof Water) {
			battleField.getBlock(v,u).draw(g);
	}
		}
	}
	protected void randomTurn() {
		Random r = new Random();
		int i = r.nextInt(4)+1;
		int d = directNumber(direction) + i;
		if(d<5) {
			i = d;
		}
		switch(i) {
			case 1: turn(Direction.UP);
				break;
			case 2: turn(Direction.DOWN);
				break;
			case 3: turn(Direction.LEFT);
				break;
			case 4: turn(Direction.RIGHT);
				break;
		}
	}
	private int directNumber(Direction direct) {
		int i = 0;
		switch(direct) {
			case UP:   i = 1;
				break;
			case DOWN: i = 2;
				break;
			case LEFT: i = 3;
				break;
			case RIGHT: i = 4;
				break;
		}
		return i;
	}
	public void destroy() {
		destroyed = true;
	}
	
	public boolean hasDestroyed() {
		return destroyed;
	}
	
	@Override
	public int getMovePath() {
		return movePath;
	}
	
	@Override
	public Action getAction() {
		return action;
	}
	
	@Override
	public void setAction(Action action) {
		this.action = action;
	}
	
}
