package tanks;

import elements.*;
import enums.*;
import fields.BattleField;
import interfaces.Tank;

public class Tiger extends AbstractTank {
	private int armor;
	private int selector;
	private Tank defender;
	private Object[] actoins = new Object[64];;
	private int step = 0;

	public Tiger(BattleField bf) {
		super(bf);
		armor = 1;
		String[] names = new String[4];
		names[0] = "tiger_up.png";
		names[1] = "tiger_down.png";
		names[2] = "tiger_left.png";
		names[3] = "tiger_right.png";
		setImages(names);
		String location = bf.getAgressorLocation();
		int x = Integer.parseInt(location.split("_")[1]);
		int y =	Integer.parseInt(location.split("_")[0]);
		updateX(-128+x);
		updateY(-512+y);
		turn(Direction.DOWN);
		selector = 0;
	}

	public Tiger(BattleField bf, int x, int y, Direction direction) {
		super(bf, x, y, direction);
		armor = 1;
		String[] names = new String[4];
		names[0] = "tiger_up.png";
		names[1] = "tiger_down.png";
		names[2] = "tiger_left.png";
		names[3] = "tiger_right.png";
		setImages(names);
		turn(Direction.DOWN);
		selector=0;
		
	}
	
	public Tiger(BattleField bf, Tank d) {
		super(bf);
		armor = 1;
		String[] names = new String[4];
		names[0] = "tiger_up.png";
		names[1] = "tiger_down.png";
		names[2] = "tiger_left.png";
		names[3] = "tiger_right.png";
		setImages(names);
		String location = bf.getAgressorLocation();
		int x = Integer.parseInt(location.split("_")[1]);
		int y =	Integer.parseInt(location.split("_")[0]);
		updateX(-128+x);
		updateY(-512+y);
		turn(Direction.DOWN);
		defender = d;
		selector = 1;
	}


	public int getArmor() {
		return armor;
	}

	private void InitialSteps() {
		switch(selector) {
		case 0: agressorOnEagle(); break;
		case 1: agressorOnDefender(); break;
		}
	}
	
	@Override
	public void destroy() {
		if (armor > 0) {
			armor = 0;
		} else {
			super.destroy();
		}
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

   private void agressorOnDefender() {
		int stp = 0;
		int x = this.getX();
		int y = this.getY();
		int separator = getBattleField().getQuadrant(x, y).indexOf("_");
		int vt = Integer.parseInt(getBattleField().getQuadrant(x,y).substring(0, separator));
		int ut = Integer.parseInt(getBattleField().getQuadrant(x,y).substring(separator + 1));
		x = defender.getX(); y = defender.getY();
		separator = getBattleField().getQuadrant(x, y).indexOf("_");
		int vd = Integer.parseInt(getBattleField().getQuadrant(x,y).substring(0, separator));
		int ud = Integer.parseInt(getBattleField().getQuadrant(x,y).substring(separator + 1));
		if (ut == ud) {
			if(vt < vd) {
				actoins[stp++] = Direction.DOWN;
				actoins[stp++] =Action.FIRE;
			}
			else {
				actoins[stp++] = Direction.UP;
				actoins[stp++] =Action.FIRE;
			}
		}
		else if (vt == vd) {
			if(ut < ud) {
				actoins[stp++] = Direction.RIGHT;
				actoins[stp++] =Action.FIRE;
			}
			else {
				actoins[stp++] = Direction.LEFT;
				actoins[stp++] =Action.FIRE;
			}
		}
		else if (ut < ud) {
			if(ut+1<=ud) {
				actoins[stp++] = Direction.RIGHT;
				if (!hasNoBlock(ut+1,vt)) {
					actoins[stp++] =Action.FIRE;
				}
				actoins[stp++] = Action.MOVE;
			}
		}
		else if (ut > ud) {
			if(ut - 1 >= ud ) {
				actoins[stp++] = Direction.LEFT;
				if (!hasNoBlock(ut-1,vt)) {
					actoins[stp++] =Action.FIRE;
				}
				actoins[stp++] = Action.MOVE;
			}
		}
		else if (vt < vd  ) {
			if (vt+1 <= vd) {
				actoins[stp++] = Direction.DOWN;
				if (!hasNoBlock(ut,vt+1)) {
					actoins[stp++] =Action.FIRE;
				}
				actoins[stp++] = Action.MOVE;
			}
		}
		else if(vt > vd ) {
			if (vt -1 >= vd) {
				actoins[stp++] = Direction.UP;
				if (!hasNoBlock(ut,vt-1)) {
					actoins[stp++] =Action.FIRE;
				}
				actoins[stp++] = Action.MOVE;
			}
		}
		actoins[stp] = Action.NONE;
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
		if (step == 0) InitialSteps();
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
