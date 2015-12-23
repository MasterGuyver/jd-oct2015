package fields;

import java.awt.Graphics;
import java.util.Random;

import elements.*;
import interfaces.*;

public class BattleField implements Drawable {
	public static final int BF_WIDTH=576;
	public static final int BF_HEIGHT=576;
	private Block[] blocks;
	private String[][] battleField={
			{ "", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "", "B", "", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", "", "B", "W", "", "B", "B", "B" },
			{ "", "", "", "", "", "W", "B", "B", "B" },
			{ "", "B", "", "", "B", "", "B", "", "B" },
			{ "B", "R", "B", "B", "", "B", "B", "R", "B" },
			{ "B", "B", "", "B", "B", "B", "", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", " ", "B", "E", "B", "B", "B", "" }};

	public BattleField() {
		blocks = new Block[81];
		setElements();
	}

	public BattleField(String[][] battleField) {
		setBattleField(battleField);
	}

	public void setBattleField(String[][] battleField) {
		this.battleField = battleField;
		blocks = new Block[81];
		setElements();
	}

	public String scanQuadrant(int y, int x) {
		if (y >= 0 && y < battleField.length && x >= 0
				&& x < battleField.length)
			return battleField[y][x];
		return null;
	}

	public void updateQuadrant(int y, int x, String field) {
		if (y >= 0 && y < battleField.length && x >= 0
				&& x < battleField.length)
			battleField[y][x] = field;
	}

	public String getQuadrant(int x, int y) {
		return y / 64 + "_" + x / 64;
	}

	public String getQuadrantXY(int v, int h) {
		return (v - 1) * 64 + "_" + (h - 1) * 64;
	}
	
	public String getAgressorLocation() {
		Random t = new Random();
		int flag = t.nextInt(3);
		String s = null;
		switch (flag) {
		case 0:
			s = "64_128";
			break;
		case 1:
			s = "196_196";
			break;
		case 2:
			s = "0_0";
			break;
		}
		return s;
	}

	public void setElements() {
		int blength = 0;
		for (int j = 0; j < getDimensionY(); j++) {
			for (int k = 0; k < getDimensionX(); k++) {
				String coordinates = this.getQuadrantXY(j + 1, k + 1);
				int separator = coordinates.indexOf("_");
				int y = Integer.parseInt(coordinates.substring(0, separator));;
				int x = Integer.parseInt(coordinates.substring(separator + 1));
				if (this.scanQuadrant(j, k).equals("B")) {
					blocks[blength] = new Brick(x, y);
				}
				else if (this.scanQuadrant(j, k).equals("W")) {
					blocks[blength] = new Water(x, y);
				}
				else if (this.scanQuadrant(j, k).equals("R")) {
					blocks[blength] = new Rock(x, y);
				}
				else if (this.scanQuadrant(j, k).equals("E")) {
					blocks[blength] = new Eagle(x, y);
				}
				else {
					blocks[blength] = new Blank(x,y);
				}
				blength++;
			}
		}
	}
	
	public void destroyBlock(int v, int h) {
		blocks[9*v+h].destroy();
		String coordinates = this.getQuadrantXY(v + 1, h + 1);
		int separator = coordinates.indexOf("_");
		int y = Integer.parseInt(coordinates.substring(0, separator));;
		int x = Integer.parseInt(coordinates.substring(separator + 1));
		blocks[9*v+h] = new Blank(x, y);
	}
	
	public Block getBlock(int v, int h) {
		return blocks[9*v+h];
	}
	

	public int getDimensionX() {
		return 9;
	}

	public int getDimensionY() {
		return 9;
	}

	public int getSizeBlocks() {
		return blocks.length;
	}

	public int getBF_WIDTH() {
		return BF_WIDTH;
	}

	public int getBF_HEIGHT() {
		return BF_HEIGHT;
	}

	@Override
	public void draw(Graphics g) {
		for(int i=0; i<getSizeBlocks(); i++) {
			blocks[i].draw(g);
		}
		
	}
}
