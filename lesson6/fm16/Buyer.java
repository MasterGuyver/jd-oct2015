package fm16;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Buyer {
	private String nameBuyer;
	private Car buyCar;
	private int numberCars;
	private Date date;
	
	public Buyer() {
		setBuyer(null, null, 0);
	}
	
	public void setBuyer(String name, Car buyCar, int numberCars) {
		this.nameBuyer = name;
		this.buyCar = buyCar;
		this.numberCars = numberCars;
		date = new Date();
		date.setTime(date.getTime());
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
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
		return sdf.format(date);
	}
}
