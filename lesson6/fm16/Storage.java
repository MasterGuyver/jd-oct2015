package fm16;


public class Storage {
	static final int COLLECT = 100;
	static final int DAYS = 31;

	private int carCount;
	private int buyerCount;

	private Car[] cars;
	private int[] numbersAuto;
	private Buyer[] buyers;
	private Buy[] buys;

	public Storage() {
		carCount = 0;
		buyerCount = 0;
		cars = null;
		numbersAuto = null;
		buyers = null;
		buys = new Buy[DAYS];
		for (int i = 0; i < DAYS; i++) {
			buys[i] = new Buy();
		}
	}

	public void addCar(String name, String type, Color color, int doors,
			int price, int numCar) {
		Car car = new Car();
		car.setCar(name, type, color, doors, price);
		if (cars == null) {
			cars = new Car[COLLECT];
			numbersAuto = new int[COLLECT];
		} else {
			int len = cars.length;
			if (carCount == len) {
				len = 2 * len;
				Car[] temp = new Car[len];
				int[] tmp = new int[len];
				int i = 0;
				for (Car s : cars) {
					temp[i] = s;
					tmp[i] = numbersAuto[i];
					i++;
				}
				carCount = i;
				cars = temp;
				numbersAuto = tmp;
			}
		}
		cars[carCount] = car;
		numbersAuto[carCount++] = numCar;
	}

	public void addBuyer(String name, Car buyCar, int numberCars) {
		Buyer buyer = new Buyer();
		buyer.setBuyer(name, buyCar, numberCars);
		if (buyers == null) {
			buyers = new Buyer[COLLECT];
		} else {
			int len = buyers.length;
			if (buyerCount == len) {
				len = 2 * len;
				Buyer[] temp = new Buyer[len];
				int i = 0;
				for (Buyer s : buyers) {
					temp[i++] = s;
				}
				buyerCount = i;
				buyers = temp;
			}
		}
		buyers[buyerCount++] = buyer;
	}

	public void deleteCar(String name) {
		if (posCar(name) < 0) {
			System.out.println("Nothing car matched!");
			return;
		}
		if (posCar(name) == carCount - 1)
			--carCount;
		else {
			for (int i = posCar(name); i < --carCount; i++) {
				cars[i] = cars[i + 1];
			}
		}
		cars[carCount] = null;
	}

	public void deleteBuyer(String name) {
		if (posBuyer(name) < 0) {
			System.out.println("Nothing buyer matched!");
			return;
		}
		if (posBuyer(name) == buyerCount - 1) {
			buyerCount--;
		} else {
			for (int i = posBuyer(name); i < --buyerCount; i++) {
				buyers[i] = buyers[i + 1];
			}
		}
		buyers[buyerCount] = null;
	}

	public void deleteDayBuys(int day) {
		if ((day - 1) >= 0 && (day - 1) < DAYS) {
			if ((day - 1) == DAYS - 1)
				for (int i = day - 1; i < DAYS - 1; i++) {

				}
		}
	}

	public void addBuyer(String name) {
		addBuyer(name, null, 0);
	}

	private int posCar(String name) {
		for (int i = 0; i < carCount; i++) {
			if (name.equals(cars[i].getName()))
				return i;
		}
		return -1;
	}

	private int posBuyer(String name) {
		for (int i = 0; i < buyerCount; i++) {
			if (name.equals(buyers[i].getName()))
				return i;
		}
		return -1;
	}

	public void addTransaction(int day, String carName, String buyerName,
			int buyCars) {
		if ((day - 1) >= 0 && (day - 1) < 31) {
			int len = posCar(carName);
			if (len < 0) {
				System.out.println("Incorrect car name");
				return;
			} else {
				if (buyCars < numbersAuto[len]) {
					numbersAuto[len] += -buyCars;
				} else {
					System.out
							.println("You didn't buy cars. No cars for you in magazine!");
					return;
				}
			}
			int lb = posBuyer(buyerName);
			if (lb < 0) {
				addBuyer(buyerName,cars[len],buyCars);
				buys[day - 1].addBuy(buyers[buyerCount-1]);
			} else {
				buyers[lb].setBuyCar(cars[len]);
				buyers[lb].setNumberCars(buyCars);
				buys[day - 1].addBuy(buyers[lb]);
			}
		} else
			System.out.println("Incerrect day input");

	}

	public Car[] getCars() {
		return cars;
	}

	public int getCarCount() {
		return carCount;
	}

	public int[] getNumbersAuto() {
		return numbersAuto;
	}

	public Buyer[] getBuyers() {
		return buyers;
	}

	public int getBuyerCount() {
		return buyerCount;
	}

	public Buy[] getBuys() {
		return buys;
	}

}
