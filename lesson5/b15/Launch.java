package b15;


public class Launch {

	public static void main(String[] args) {
	Storage mag = new Storage();
	setCars(mag);
	setBuyers(mag);
	CarsMagazineUI ui = new CarsMagazineUI(mag);
  
	System.out.println("Price of cars");
	System.out.println();
	for(int i = 0; i<mag.getCarCount();i++) {
		System.out.println(mag.getCars()[i].getName()+" "+mag.getCars()[i].getPrice()+"$");
	}
	System.out.println();
	System.out.println("Lot of cars");
	System.out.println();
	int len = mag.getCarCount();
	for(int i = 0; i<len-1;i++) {
		System.out.print(mag.getNumbersAuto()[i]+" "+mag.getCars()[i].getName()+",");
	}
	System.out.println(mag.getNumbersAuto()[len-1]+" "+mag.getCars()[len-1].getName());
	System.out.println();
	
}
	static void setCars(Storage magazine) {
		magazine.addCar("ALFAROMEO", "B",Color.BLACK, 4, 1000,6);
		magazine.addCar("AUDI", "A", Color.WHITE, 2, 3000, 4);
		magazine.addCar("BMW","C",Color.BLACK,4,4000,8);
		magazine.addCar("CHEVROLET","E",Color.BLACK,4,6000,4);
		magazine.addCar("CITROEN","D",Color.RED,4,2000,7);
		magazine.addCar("DODGE","A",Color.BLUE,4,1000,3);
		magazine.addCar("DAEWOO", "E", Color.GREY, 4, 6000, 9);
	}
	static void setBuyers(Storage mag) {
		    mag.addBuyer("Abramson G.");
		    mag.addBuyer("Archibald T.");
		    mag.addBuyer("Kennett A.");
		    mag.addBuyer("Johnson L.");
		    mag.addBuyer("Campbell A.");
		    mag.addBuyer("Bosworth E.");
			mag.addBuyer("Carroll G.");
			mag.addBuyer("Macduff M.");
	}
}