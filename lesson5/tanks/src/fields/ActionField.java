package fields;

import interfaces.*;
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
			agressor.stepTank();
			defender.stepTank();
			if (!agressor.hasDestroyed() && !defender.hasDestroyed()) {
				processAction(agressor);
			}
			if (!agressor.hasDestroyed() && !defender.hasDestroyed()) {
				processAction(defender);
			}
			// else break;
		}
	}

	private void processAction(Tank t) throws Exception {
		if (t.getAction() == Action.MOVE) {
			processMove(t);
		} else if (t.getAction() == Action.FIRE) {
			processTurn(t);
			processFire(t);
		}
	}

	public void processTurn(Tank tank) throws Exception {
		repaint();
	}

	public void processMove(Tank tank) throws Exception {
		int step = 1;

		for (int i = 0; i < tank.getMovePath(); i++) {
			int covered = 0;

			String tankQuadrant = battleField.getQuadrant(tank.getX(), tank.getY());
			int v = Integer.parseInt(tankQuadrant.split("_")[0]);
			int h = Integer.parseInt(tankQuadrant.split("_")[1]);

			// check limits x: 0, 513; y: 0, 513
			if ((tank.getDirection().equals(Direction.UP) && tank.getY() == 0)
					|| (tank.getDirection().equals(Direction.DOWN) && tank.getY() >= 512)
					|| (tank.getDirection().equals(Direction.LEFT) && tank.getX() == 0)
					|| (tank.getDirection().equals(Direction.RIGHT) && tank.getX() >= 512)) {
				return;
			}

			if (tank.getDirection() == Direction.UP) {
				v--;
			} else if (tank.getDirection() == Direction.DOWN) {
				v++;
			} else if (tank.getDirection() == Direction.RIGHT) {
				h++;
			} else if (tank.getDirection() == Direction.LEFT) {
				h--;
			}

			Block block = battleField.getBlock(v, h);

			if (!(block instanceof Blank) && !block.hasDestroyed() && !(block instanceof Water)) {
				System.out.println("[illegal move] direction: " + tank.getDirection() + "tankX: " + tank.getX()
						+ ", tankY: " + tank.getY());
				return;
			}
			// move
			while (covered < 64) {
				if (tank.getDirection().equals(Direction.UP)) {
					tank.updateY(-step);
				} else if (tank.getDirection().equals(Direction.DOWN)) {
					tank.updateY(step);
				} else if (tank.getDirection().equals(Direction.LEFT)) {
					tank.updateX(-step);
				} else {
					tank.updateX(step);
				}
				covered += step;
				repaint();
				Thread.sleep(tank.getSpeed());
			}
		}
	}

	public void processFire(Tank tank) throws Exception {
		this.bullet = tank.fire();
		int step = 1;
		while ((bullet.getX() > -19 && bullet.getX() < 562) && (bullet.getY() > -19 && bullet.getY() < 562)) {
			if (tank.getDirection().equals(Direction.UP))
				bullet.updateY(-step);
			else if (tank.getDirection().equals(Direction.DOWN))
				bullet.updateY(step);
			else if (tank.getDirection().equals(Direction.LEFT))
				bullet.updateX(-step);
			else
				bullet.updateX(step);
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

	private boolean processInterception(Tank t) {
		int separator = battleField.getQuadrant(bullet.getX(), bullet.getY()).indexOf("_");
		int v = Integer.parseInt(battleField.getQuadrant(bullet.getX(), bullet.getY()).substring(0, separator));
		int u = Integer.parseInt(battleField.getQuadrant(bullet.getX(), bullet.getY()).substring(separator + 1));
		Block block;
		if (v >= 0 && v < 9 && u >= 0 && u < 9) {
			block = battleField.getBlock(v, u);
			if (!block.hasDestroyed() && !(block instanceof Blank) && !(block instanceof Water)) {
				if (!(t instanceof Tiger) && (block instanceof Rock)) {
					bullet.destroy();
				} else {
					battleField.destroyBlock(v, u);
				}
				return true;
			}
			if (!agressor.hasDestroyed() && checkInterception(bullet, agressor)) {
				agressor.destroy();
				return true;
			}

			if (!defender.hasDestroyed() && checkInterception(bullet, defender)) {
				defender.destroy();
				return true;
			}

		}
		return false;
	}

	private boolean checkInterception(Bullet bullet, Tank tank) {
		boolean flag = tank.getDirection() == Direction.UP || tank.getDirection() == Direction.DOWN
				|| tank.getDirection() == Direction.LEFT || tank.getDirection() == Direction.RIGHT;
		flag = flag && (bullet.getX() == tank.getX() - 25 || bullet.getX() == tank.getX() + 25);
		flag = flag && (bullet.getY() == tank.getY() - 25 || bullet.getY() == tank.getY() + 25);
		String object = battleField.getQuadrant(tank.getX(), tank.getY());
		String quadrant = battleField.getQuadrant(bullet.getX(), bullet.getY());
		int oy = Integer.parseInt(object.split("_")[0]);
		int ox = Integer.parseInt(object.split("_")[1]);

		int qy = Integer.parseInt(quadrant.split("_")[0]);
		int qx = Integer.parseInt(quadrant.split("_")[1]);

		if (oy >= 0 && oy < 9 && ox >= 0 && ox < 9) {
			if (oy == qy && ox == qx && flag) {
				return true;
			}
		}
		return false;
	}

	public ActionField() throws Exception {
		battleField = new BattleField();
		JFrame frame = new JFrame("BATTLE TANKS");
		frame.setLocation(750, 150);
		frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 8, battleField.getBF_HEIGHT() + 30));
		frame.setContentPane(this);
		defender = new T34(battleField);
		bullet = new Bullet(-100, -100, Direction.NONE);
		agressor = new Tiger(battleField,defender);
		agressor = new Tiger(battleField, 0, 0, Direction.DOWN);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	@Override
	 protected void paintComponent(Graphics g) { 
		 super.paintComponent(g);
	     battleField.draw(g); agressor.draw(g); defender.draw(g);
	     bullet.draw(g);
}
}