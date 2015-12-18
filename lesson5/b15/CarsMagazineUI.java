package b15;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CarsMagazineUI {
	private Storage magazine;
	private int carIndex = 0;
	
	public CarsMagazineUI(Storage mag) {
		magazine = mag;
		JFrame f = new JFrame("Magazine of Cars");
		f.setMinimumSize(new Dimension(800, 600));
		f.setLocation(300, 100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel transPanel = new JPanel();
		GridBagLayout expLayout = new GridBagLayout();
		transPanel.setLayout(expLayout);
		JLabel blabel = new JLabel("Input buyer name please: ");
		JLabel bcountLabel = new JLabel("Number of Cars to bye: ");
		JTextField bname = new JTextField();
		bname.setColumns(25);
		JFormattedTextField bcount = new JFormattedTextField(NumberFormat.getNumberInstance());
		bcount.setColumns(2);
		bcount.setValue(1);
		JLabel bdayLabel = new JLabel("Day of transaction: ");
		JFormattedTextField bday = new JFormattedTextField(NumberFormat.getNumberInstance());
		bday.setColumns(1);
		bday.setValue(1);
		JLabel lcarsGroup = new JLabel("Cars");
		ButtonGroup carsGroup = new ButtonGroup();
		JPanel carsPanel = new JPanel();
		carsPanel.setLayout(new GridLayout(magazine.getCarCount(),0));
		carsPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		ActionListener carListener = new CarListener();
		for(int i = 0; i<magazine.getCarCount(); i++) {
		JRadioButton selectCar = new JRadioButton(magazine.getCars()[i].getName()
				+" "+magazine.getCars()[i].getPrice()+"$");
		selectCar.setActionCommand(String.valueOf(i));
		selectCar.addActionListener(carListener);
		if(i == 0) {
			selectCar.setSelected(true);
		}
		carsGroup.add(selectCar);
		carsPanel.add(selectCar);
		}
		transPanel.add(blabel, new GridBagConstraints(0,0,1,1,0,0, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
		transPanel.add(bname,new GridBagConstraints(1,0,1,1,0,0, GridBagConstraints.LINE_START,
				0, new Insets(0,0,0,0),0,0));
		transPanel.add(lcarsGroup,new GridBagConstraints(0,1,1,1,0,0, GridBagConstraints.LINE_START,
				0, new Insets(0,0,0,0),0,0));
		transPanel.add(carsPanel,new GridBagConstraints(1,1,1,1,0,0, GridBagConstraints.LINE_START,
				GridBagConstraints.NONE, new Insets(0,3,0,0),0,0));
		transPanel.add(bcountLabel,new GridBagConstraints(0,2,1,1,0,0, GridBagConstraints.LINE_START,
				0, new Insets(0,0,0,0),0,0));
		transPanel.add(bcount,new GridBagConstraints(1,2,1,1,0,0, GridBagConstraints.LINE_START,
				0, new Insets(0,0,0,0),0,0));
		transPanel.add(bdayLabel,new GridBagConstraints(0,3,1,1,0,0, GridBagConstraints.LINE_START,
				0, new Insets(0,0,0,0),0,0));
		transPanel.add(bday,new GridBagConstraints(1,3,1,1,0,0, GridBagConstraints.LINE_START,
				0, new Insets(0,0,0,0),0,0));
		JButton btbuy = new JButton("Buy");
		transPanel.add(btbuy,new GridBagConstraints(1,4,1,1,0,0, GridBagConstraints.LINE_START,
				0, new Insets(0,0,0,0),0,0));
		btbuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				magazine.addTransaction(Integer.parseInt(bday.getText()),magazine.getCars()[carIndex].getName(),
						bname.getText(),Integer.parseInt(bcount.getText()));
				magazine.getBuys()[Integer.parseInt(bday.getText())-1].printInfoBuy();
			}
		});
		f.getContentPane().add(transPanel);
		f.pack();
		f.setVisible(true);
		
		}
	private class CarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			carIndex = Integer.parseInt(e.getActionCommand());
		}
	}
}
