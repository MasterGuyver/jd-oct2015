package tanks;

import java.awt.Color;

import enums.*;
import fields.*;

public class Tiger extends AbstractTank {
private int armor;

public Tiger(BattleField bf) {
	super(bf);
	armor = 1;
	tankColor =  new Color(105,105,105);
	towerColor = new Color(0,0,0);
}

public Tiger(BattleField bf, int x, int y, Direction direction) {
	super(bf,x,y,direction);
	armor = 1;
	tankColor =  new Color(105,105,105);
	towerColor = new Color(0,0,0);
}

public int getArmor() {
	return armor;
}

@Override
public void destroy() {
	if(armor == 1) { armor = 0; }
	else { super.destroy(); }
}

@Override
public void setUp(Action action) {
	super.action = action; 
}

}
