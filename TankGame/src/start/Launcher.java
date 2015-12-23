package start;

import fields.ActionField;

import java.util.Arrays;

public class Launcher {
	public static void main(String[] args) throws Exception {
		SceneSelect select = new SceneSelect();
		System.out.println(Arrays.toString(select.getField()));
		ActionField af = new ActionField(select.getField(),select.getTankName());
		SceneEnd en = new SceneEnd();
		//
	}
}
