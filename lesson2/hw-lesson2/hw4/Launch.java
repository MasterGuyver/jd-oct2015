package hw4;


public class Launch {

	public static void main(String[] args) {
	Storage mag = new Storage();
	mag.addCar("ALFAROMEO", "B",Color.BLACK, 4, 1000,6);
	mag.addCar("AUDI", "A", Color.WHITE, 2, 3000, 4);
	mag.addCar("BMW","C",Color.BLACK,4,4000,8);
	mag.addCar("CHEVROLET","E",Color.BLACK,4,6000,4);
	mag.addCar("CITROEN","D",Color.RED,4,2000,7);
	mag.addCar("DODGE","A",Color.BLUE,4,1000,3);
    mag.addCar("DAEWOO", "E", Color.GREY, 4, 6000, 9);
    
    mag.addBuyer("Abramson G.");
    mag.addBuyer("Archibald T.");
    mag.addBuyer("Kennett A.");
    mag.addBuyer("Johnson L.");
    mag.addBuyer("Campbell A.");
    mag.addBuyer("Bosworth E.");
	mag.addBuyer("Carroll G.");
	mag.addBuyer("Macduff M.");

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
	mag.addTransaction(1,"CITROEN","Archibald T.",3);
	mag.addTransaction(2,"BMW","Macduff M.",6);
	mag.addTransaction(3,"DODGE","Campbell A.",2);
	mag.addTransaction(4,"CHEVROLET","Carroll G.",2);
	mag.addTransaction(5,"AUDI","Bosworth E.",2);
	mag.addTransaction(6,"DAEWOO","Tor M.",3);
	mag.addTransaction(7,"DAEWOO","Carroll G.",2);
	mag.addTransaction(7,"BMW","Johnson L.",1);
	System.out.println("Number of Buys 7 days");
	int days = 7;
	for(int i = 1; i < days; i++) {
		System.out.print(mag.getBuys()[i-1].getNumberCount()+",");
	}
	    System.out.println(mag.getBuys()[days-1].getNumberCount());
	System.out.println();
	System.out.println("Number of current day");
	System.out.println();
	mag.getBuys()[days-1].printInfoBuy();
	mag.deleteCar("BMW");
	mag.deleteBuyer("Tor M.");
	System.out.println();
	System.out.println("After deleting car BMW");
	len = mag.getCarCount();
	for(int i = 0; i<len-1;i++) {
		System.out.print(mag.getNumbersAuto()[i]+" "+mag.getCars()[i].getName()+",");
	}
	System.out.println(mag.getNumbersAuto()[len-1]+" "+mag.getCars()[len-1].getName());
	System.out.println();
	System.out.println("After deleting buyer Tor M.");
	System.out.println();
	len = mag.getBuyerCount();
	for(int i = 0; i<len-1;i++) {
		System.out.print(mag.getBuyers()[i].getName()+", ");
	}
	System.out.println(mag.getBuyers()[len-1].getName());
}
}