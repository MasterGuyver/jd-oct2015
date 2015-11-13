package hw567;

import java.awt.Color;

public class BT7 extends AbstractTank {

	public BT7(ActionField af, BattleField bf) {
		super(af,bf);
		speed = 5;
		tankColor =  new Color(255,0,0);
		towerColor = new Color(0,255,0);
	}
	
	public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {
		super(af,bf,x,y,direction);
		speed = 5;
		tankColor =  new Color(255,0,0);
		towerColor = new Color(0,255,0);
	}
	
	public void move() throws Exception {
		System.out.println("Tank X: " + super.getX() + ", Tank Y: "
				+ super.getY());
		super.move();
	}

	public int getSpeed() {
		return speed;
	}

	public String toString() {
		return "Tank is BT7. It speed " + getSpeed();
	}
}
