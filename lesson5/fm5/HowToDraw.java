package fm5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class HowToDraw extends JPanel {
	
	static int x = 50;
	
	public HowToDraw() {
		
		JFrame frame = new JFrame("Frame 2D Graphics");
		frame.setLocation(750, 150);
		frame.setMinimumSize(new Dimension(600, 400));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		
		repaint();
		
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(x, 50, 10, 10);
		
		g.setColor(Color.BLUE);
		g.fillRect(100, 100, 10, 10);
	}
	public static void main(String[] args) throws Exception {
		HowToDraw htd = new HowToDraw();
		Thread.sleep(5000);
		System.out.println("wake up");
		x = 150;
		htd.repaint();
	}

}
