package fields;

import interfaces.*;
import elements.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import tanks.*;
import enums.*;

public class ActionField extends JPanel {
	private boolean COLORDED_MODE = false;
	private BattleField battleField;
	private Tank defender;
	private Tank agressor;
	private Bullet bullet;

	public void runTheGame() throws Exception {
		agressorAction();
	}

	public void agressorAction() throws Exception {
		int ueagle = -1;
		int veagle = -1;
		String location = battleField.getQuadrant(this.getX(),
				this.getY());
		int dfu = Integer.parseInt(location.split("_")[1]);
		int dfv = Integer.parseInt(location.split("_")[0]);
		Block eagle;
		for (int i = 0; i < battleField.getDimensionX(); i++) {
			for (int j = 0; j < battleField.getDimensionY(); j++) {
				eagle = battleField.getBlock(j, i);
				if (eagle instanceof Eagle) {
					ueagle = i;
					veagle = j;
					break;
				}
			}
		}
		if (ueagle == -1 && veagle == -1) {
			agressor.turn(Direction.DOWN);
			processTurn(agressor);
			processFire(agressor);
		} else {
			if (dfu <= ueagle) {
				agressor.turn(Direction.RIGHT);
				processTurn(agressor);
				for (int i = dfu; i <= ueagle; i++) {
					if ((battleField.getBlock(dfv, i) instanceof Brick)
							|| (battleField.getBlock(dfv, i) instanceof Rock)
							|| (battleField.getBlock(dfv, i) instanceof Eagle)) {
						processFire(agressor);
					}
					processMove(agressor);
				}
			}
			if (dfv <= veagle) {
				agressor.turn(Direction.DOWN);
				processTurn(agressor);
				for (int i = dfv; i <= veagle; i++) {
					if ((battleField.getBlock(i, ueagle) instanceof Brick)
							|| (battleField.getBlock(i, ueagle) instanceof Rock)
							|| (battleField.getBlock(i, ueagle) instanceof Eagle)) {
						processFire(agressor);
					}
					processMove(agressor);
					
				}
			}
			if (dfu > ueagle) {
				agressor.turn(Direction.LEFT);
				processTurn(agressor);
				for (int i = dfu; i >= ueagle; i--) {
					if ((battleField.getBlock(dfv, i) instanceof Brick)
							|| (battleField.getBlock(dfv, i) instanceof Rock)
							|| (battleField.getBlock(dfv, i) instanceof Eagle)) {
						processFire(agressor);
					}
					processMove(agressor);
				
				}
			}
			if (dfv > veagle) {
				agressor.turn(Direction.UP);
				processTurn(agressor);
				for (int i = dfv; i >= veagle; i--) {
					if ((battleField.getBlock(i, ueagle) instanceof Brick)
							|| (battleField.getBlock(i, ueagle) instanceof Rock)
							|| (battleField.getBlock(i, ueagle) instanceof Eagle)) {
						processFire(agressor);
					}
					processMove(agressor);
				}
			}
		}
	}

	public void defenderAction() throws Exception {
		
		processFire(defender);
		processFire(defender);
		processFire(defender);
		processFire(defender);
		processFire(defender);
		processFire(defender);
		processMove(defender);
		processMove(defender);
		processMove(defender);
		processMove(defender);
	}
	
	public void processTurn(Tank tank) throws Exception {
		repaint();
	}

	public void processMove(Tank tank) throws Exception {
		tank.setUp(Action.MOVE);
		int step = 1;

		for (int i = 0; i < tank.getMovePath(); i++) {
			int covered = 0;

			String tankQuadrant = battleField.getQuadrant(tank.getX(),
					tank.getY());
			int v = Integer.parseInt(tankQuadrant.split("_")[0]);
			int h = Integer.parseInt(tankQuadrant.split("_")[1]);

			// check limits x: 0, 513; y: 0, 513
			if ((tank.getDirection().equals(Direction.UP) && tank.getY() == 0)
					|| (tank.getDirection().equals(Direction.DOWN) && tank
							.getY() >= 512)
					|| (tank.getDirection().equals(Direction.LEFT) && tank
							.getX() == 0)
					|| (tank.getDirection().equals(Direction.RIGHT) && tank
							.getX() >= 512)) {
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

			if (!(block instanceof Blank) && !block.hasDestroyed()) {
				System.out.println("[illegal move] direction: "
						+ tank.getDirection() + "tankX: " + tank.getX()
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
		tank.setUp(Action.FIRE);
		this.bullet = tank.fire();
		int step = 1;
		while ((bullet.getX() > -19 && bullet.getX() < 562)
				&& (bullet.getY() > -19 && bullet.getY() < 562)) {
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
		int separator = battleField.getQuadrant(bullet.getX(), bullet.getY())
				.indexOf("_");
		int v = Integer.parseInt(battleField.getQuadrant(bullet.getX(),
				bullet.getY()).substring(0, separator));
		int u = Integer.parseInt(battleField.getQuadrant(bullet.getX(),
				bullet.getY()).substring(separator + 1));
		Block block;
		if (v >= 0 && v < 9 && u >= 0 && u < 9) {
			block = battleField.getBlock(v, u);
			if (!block.hasDestroyed() && !(block instanceof Blank)
					&& !(block instanceof Water)) {
				if (!(t instanceof Tiger) && (block instanceof Rock)) {
					bullet.destroy();
				} else {
					battleField.destroyBlock(v, u);
				}
				return true;
			}
			if (!defender.hasDestroyed() && checkInterception(bullet, defender)) {
				defender.destroy();
				return true;
			}

			if (!agressor.hasDestroyed() && checkInterception(bullet, agressor)) {
				agressor.destroy();
				return true;
			}
		}
		return false;
	}

	private boolean checkInterception(Bullet bullet, Tank tank) {
		String object = battleField.getQuadrant(tank.getX(), tank.getY());
		String quadrant = battleField.getQuadrant(bullet.getX(), bullet.getY());
		int oy = Integer.parseInt(object.split("_")[0]);
		int ox = Integer.parseInt(object.split("_")[1]);

		int qy = Integer.parseInt(quadrant.split("_")[0]);
		int qx = Integer.parseInt(quadrant.split("_")[1]);

		if (oy >= 0 && oy < 9 && ox >= 0 && ox < 9) {
			if (oy == qy && ox == qx && tank.getAction() != Action.FIRE) {
				return true;
			}
		}
		return false;
	}

	public ActionField() throws Exception {
		battleField = new BattleField();
		defender = new T34(battleField);
		bullet = new Bullet(-100, -100, Direction.NONE);
		String location = battleField.getAgressorLocation();
		agressor = new Tiger(battleField,
				Integer.parseInt(location.split("_")[1]),
				Integer.parseInt(location.split("_")[0]), Direction.RIGHT);
		JFrame frame = new JFrame("BATTLE FIELD, DAY");
		frame.setLocation(750, 150);
		frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 8,
				battleField.getBF_HEIGHT() + 30));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		battleField.draw(g);
		agressor.draw(g);
		defender.draw(g);
		bullet.draw(g);
	}

}
