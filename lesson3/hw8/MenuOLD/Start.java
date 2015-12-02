package hw5;

public class Start {

	public static void main(String[] args) {
		Commodity item1 = new Commodity();
		String[] comp = {"cofee","water","sugar"};
		int[] prices = new int[3];
		prices[0] = 1;
		prices[1] = 2;
		prices[2] = 3;
		
		int[] port = new int[3];
		
		port[0] = 1;
		port[1] = 1;
		port[2] = 1;
		
		item1.addProduct("Black cofee",comp,prices,port);
		System.out.println("Price product: "+item1.getPrice());
		item1.printInfo();
	}

}
