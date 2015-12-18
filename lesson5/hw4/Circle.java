package game;

import java.awt.Graphics;
import java.util.Random;

public class Circle {
	final static int WIDTH = 725;
	final static int HEIGHT = 525;
	final static int HORIZONTAL = 107;
	final static int VERTICAL = 75;
	private int x;
	private int y;

public Circle() {
	Random rand = new Random();
	x = rand.nextInt(WIDTH)+75;
	y = rand.nextInt(HEIGHT)+75;
}

public void setX(int x) {
	Random rand = new Random();
	if(x <= VERTICAL) {
		this.x = x + rand.nextInt(200)+100;
	}
	else if(x >= WIDTH) {
		this.x = x + rand.nextInt(200)-100;
	}
	else {
		this.x = x;
	}
}

public void setY(int y) {
	Random rand = new Random();
	if(y <= HORIZONTAL) {
		this.y = y + rand.nextInt(300)+100;
	}
	else if(y >= HEIGHT) {
		this.y = y - rand.nextInt(300)-100;
	}
	else {
		this.y = y;
	}
}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

boolean hasCircle(int mx, int my) {
	return x<mx+75 && x>mx-150 && y<my+150;
}

public void draw(Graphics g) {
	g.fillOval(x,y,150,150);
}

}
