package b15;

public class Car {
	private String name;
	private String type;
	private Color color;
	private int doors;
	private int price;

	public Car() {
		setCar(null, null, Color.NONE, 0, 0);
	}

	public void setCar(String name, String type, Color color, int doors,
			int price) {
		this.name = name;
		this.type = type;
		this.color = color;
		this.doors = doors;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public int getDoors() {
		return doors;
	}

	public int getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

}
