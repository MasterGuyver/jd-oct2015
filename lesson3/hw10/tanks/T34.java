package tanks;

import java.awt.Color;

import enums.Direction;

import fields.*;

public class T34 extends AbstractTank {
	private int maxSpeed = 50;

	public T34(ActionField actionField, BattleField battleField) {
		super(actionField, battleField, 128, 512, Direction.UP);
		tankColor = new Color(0,255,0);
		towerColor = new Color(255,0,0);
	}

	public T34(ActionField actionField, BattleField battleField, int x, int y,
			Direction direction) {
		super(actionField,battleField,x,y,direction);
		tankColor =  new Color(0,255,0);
		towerColor = new Color(255,0,0);
	}
	
	public void move() throws Exception {	
		super.move();
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public String toString() {
		return "Tank is T34. It speed " + getMaxSpeed();
	}
}
