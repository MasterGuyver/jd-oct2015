package tanks;

import elements.*;
import enums.Action;
import enums.Direction;
import fields.*;

public class T34 extends AbstractTank {

	private Object[] actoins = new Object[8];
	private int step = 0;

	public T34(BattleField battleField) {
		super(battleField, 128, 512, Direction.UP);
		String[] names = new String[4];
		names[0] = "greenTank_UP.png";
		names[1] = "greenTank_DOWN.png";
		names[2] = "greenTank_LEFT.png";
		names[3] = "greenTank_RIGHT.png";
		turn(Direction.UP);
		setImages(names);
	}

	public T34(BattleField battleField, int x, int y,
			   Direction direction) {
		super(battleField, x, y, direction);
		String[] names = new String[4];
		names[0] = "greenTank_UP.png";
		names[1] = "greenTank_DOWN.png";
		names[2] = "greenTank_LEFT.png";
		names[3] = "greenTank_RIGHT.png";
		setImages(names);
	}

	private void defenderOfEagle() {
		int step = 0;
		Direction direction = this.getDirection();
		int x = this.getX();
		int y = this.getY();
		int separator = getBattleField().getQuadrant(x, y).indexOf("_");
		int vt = Integer.parseInt(getBattleField().getQuadrant(x, y).substring(0, separator));
		int ut = Integer.parseInt(getBattleField().getQuadrant(x, y).substring(separator + 1));
		if (vt > 6 && vt < 9 && (ut == 2 || ut == 6)) {
			switch (ut) {
				case 2:
					actoins[step+2] = direction;
					actoins[step+3] = tankNextAct();
					actoins[step+4] = Action.NONE;
					actoins[step++] = Direction.LEFT;
					actoins[step++] = Action.FIRE;
					break;
				case 6:
					actoins[step+2] = direction;
					actoins[step+3] = tankNextAct();
					actoins[step+4] = Action.MOVE;
					actoins[step+5] = Action.NONE;
					actoins[step++] = Direction.RIGHT;
					actoins[step++] = Action.FIRE;
					break;
			}
		}
		else if (vt == 6 && ut > 2 && ut < 6) {
			actoins[step+2] = direction;
			actoins[step+3] = tankNextAct();
			actoins[step+4] = Action.NONE;
			actoins[step++] = Direction.UP;
			actoins[step++] = Action.FIRE;
		} else if (vt == 6 && (ut == 2 || ut == 6)) {
			switch (ut) {
				case 2:
					switch (direction) {
						case UP:
							actoins[step+3] = Action.FIRE;
							actoins[step+4] = Direction.RIGHT;
							actoins[step+5] = tankNextAct();
							actoins[step+6] = Action.NONE;
							break;
						case DOWN:
							actoins[step+2] = tankNextAct();
							actoins[step+3] = Action.NONE;
							break;
						default:
							actoins[step+3] = Action.NONE;
					}
					actoins[step+2] = direction;
					actoins[step++] = Action.FIRE;
					actoins[step++] = Direction.LEFT;
					break;
				case 6:
					switch (direction) {
						case UP:
							actoins[step+3] = Action.FIRE;
							actoins[step+4] = Direction.LEFT;
							actoins[step+5] = tankNextAct();
							actoins[step+6] = Action.NONE;
							break;
						case DOWN:
							actoins[step+3] = tankNextAct();
							actoins[step+4] = Action.NONE;
							break;
						default:
							actoins[step+3] = Action.NONE;
					}
					actoins[step+2] = direction;
					actoins[step++] = Direction.RIGHT;
					actoins[step++] = Action.FIRE;
					break;
			}
		} else {
			if (ut < 2) {
				actoins[step++] = Direction.RIGHT;
				actoins[step++] = tankNextAct();
			} else if (ut > 5) {
				actoins[step++] = Direction.LEFT;
				actoins[step++] = tankNextAct();
			}
			if (vt < 6) {
				actoins[step++] = Direction.DOWN;
				actoins[step++] = tankNextAct();
			} else if (vt > 6) {
				actoins[step++] = Direction.UP;
				actoins[step++] = tankNextAct();
			}
			actoins[step++] = Action.NONE;
		}
	}

	private Object tankNextAct() {
		Object act = Action.MOVE;
		Direction direction = this.getDirection();
		int x = this.getX();
		int y = this.getY();
		int separator = getBattleField().getQuadrant(x, y).indexOf("_");
		int vt = Integer.parseInt(getBattleField().getQuadrant(x, y).substring(0, separator));
		int ut = Integer.parseInt(getBattleField().getQuadrant(x, y).substring(separator + 1));
		switch (direction) {
			case UP:
				--vt;
				break;
			case LEFT:
				--ut;
				break;
			case RIGHT:
				++ut;
				break;
			default:
				++vt;
		}
		if (ut > -1 && ut < 9 && vt > -1 && vt < 9) {
			Block block = getBattleField().getBlock(vt, ut);
			if (block instanceof Brick) {
				act = Action.FIRE;
			}
			else if (block instanceof Rock) {
				act = randomDirection();
			}
		} else {
			act = invertDirection();
		}
		return act;
	}

	private Object randomDirection() {
		randomTurn();
		return (this.getDirection());
	}

	private Object invertDirection() {
		Object act;
		switch (this.getDirection()) {
			case UP:
				act = Direction.DOWN;
				break;
			case LEFT:
				act = Direction.RIGHT;
				break;
			case RIGHT:
				act = Direction.LEFT;
				break;
			default:
				act = Direction.UP;
		}
		return act;
	}

	private void defenderAction() {
		int step = 0;
		actoins[step++] = Direction.UP;
		actoins[step++] = tankNextAct();
		actoins[step++] = Action.NONE;
	}

	@Override
	public void stepTank() {
		if (step == 0) defenderOfEagle();
		if (actoins[step] instanceof Direction) {
			turn((Direction) actoins[step++]);
		} else {
			if ((Action) actoins[step] != Action.NONE) {
				setAction((Action) actoins[step++]);
			} else {
				step = 0;
			}
		}
	}
}
