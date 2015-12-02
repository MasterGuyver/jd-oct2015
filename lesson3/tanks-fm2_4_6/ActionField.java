package hw567;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ActionField extends JPanel {
	private boolean COLORDED_MODE = false;
	private BattleField battleField;
	private AbstractTank defender;
	private Tiger agressor;
	private Bullet bullet;

	public void runTheGame() throws Exception {
		clean();
	}

	public void processTurn(AbstractTank tank) throws Exception {
		repaint();
	}

	public void clean() throws Exception {
		defender.turn(Direction.LEFT);
		int separator = getQuadrant(defender.getX(), defender.getY()).indexOf(
				"_");
		defender.moveToQuadrant(1, 1);
		int v = Integer.parseInt(getQuadrant(defender.getX(), defender.getY())
				.substring(0, separator));
		int u = Integer.parseInt(getQuadrant(defender.getX(), defender.getY())
				.substring(separator + 1));
		for (int i = u; i >= 0; i--) {
			if (battleField.scanQuadrant(v, i).equals("B"))
				battleField.updateQuadrant(v, i, "");
		}
		for (int i = v; i >= 0; i--) {
			if (battleField.scanQuadrant(i, 0).equals("B"))
				battleField.updateQuadrant(i, 0, "");
		}
		for (int i = 1; i < 9; i++) {
			int flag = 0;
			for (int j = 0; j < 9; j++) {
				if (battleField.scanQuadrant(j, i).equals("B"))
					flag++;
			}
			defender.moveToQuadrant(1, i + 1);
			defender.turn(Direction.DOWN);
			for (int k = 0; k < flag; k++) {
				defender.fire();
			}
		}
	}

	public void processMove(AbstractTank tank) throws Exception {
		int step = 1;
		int covered = 0;
		// check limits x: 0, 513; y: 0, 513
		if ((tank.getDirection().equals(Direction.UP) && tank.getY() == 0)
				|| (tank.getDirection().equals(Direction.DOWN) && tank.getY() >= 512)
				|| (tank.getDirection().equals(Direction.LEFT) && tank.getX() == 0)
				|| (tank.getDirection().equals(Direction.RIGHT) && tank.getX() >= 512)) {
			return;
		}
		tank.turn(tank.getDirection());
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

	public void processFire(Bullet bullet) throws Exception {
		this.bullet = bullet;
		int step = 1;
		while ((bullet.getX() > -19 && bullet.getX() < 562)
				&& (bullet.getY() > -19 && bullet.getY() < 562)) {
			if (defender.getDirection().equals(Direction.UP))
				bullet.updateY(-step);
			else if (defender.getDirection().equals(Direction.DOWN))
				bullet.updateY(step);
			else if (defender.getDirection().equals(Direction.LEFT))
				bullet.updateX(-step);
			else
				bullet.updateX(step);
			if (processInterception()) {
				bullet.destroy();
			}
			repaint();
			Thread.sleep(bullet.getSpeed());
		}
	}

	private boolean processInterception() {
		int separator = getQuadrant(bullet.getX(), bullet.getY()).indexOf("_");
		int v = Integer.parseInt(getQuadrant(bullet.getX(), bullet.getY())
				.substring(0, separator));
		int u = Integer.parseInt(getQuadrant(bullet.getX(), bullet.getY())
				.substring(separator + 1));
		if (fireTank(v,u,getQuadrant(agressor.getX(),agressor.getY()))) { agressor.destroy(); return true;}
		if (v >= 0 && v < 9 && u >= 0 && u < 9) {
			if (battleField.scanQuadrant(v, u).equals("")
					|| battleField.scanQuadrant(v, u).equals(" "))
				return false;
			else
				battleField.updateQuadrant(v, u, "");
		} else
			return false;
		if (fireTank(v,u,getQuadrant(defender.getX(),defender.getY()))) { defender.destroy();}
		return true;
	}

	public boolean fireTank(int y,int x,String coordinates) {
		int separator = coordinates.indexOf("_");
		int v = Integer.parseInt(coordinates.substring(0, separator));
		int u = Integer.parseInt(coordinates.substring(separator + 1));;
		if (v >= 0 && v < 9 && u >= 0 && u < 9) {
			if(v==y && u==x) return true;
		}
		return false;
	}
	
	public String getQuadrant(int x, int y) {
		return y / 64 + "_" + x / 64;
	}

	public String getQuadrantXY(int v, int h) {
		return (v - 1) * 64 + "_" + (h - 1) * 64;
	}

	public ActionField() throws Exception {
		battleField = new BattleField();
		defender = new T34(this, battleField);
		bullet = new Bullet(-100, -100, Direction.NONE);
		String location = battleField.getAgressorLocation();
		System.out.println(location);
		agressor = new Tiger(this, battleField, Integer.parseInt(location.split("_")[1]),
				Integer.parseInt(location.split("_")[0]),Direction.DOWN);
		JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
		frame.setLocation(750, 150);
		frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH(),
				battleField.getBF_HEIGHT() + 22));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i = 0;
		Color cc;
		for (int v = 0; v < 9; v++) {
			for (int h = 0; h < 9; h++) {
				if (COLORDED_MODE) {
					if (i % 2 == 0) {
						cc = new Color(252, 241, 177);
					} else {
						cc = new Color(233, 243, 255);
					}
				} else {
					cc = new Color(180, 180, 180);
				}
				i++;
				g.setColor(cc);
				g.fillRect(h * 64, v * 64, 64, 64);
			}
		}

		for (int j = 0; j < battleField.getDimensionY(); j++) {
			for (int k = 0; k < battleField.getDimensionX(); k++) {
				if (battleField.scanQuadrant(j, k).equals("B")) {
					String coordinates = getQuadrantXY(j + 1, k + 1);
					int separator = coordinates.indexOf("_");
					int y = Integer.parseInt(coordinates
							.substring(0, separator));
					int x = Integer.parseInt(coordinates
							.substring(separator + 1));
					g.setColor(new Color(0, 0, 255));
					g.fillRect(x, y, 64, 64);
				}
			}
		}

		defender.draw(g);
		agressor.draw(g);
		bullet.draw(g);
	}

	public void processMoveToQuadrant(int v, int h) throws Exception {
		String coordinates = getQuadrantXY(v, h);
		int separator = coordinates.indexOf("_");
		int y = Integer.parseInt(coordinates.substring(0, separator));
		int x = Integer.parseInt(coordinates.substring(separator + 1));
		if (defender.getX() < x) {
			defender.turn(Direction.RIGHT);
			while (defender.getX() != x) {
				defender.fire();
				bullet.destroy();
				defender.move();
			}
		} else {
			defender.turn(Direction.LEFT);
			while (defender.getX() != x) {
				defender.fire();
				bullet.destroy();
				defender.move();
			}
		}

		if (defender.getY() < y) {
			defender.turn(Direction.DOWN);
			while (defender.getY() != y) {
				defender.fire();
				bullet.destroy();
				defender.move();
			}
		} else {
			defender.turn(Direction.UP);
			while (defender.getY() != y) {
				defender.fire();
				bullet.destroy();
				defender.move();
			}
		}
	}

}
