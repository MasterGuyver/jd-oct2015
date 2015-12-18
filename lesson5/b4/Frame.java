package b4;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Frame {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		//f.setSize(800, 600);
		f.setMinimumSize(new Dimension(800, 600));
		f.setLocation(300, 100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}

}
