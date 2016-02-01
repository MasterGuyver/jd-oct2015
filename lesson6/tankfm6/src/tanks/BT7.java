package tanks;

import elements.Blank;
import elements.Water;
import enums.*;
import fields.*;

public class BT7 extends AbstractTank {
	private int selector;
	private Object[] actoins = new Object[64];;
	private int step = 0;

	public BT7(BattleField bf) {
		super(bf);
		speed = 5;
		String[] names = new String[4];
		names[0] ="magTank_UP.png";
		names[1]="magTank_DOWN.png";
		names[2]="magTank_LEFT.png";
		names[3]="magTank_RIGHT.png";
		setImages(names);
		String location = bf.getAgressorLocation();
		int x = Integer.parseInt(location.split("_")[1]);
		int y =	Integer.parseInt(location.split("_")[0]);
		updateX(-128+x);
		updateY(-512+y);
		turn(Direction.DOWN);
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

	private void agressorOnEagle() {
		int count = 0;
		String location = getBattleField().getQuadrant(this.getX(), this.getY());
		int agru = Integer.parseInt(location.split("_")[1]);
		int agrv = Integer.parseInt(location.split("_")[0]);
		if (agru < 4 ) {
			if(agru + 1 <=4) {
				actoins[count++] = Direction.RIGHT;
				if (!hasNoBlock(agru+1,agrv)) {
					actoins[count++] = Action.FIRE;
				}
				actoins[count++] = Action.MOVE;
			}
		}
		else if (agru > 4) {
			if(agru-1 >= 4) {
				actoins[count++] = Direction.LEFT;
				if (!hasNoBlock(agru-1,agrv)) {
					actoins[count++] = Action.FIRE;
				}
				actoins[count++] = Action.MOVE;
			}
		}
		if (agrv < 8) {
			if(agrv+1 <=8)
				actoins[count++] = Direction.DOWN;
			if (!hasNoBlock(agru,agrv+1)) {
				actoins[count++] = Action.FIRE;
			}
			actoins[count++] = Action.MOVE;
		}
		actoins[count] = Action.NONE;
	}

	private boolean hasNoBlock(int v, int u) {
		boolean flag = false;
		if (u>-1 && u <9 && v>-1 && v<9) {
			flag = flag || getBattleField().getBlock(u, v) instanceof Water ||
					getBattleField().getBlock(u, v) instanceof Blank;
		}
		return flag;
	}

	@Override
	public void stepTank() {
		if (step == 0) agressorOnEagle();
		if (actoins[step] instanceof Direction) {
			turn((Direction) actoins[step++]);
		} else {
			if ((Action) actoins[step] != Action.NONE)
			{ setAction((Action) actoins[step++]);
			}
			else {
				step = 0;
			}
		}
	}
	
}
