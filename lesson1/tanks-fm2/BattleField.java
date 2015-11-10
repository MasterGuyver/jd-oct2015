package hw567;

import java.util.Random;

public class BattleField {
	private int BF_WIDTH = 576;
	private int BF_HEIGHT = 576;
	private String[][] battleField = {
			{ "", "", "B", "B", "B", "B", "B", "B", "B" },
			{ "", "B", "", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", "B", "", "B", "B", "B", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", "", "B", "B", "B", "", "B", "B" },
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ "B", "B", " ", "B", " ", "B", "B", "B", "" } };
	public BattleField() {}
	public BattleField(String[][] battleField) {
		this.battleField = battleField;
	}
	public BattleField(int height, int width, String[][] battleField) {
		BF_WIDTH = width;
		BF_HEIGHT = height;
		this.battleField = battleField;
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
	
	public int getDimensionX() {
		return 9;
	}

	public int getDimensionY() {
		return 9;
	}
	public int getBF_WIDTH() {
		return BF_WIDTH;
	}
	public int getBF_HEIGHT() {
		return BF_HEIGHT;
	}
}
