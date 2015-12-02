package hw567;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractTank implements Drawable, Destroyable {
	private int x;
	private int y;
	private Direction direction;
	protected int speed = 8;
	private ActionField actionField;
	private BattleField battleField;
	
	protected Color tankColor;
	protected Color towerColor;
	
	public AbstractTank(ActionField actionField, BattleField battleField) {
		this(actionField, battleField, 128, 512, Direction.UP);
	}

	public AbstractTank(ActionField actionField, BattleField battleField, int x, int y,
			Direction direction) {
		this.actionField = actionField;
		this.battleField = battleField;
		this.x = x;
		this.y = y;
		this.direction = direction;
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

	public ActionField getActionField() {
		return actionField;
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

	public void turn(Direction direction) throws Exception {
		this.direction = direction;
		actionField.processTurn(this);
	}

	public void move() throws Exception {
		actionField.processMove(this);
	}

	public void fire() throws Exception {
		Bullet bullet = new Bullet(x + 25, y + 25,direction);
		actionField.processFire(bullet);
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
		actionField.processMoveToQuadrant(v, h);
	}
	
	public void draw(Graphics g) {
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
	public void destroy() {
		x = -100;
		y = -100;
	}
}
