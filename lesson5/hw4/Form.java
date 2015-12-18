package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Form  extends JPanel implements MouseMotionListener {
	private Circle circle;
	public Form()  {
    circle = new Circle();
	JFrame f = new JFrame("Game 2D");
	f.setMinimumSize(new Dimension(800, 600));
	f.setLocation(300, 100);
	f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.getContentPane().add(this);
	f.pack();
	f.setVisible(true);
	addMouseMotionListener(this);
	}
	
	@Override
	  public void mouseMoved(MouseEvent e) {
	      Random rand = new Random();
	      if(circle.hasCircle(e.getX(),e.getY())) {
	    	  circle.setX(rand.nextInt(800));
	    	  circle.setY(rand.nextInt(600));
	    	  repaint();
	      }
	      repaint();
	    }
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Random rand = new Random();
	      if(circle.hasCircle(e.getX(),e.getY())) {
	    	  circle.setX(rand.nextInt(800));
	    	  circle.setY(rand.nextInt(600));
	    	  repaint();
	      }
	      repaint();
	    }  
	  
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("BOLD",Font.PLAIN,32));
		g.drawString("Кликни по кругу!", 500, 50);
		g.setColor(new Color(255,0,0));
		circle.draw(g);
	}
	public static void main(String[] args)  {
		new Form();

	}

}