package fields;

import java.util.Random;
import elements.*;

public class BattleField {
	private int BF_WIDTH = 576;
	private int BF_HEIGHT = 576;
	private int blength;
	private int rlength;
	private int wlength;
	private Brick[] bricks;
	private Rock[] rocks;
	private Water[] waters;
	private Eagle eagle;
	private String[][] battleField = {
			{ "", "", "B", "B", "B", "B", "B", "B", "B" },
			{ "", "B", "", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "W", "W", "W", "", "B", "B", "B", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "B", "R", "B", "B", "B", "B", "B", "R", "B" },
			{ "B", "B", "", "B", "B", "B", "", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", " ", "B", "E", "B", "B", "B", "" } };
	public BattleField() {}
	public BattleField(String[][] battleField) {
		this.battleField = battleField;
		setElementsNull();
	}
	public BattleField(int height, int width, String[][] battleField) {
		BF_WIDTH = width;
		BF_HEIGHT = height;
		this.battleField = battleField;
		setElementsNull();
	}

	private void setElementsNull() {
		bricks = null;
		rocks = null;
		waters = null;
		battleField = null;
		blength = 0;
		rlength = 0;
		wlength = 0;
	}
	
	public String scanQuadrant(int y, int x) {
		if (y >= 0 && y < battleField.length && x >= 0 && x < battleField.length)
			return battleField[y][x];
		return null;
	}

	public void updateQuadrant(int y, int x, String field) {
		if (y >= 0 && y < battleField.length && x >= 0 && x < battleField.length)
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
		switch(flag) {
		case 0: s = "64_128";
		break;
		case 1: s = "196_196";
		break;
		case 2: s = "0_0";
		break;
		}
		return s;
	}
	
     private void addElement(int u, int v, String type) {
    	 String coordinates = this.getQuadrantXY(u + 1, v + 1);
    	 int separator = coordinates.indexOf("_");
    	 int y = Integer.parseInt(coordinates.substring(0, separator));
    	 int x = Integer.parseInt(coordinates.substring(separator + 1));
    	 if(type.equals("B")) {
    		 bricks[blength++] = new Brick(this,x,y);
    	 }
    	 if(type.equals("R")) {
    		 rocks[rlength++] = new Rock(this,x,y);
    	 }
    	 if(type.equals("W")) {
    		 waters[wlength++] = new Water(this,x,y);
    	 }
    	 if(type.equals("E")) {
			 eagle = new Eagle(this,x,y);
		 }
    	 
     }
	
	 public void setElements() {
		 bricks = new Brick[81];
		 rocks = new Rock[81];
		 waters = new Water[81];
		 for (int j = 0; j < this.getDimensionY(); j++) {
				for (int k = 0; k < this.getDimensionX(); k++) {
					if (this.scanQuadrant(j, k).equals("B")) {
						addElement(j,k,"B"); }
					if (this.scanQuadrant(j, k).equals("W")) {
						addElement(j,k,"W"); }
					if (this.scanQuadrant(j, k).equals("R")) {
						addElement(j,k,"R"); }
					if (this.scanQuadrant(j, k).equals("E")) {
						addElement(j,k,"E"); }
				}
		 }
	 }
	
	public int getDimensionX() {
		return 9;
	}

	public int getDimensionY() {
		return 9;
	}
	public int getBlength() {
		return blength;
	}
	public int getRlength() {
		return rlength;
	}
	public int getWlength() {
		return wlength;
	}
	public int getBF_WIDTH() {
		return BF_WIDTH;
	}
	public int getBF_HEIGHT() {
		return BF_HEIGHT;
	}
	public Brick[] getBricks() {
		return bricks;
	}
	public Rock[] getRocks() {
		return rocks;
	}
	public Water[] getWaters() {
		return waters;
	}
	public Eagle getEagle() {
		return eagle;
	}
	public Brick getBrick(int v, int u) {
		  String str = getQuadrantXY(v+1, u+1);
		  int separator = str.indexOf("_");
		  int y = Integer.parseInt(str.substring(0, separator));
		  int x = Integer.parseInt(str.substring(separator + 1));
		  int k = -1;
		  for(int i=0; i < blength; i++) {
			     if(y == bricks[i].getY() && x == bricks[i].getX()) {
			    	 k = i;
			    	 break;
			     }  
		  }
		  if(k>=0) return bricks[k];
		  return null;
	}
	public Rock getRock(int v, int u) {
		  String str = getQuadrantXY(v, u);
		  int separator = str.indexOf("_");
		  int y = Integer.parseInt(str.substring(0, separator));
		  int x = Integer.parseInt(str.substring(separator + 1));
		  int k = -1;
		  for(int i =0; i<rlength; i++) {
			  if(rocks[i].getX() == x && rocks[i].getY() == y) {
				  k = i;
				  break;
				  
			  } 
		  }
		  if(k>=0) return rocks[k]; 
		  return null;
	}
}
