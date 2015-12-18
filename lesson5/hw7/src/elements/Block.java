package elements;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import interfaces.*;

public abstract class Block implements Drawable, Destroyable {
	private int x;
	private int y;
	private boolean destroyed = false;
	private Image iBlock;
	protected boolean isWater=false;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void destroy() {
		 destroyed = true;
	}

	public boolean hasDestroyed() {
		return destroyed;
	}
	
	protected void setImage(String name) {
		try {
			iBlock = ImageIO.read(new File(name));
		} catch (IOException e) {
			System.err.println("Can't find image: "+name);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(!destroyed) {
			if(!isWater) {
			g.drawImage(iBlock, x, y, 64, 64, new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y,
						int width, int height) {
					return false;
				}
			});
				}
		
		else {
		  BufferedImage iWater = (BufferedImage) iBlock;
		  Graphics2D g2d = (Graphics2D) g;
		  float[] scales = { 1f, 1f, 1f, 0.6f };
		  float[] offsets = new float[4];
		  RescaleOp rop = new RescaleOp(scales, offsets, null);
		  g2d.drawImage(iWater,rop,x,y);
				}	
	}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
