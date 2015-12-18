package b15;



public class Buyer {
	private String nameBuyer;
	private Car buyCar;
	private int numberCars;
	
	public Buyer() {
		setBuyer(null, null, 0);
	}
	
	public void setBuyer(String name, Car buyCar, int numberCars) {
		this.nameBuyer = name;
		this.buyCar = buyCar;
		this.numberCars = numberCars;
	}
	
	public void setBuyer(String name) {
		setBuyer(name,null,0);
	}
 
	public void setBuyCar(Car buyCar) {
		this.buyCar = buyCar;
	}

	public void setNumberCars(int numberCars) {
		this.numberCars = numberCars;
	}

	public String getName() {
		return nameBuyer;
	}

	public Car getBuyCar() {
		return buyCar;
	}

	public int getNumberCars() {
		return numberCars;
	}

}
