package b11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame  extends JPanel{
	private final static String IMAGE_NAME = "red-top.png";
	private Image iTank;
	
	public Frame()  {
	try {
		iTank = ImageIO.read(new File(IMAGE_NAME));
	} catch (IOException e) {
		System.err.println("Can't find image: "+IMAGE_NAME);
	}

	JFrame f = new JFrame("My graphics 2D");
	//f.setSize(800, 600);
	f.setMinimumSize(new Dimension(800, 600));
	f.setLocation(300, 100);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.getContentPane().add(this);
	f.pack();
	f.setVisible(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(203,30,0));
		g.fillRect(0, 0, 800, 600);
		
		g.setColor(new Color(86, 142, 204));
		g.fillRect(400, 100, 50, 50);
		
		g.setColor(new Color(34, 174, 93));
		g.draw3DRect(400, 200, 49, 49, true);
		
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("BOLD",Font.PLAIN,32));
		g.drawString("I  love programming with midgardabc!", 20, 50);
		
		g.drawImage(iTank, 100, 500, new ImageObserver() {
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y,
					int width, int height) {
				return false;
			}
		});
	}
	public static void main(String[] args)  {
		new Frame();

	}

}
