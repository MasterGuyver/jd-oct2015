package tanks;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;




import interfaces.*;
import fields.*;
import enums.*;

public abstract class AbstractTank implements Tank {
	private int x;
	private int y;
	private Direction direction;
	protected Action action;
	protected int movePath = 1;
	protected int speed = 10;
	private BattleField battleField;
	
	protected Color tankColor;
	protected Color towerColor;
	
	private boolean destroyed;
	
	public AbstractTank(BattleField battleField) {
		this(battleField, 128, 512, Direction.UP);
	}

	public AbstractTank(BattleField battleField, int x, int y, Direction direction) {
		this.battleField = battleField;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.destroyed = false;
		this.action = Action.NONE;
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
	}

	@Override
	public Bullet fire()  {
		int bulletX = -100;
		int bulletY = -100;
		if (direction == Direction.UP) {
			bulletX = x + 25;
			bulletY = y - 25;
		} else if (direction == Direction.DOWN) {
			bulletX = x + 25;
			bulletY = y + 25;
		} else if (direction == Direction.LEFT) {
			bulletX = x - 25;
			bulletY = y + 25;
		} else if (direction == Direction.RIGHT) {
			bulletX = x + 25;
			bulletY = y + 25;
		}
		return new Bullet(bulletX, bulletY, direction);
	}

	public void moveRandom() throws Exception {
		Random r = new Random();
		int i;
		while (true) {
			i = r.nextInt(5);
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
			move();
		}
	};

	public void moveToQuadrant(int v, int h) throws Exception {
	
	}
	
	public void draw(Graphics g) {
		if(!destroyed) {
		g.setColor(tankColor);
		g.fillRect(this.getX(), this.getY(), 64, 64);
		g.setColor(towerColor);
		if (this.getDirection().equals(Direction.UP)) {
			g.fillRect(this.getX() + 20, this.getY(), 24, 34);
		} else if (this.getDirection().equals(Direction.DOWN)) {
			g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
		} else if (this.getDirection().equals(Direction.LEFT)) {
			g.fillRect(this.getX(), this.getY() + 20, 34, 24);
		} else {
			g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
		}
	}
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
	
}
