package fm16;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SplashScreen;
import java.text.NumberFormat;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CarsMagazineUI {
	Storage magazine;
	private int carIndex = 0;
	private Calendar calendar = new GregorianCalendar();
	int day = calendar.get(Calendar.DAY_OF_MONTH);

	public CarsMagazineUI(Storage mag) throws Exception {
		magazine = mag;
		magazine.addTransaction(day, magazine.getCars()[2].getName(),
				magazine.getBuyers()[2].getName(), 2);
		magazine.addTransaction(day, magazine.getCars()[1].getName(),
				magazine.getBuyers()[3].getName(), 1);
		magazine.addTransaction(day, magazine.getCars()[3].getName(),
				magazine.getBuyers()[4].getName(), 2);
		JFrame f = new JFrame("Magazine of Cars");
		f.setMinimumSize(new Dimension(800, 600));
		f.setLocation(300, 100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("Verdana", Font.PLAIN, 12);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.ORANGE);
		menuBar.setForeground(Color.MAGENTA);
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(font);
		menuFile.setBackground(Color.ORANGE);
		menuFile.setForeground(Color.MAGENTA);
		JMenuItem buyItem = new JMenuItem("Buy Car");
		buyItem.setFont(font);
		buyItem.setBackground(Color.ORANGE);
		buyItem.setForeground(Color.MAGENTA);
		buyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintGUI(f);
			}
		});
		menuFile.add(buyItem);
		menuBar.add(menuFile);
		f.setJMenuBar(menuBar);
		SplashScreen splashScr = SplashScreen.getSplashScreen();
		if (splashScr != null) {
			Graphics2D g = splashScr.createGraphics();
			g.setPaintMode();
			g.setFont(new Font("Times New Roman", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("Auto magazine", 260, 420);
				splashScr.update();
		}
		Thread.sleep(5000);
		splashScr.close();
		printTable(f);
		f.pack();
		f.setVisible(true);
	}

	private void printTable(JFrame f) {
		Object[][] data = new Object[magazine.getBuys()[day - 1]
				.getNumberCount()][];
		f.getContentPane().removeAll();
		for (int i = 0; i < magazine.getBuys()[day - 1].getNumberCount(); i++) {
			data[i] = new Object[5];
			data[i][0] = new Integer(i + 1);
			data[i][1] = magazine.getBuys()[day - 1].getBuyers()[i].getDate();
			data[i][2] = magazine.getBuys()[day - 1].getBuyers()[i].getBuyCar()
					.getName();
			data[i][3] = magazine.getBuys()[day - 1].getBuyers()[i]
					.getNumberCars();
			data[i][4] = magazine.getBuys()[day - 1].getBuyers()[i].getName();
		}

		String[] columns = { "ID transaction", "Date", "Car", "Count", "Buyer" };
		JTable table = new JTable(data, columns);
		table.setBackground(Color.BLUE);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setFont(new Font("Verdana", Font.BOLD, 12));
		table.setForeground(Color.YELLOW);
		table.setLayout(new BorderLayout());
		f.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
		f.getContentPane().add(table, BorderLayout.CENTER);
		f.pack();
		f.repaint();
	}

	private void paintGUI(JFrame f) {
		f.getContentPane().removeAll();
		GridBagLayout expLayout = new GridBagLayout();
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setLayout(expLayout);
		JLabel blabel = new JLabel("Input buyer name please: ");
		blabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		blabel.setForeground(Color.RED);
		JLabel bcountLabel = new JLabel("Number of Cars to bye: ");
		bcountLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		bcountLabel.setForeground(Color.RED);
		JTextField bname = new JTextField();
		bname.setColumns(25);
		bname.setBackground(Color.GREEN);
		bname.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JFormattedTextField bcount = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		bcount.setColumns(2);
		bcount.setBackground(Color.GREEN);
		bcount.setFont(new Font("Times New Roman", Font.BOLD, 20));
		bcount.setValue(1);
		JLabel bdayLabel = new JLabel("Day of transaction: ");
		bdayLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		bdayLabel.setForeground(Color.RED);
		JFormattedTextField bday = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		bday.setColumns(2);
		bday.setBackground(Color.GREEN);
		bday.setFont(new Font("Times New Roman", Font.BOLD, 20));
		bday.setValue(day);
		JLabel lcarsGroup = new JLabel("Cars");
		lcarsGroup.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lcarsGroup.setForeground(Color.RED);
		ButtonGroup carsGroup = new ButtonGroup();
		JPanel carsPanel = new JPanel();
		carsPanel.setLayout(new GridLayout(magazine.getCarCount(), 0));
		carsPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		ActionListener carListener = new CarListener();
		for (int i = 0; i < magazine.getCarCount(); i++) {
			JRadioButton selectCar = new JRadioButton(
					magazine.getCars()[i].getName() + " "
							+ magazine.getCars()[i].getPrice() + "$");
			selectCar.setFont(new Font("Times New Roman", Font.BOLD, 18));
			selectCar.setBackground(Color.BLUE);
			selectCar.setForeground(Color.YELLOW);
			selectCar.setActionCommand(String.valueOf(i));
			selectCar.addActionListener(carListener);
			if (i == 0) {
				selectCar.setSelected(true);
			}
			carsGroup.add(selectCar);
			carsPanel.add(selectCar);
		}
		panel.add(blabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		panel.add(bname, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(lcarsGroup, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(carsPanel, new GridBagConstraints(1, 1, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 3, 0, 0), 0, 0));
		panel.add(bcountLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(bcount, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(bdayLabel, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(bday, new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
		JButton btbuy = new JButton("Buy");
		btbuy.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btbuy, new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
		btbuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				magazine.addTransaction(Integer.parseInt(bday.getText()),
						magazine.getCars()[carIndex].getName(),
						bname.getText(), Integer.parseInt(bcount.getText()));
				printTable(f);
			}
		});
		f.getContentPane().add(panel);
		f.pack();
		f.repaint();
	}

	private class CarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			carIndex = Integer.parseInt(e.getActionCommand());
		}
	}
}
