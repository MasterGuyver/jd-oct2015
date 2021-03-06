package hw567;

import java.awt.Color;

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
		System.out.println("Old position");
		System.out.println("Tank X: " + super.getX() + ", Tank Y: "
				+ super.getY());	
		super.move();
		System.out.println("New position");
		System.out.println("Tank X: " + super.getX() + ", Tank Y: "
				+ super.getY());	
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public String toString() {
		return "Tank is T34. It speed " + getMaxSpeed();
	}
}
