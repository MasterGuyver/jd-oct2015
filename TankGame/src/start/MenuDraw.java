package start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuDraw {
	private boolean startPressed;
	public MenuDraw(JFrame frame) {
		this(frame,false);
	}
	public MenuDraw(JFrame frame,boolean press) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this game that has menu items");
		JMenuItem menuItemStart = new JMenuItem("Start", KeyEvent.VK_S);
		JMenuItem menuItemPause = new JMenuItem("Pause", KeyEvent.VK_P);
		this.startPressed = press;
		if(press) {
			menuItemStart.setVisible(false);
			menuItemPause.setVisible(true);
		}
		else {
			menuItemPause.setVisible(false);
			menuItemStart.setVisible(true);
		}
		menuItemPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					menuItemStart.setVisible(true);
					menuItemPause.setVisible(false);
			}
		});
		menuItemStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame.getName().toString().equals("frame0")) {
					frame.setVisible(false);
					//frame.dispose();
					startPressed = true;
				}
				menuItemStart.setVisible(false);
				menuItemPause.setVisible(true);
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
	}
	public boolean getStartPressed() {
		return this.startPressed;
	}
}
