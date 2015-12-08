package tanks;

import java.awt.Color;

import enums.Action;
import enums.Direction;
import fields.*;

public class T34 extends AbstractTank {

	public T34(BattleField battleField) {
		super(battleField, 128, 512, Direction.UP);
		tankColor = new Color(0,255,0);
		towerColor = new Color(255,0,0);
	}

	public T34(BattleField battleField, int x, int y,
			Direction direction) {
		super(battleField,x,y,direction);
		tankColor =  new Color(0,255,0);
		towerColor = new Color(255,0,0);
	}
		
	@Override
	public void setUp(Action action) {
		super.action = action; 
	}
}
