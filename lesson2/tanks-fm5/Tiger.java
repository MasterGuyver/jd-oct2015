package hw567;

import java.awt.Color;

public class Tiger extends Tank {
private int armor;

public Tiger(ActionField af, BattleField bf) {
	super(af,bf);
	armor = 1;
	tankColor =  new Color(105,105,105);
	towerColor = new Color(0,0,0);
}

public Tiger(ActionField af, BattleField bf, int x, int y, Direction direction) {
	super(af,bf,x,y,direction);
	armor = 1;
	tankColor =  new Color(105,105,105);
	towerColor = new Color(0,0,0);
}

public int getArmor() {
	return armor;
}

public void destroy() {
	if(armor == 1) { armor = 0; }
	else { super.destroy(); }
}
}
