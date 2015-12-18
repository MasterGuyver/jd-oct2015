package tanks;

import enums.Action;
import enums.Direction;
import fields.*;

public class T34 extends AbstractTank {

	public T34(BattleField battleField) {
		super(battleField, 128, 512, Direction.UP);
		String[] names = new String[4];
		names[0] ="greenTank_UP.png";
		names[1]="greenTank_DOWN.png";
		names[2]="greenTank_LEFT.png";
		names[3]="greenTank_RIGHT.png";
		turn(Direction.UP);
		setImages(names);
	}

	public T34(BattleField battleField, int x, int y,
			Direction direction) {
		super(battleField,x,y,direction);
		String[] names = new String[4];
		names[0] ="greenTank_UP.png";
		names[1]="greenTank_DOWN.png";
		names[2]="greenTank_LEFT.png";
		names[3]="greenTank_RIGHT.png";
		setImages(names);
	}
	
	private Object[] actoins = new Object[] {
			Action.FIRE,
			Action.MOVE,
			Action.FIRE,
			Action.MOVE,
			Action.FIRE,
			Action.FIRE,
			Direction.RIGHT
		};
		
private int step = 0;
	
	@Override
	public void stepTank() {
		if (step >= actoins.length) {
			step = 0;
		}
		if (!(actoins[step] instanceof Action)) {
			turn((Direction) actoins[step++]);
		}
		if (step >= actoins.length) {
			step = 0;
		}
		setAction((Action) actoins[step++]);
	}
	
}
