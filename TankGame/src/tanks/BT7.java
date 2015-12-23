package tanks;

import enums.*;
import fields.*;

public class BT7 extends AbstractTank {

	public BT7(BattleField bf) {
		super(bf);
		speed = 5;
		String[] names = new String[4];
		names[0] ="magTank_UP.png";
		names[1]="magTank_DOWN.png";
		names[2]="magTank_LEFT.png";
		names[3]="magTank_RIGHT.png";
		setImages(names);
	}
	
	public BT7(BattleField bf, int x, int y, Direction direction) {
		super(bf,x,y,direction);
		speed = 5;
		String[] names = new String[4];
		names[0] ="magTank_UP.png";
		names[1]="magTank_DOWN.png";
		names[2]="magTank_LEFT.png";
		names[3]="magTank_RIGHT.png";
		setImages(names);
	}
	
	@Override
	public void stepTank() {
	}
	
}
