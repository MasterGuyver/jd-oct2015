package tanks;

import java.awt.Color;

import enums.*;
import fields.*;

public class BT7 extends AbstractTank {

	public BT7(BattleField bf) {
		super(bf);
		speed = 5;
		tankColor =  new Color(255,0,0);
		towerColor = new Color(0,255,0);
	}
	
	public BT7(BattleField bf, int x, int y, Direction direction) {
		super(bf,x,y,direction);
		speed = 5;
		tankColor =  new Color(255,0,0);
		towerColor = new Color(0,255,0);
	}
	
	@Override
	public void setUp(Action action) {
		super.action = action; 
	}
}
