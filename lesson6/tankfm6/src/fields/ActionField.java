package fields;

import interfaces.*;
import elements.*;
import tanks.*;
import enums.*;

import java.awt.*;
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


public class ActionField extends JPanel {
	private BattleField battleField;
	private Tank defender;
	private Tank agressor;
	private Bullet bullet;
	private JFrame frame;
	private boolean paused = false;

	 public void runTheGame() throws Exception {
		 tanksAction();
		 Thread.sleep(1000);
		 frame.dispose();
	 }

	private void tanksAction() throws Exception {
		Block eagle = battleField.getBlock(8,4);
		while (!eagle.hasDestroyed() & !defender.hasDestroyed() && !agressor.hasDestroyed()) {
				agressor.stepTank();
				defender.stepTank();
				if (!paused) {
					processAction(agressor);
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

	    public ActionField(String[][] field, String nameTank, Point location ) throws Exception {
		battleField = new BattleField(field);
		frame = new JFrame("GAME BEGIN");
		frame.setLocation(location);
		frame.setMinimumSize(new Dimension(BattleField.BF_WIDTH + 8, BattleField.BF_HEIGHT + 55));
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Game");
			menu.setMnemonic(KeyEvent.VK_G);
			menu.getAccessibleContext().setAccessibleDescription("The only menu in this game that has menu items");
			JMenuItem menuItemStart = new JMenuItem("Start", KeyEvent.VK_S);
			JMenuItem menuItemPause = new JMenuItem("Pause", KeyEvent.VK_P);
			menuItemStart.setVisible(false);
			menuItemStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					paused = false;
					menuItemStart.setVisible(false);
					menuItemPause.setVisible(true);
				}
			});

			menuItemPause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					paused = true;
					menuItemStart.setVisible(true);
					menuItemPause.setVisible(false);
				}
			});
			menuItemStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
			menuItemStart.getAccessibleContext().setAccessibleDescription("This item used for start");
			menuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
			menuItemPause.getAccessibleContext().setAccessibleDescription("This item used for pause");
			JMenuItem menuItemEnd = new JMenuItem("End", KeyEvent.VK_E);
			menuItemEnd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
			menuItemEnd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			menuItemStart.getAccessibleContext().setAccessibleDescription("This item used for quit");
			menu.add(menuItemStart);
			menu.add(menuItemPause);
			menu.add(menuItemEnd);
			menuBar.add(menu);
			frame.setJMenuBar(menuBar);

		defender = new T34(battleField);
		bullet = new Bullet(-100, -100, Direction.NONE);
			if(nameTank.equals("Tiger")) {
				agressor = new Tiger(battleField);
			}
			else {
				agressor = new BT7(battleField);
			}
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	 protected void paintComponent(Graphics g) { 
		 super.paintComponent(g);
	     battleField.draw(g);  bullet.draw(g);
	      agressor.draw(g); defender.draw(g);

}
}