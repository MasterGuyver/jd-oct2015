package fields;

import interfaces.*;
import start.MenuDraw;
import start.SceneSelect;
import elements.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import tanks.*;
import enums.*;

public class ActionField extends JPanel {
	private BattleField battleField;
	private Tank defender;
	private Tank agressor;
	private Bullet bullet;
	
	 public void runTheGame() throws Exception {
	 tanksAction();
	 }

	private void tanksAction() throws Exception {
		while (true) {
			//agressor.stepTank();
			defender.stepTank();
			if (!agressor.hasDestroyed() && !defender.hasDestroyed()) {
				processAction(agressor);
			}
			if (!agressor.hasDestroyed() && !defender.hasDestroyed()) {
				processAction(defender);
			}
		}
	}

	private void processAction(Tank t) throws Exception {
		switch(t.getAction()) {
			case FIRE: processTurn(t);
				       processFire(t);
				break;
			case MOVE: processMove(t);
				break;
		}
	}

	public void processTurn(Tank tank) throws Exception {
		repaint();
	}

	public void processMove(Tank tank) throws Exception {
		int step = 1;
		for (int i = 0; i < tank.getMovePath(); i++) {
			int covered = 0;
			if (!checkLimits(tank) && !checkQuadrant(tank)) {
				while (covered < 64) {
					switch(tank.getDirection()) {
						case UP: tank.updateY(-step);
							break;
						case DOWN: tank.updateY(step);
							break;
						case LEFT: tank.updateX(-step);
							break;
						default: tank.updateX(step);
					}
					covered += step;
					repaint();
					Thread.sleep(tank.getSpeed());
				}
			}
				else {
					break;
				}
		}
	}


	public void processFire(Tank tank) throws Exception {
		bullet = tank.fire();
		int step = 1;
		while ((bullet.getX() > -19 && bullet.getX() < 562) && (bullet.getY() > -19 && bullet.getY() < 562)) {
			switch(tank.getDirection()) {
				case UP: bullet.updateY(-step);
					break;
				case DOWN: bullet.updateY(step);
					break;
				case LEFT: bullet.updateX(-step);
					break;
				default: bullet.updateX(step);
			}
			if (processInterception(tank)) {
				bullet.destroy();
			}
			Thread.sleep(bullet.getSpeed());
			if (bullet.hasDestroyed()) {
				break;
			}
			repaint();
		}
		bullet.destroy();
	}

	private boolean checkLimits(Tank tank) {
		return (tank.getDirection().equals(Direction.UP) && tank.getY() == 0)
				|| (tank.getDirection().equals(Direction.DOWN) && tank.getY() >= 512)
				|| (tank.getDirection().equals(Direction.LEFT) && tank.getX() == 0)
				|| (tank.getDirection().equals(Direction.RIGHT) && tank.getX() >= 512);
	}

	private boolean checkQuadrant(Tank tank) {
		String tankQuadrant = battleField.getQuadrant(tank.getX(), tank.getY());
		int v = Integer.parseInt(tankQuadrant.split("_")[0]);
		int h = Integer.parseInt(tankQuadrant.split("_")[1]);
		boolean flag = false;
		switch(tank.getDirection()) {
			case UP: v--;
				break;
			case DOWN: v++;
				break;
			case RIGHT: h++;
				break;
			case LEFT: h--;
				break;
		}
		Block block = battleField.getBlock(v, h);
		if (!(block instanceof Blank) && !block.hasDestroyed() && !(block instanceof Water)) {
			flag = true ;
		}
		return flag;
	}

	private boolean checkTankInterception() {
			return defender.getX() == agressor.getX() && defender.getY() == agressor.getY();
		}

	private boolean processInterception(Tank t) {
		int separator = battleField.getQuadrant(bullet.getX(), bullet.getY()).indexOf("_");
		int v = Integer.parseInt(battleField.getQuadrant(bullet.getX(), bullet.getY()).substring(0, separator));
		int u = Integer.parseInt(battleField.getQuadrant(bullet.getX(), bullet.getY()).substring(separator + 1));
		if (v > -1 && v < 9 && u > -1 && u < 9) {
			Block block = battleField.getBlock(v, u);
			if (!block.hasDestroyed() && !(block instanceof Blank) && !(block instanceof Water)) {
				if (!(t instanceof Tiger) && (block instanceof Rock)) {
					bullet.destroy();
				} else {
					battleField.destroyBlock(v, u);
				}
				return true;
			}

			if (!defender.hasDestroyed() && checkInterception(bullet,defender)) {
				defender.destroy();
				return true;
			}
			if (!agressor.hasDestroyed() && checkInterception(bullet,agressor)) {
				agressor.destroy();
				return true;
			}
		}
		return false;
	}

	private boolean checkInterception(Bullet bullet, Tank tank) {
		String quadrantTank = battleField.getQuadrant(tank.getX(), tank.getY());
		String quadrantBullet = battleField.getQuadrant(bullet.getX(), bullet.getY());
		int ty = Integer.parseInt(quadrantTank.split("_")[0]);
		int tx = Integer.parseInt(quadrantTank.split("_")[1]);

		int by = Integer.parseInt(quadrantBullet.split("_")[0]);
		int bx = Integer.parseInt(quadrantBullet.split("_")[1]);

		if (ty > -1 && ty < 9 && tx > -1 && tx < 9) {
			if (ty == by && tx == bx && !tank.isTankBullet(bullet)) {
				return true;
			}
		}
		return false;
	}

	    public ActionField() throws Exception {
		battleField = new BattleField();
		JFrame frame = new JFrame("BATTLE TANKS");
		frame.setLocation(300, 150);
		frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 8, battleField.getBF_HEIGHT() + 55));
		MenuDraw menu = new MenuDraw(frame,true);
		defender = new T34(battleField);
		bullet = new Bullet(-100, -100, Direction.NONE);
		agressor = new Tiger(battleField);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	/* public ActionField(String[][] field, String nameTank) throws Exception {
		battleField = new BattleField(field);
		JFrame frame = new JFrame("GAME BEGIN");
		frame.setLocation(300, 150);
		frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 8, battleField.getBF_HEIGHT() + 55));
		MenuDraw menu = new MenuDraw(frame,true);
		defender = new T34(battleField);
		bullet = new Bullet(-100, -100, Direction.NONE);
		if(nameTank.equals("Tiger")) {
			agressor = new Tiger(battleField, defender);
		}
		if(nameTank.equals("BT7")) {
			agressor = new BT7(battleField);
		}
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	} */
	@Override
	 protected void paintComponent(Graphics g) { 
		 super.paintComponent(g);
	     battleField.draw(g);  bullet.draw(g);
	      agressor.draw(g); defender.draw(g);

}
}