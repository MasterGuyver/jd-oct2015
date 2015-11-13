package hw4;


   public class Buy {
	static final int SIZE = 50;
	private int numberCount;
	private Buyer[] buyers;
	
	public Buy() {
		buyers = new Buyer[SIZE];
		numberCount = 0;
			}
	public void addBuy(Buyer buyer) {
		if(numberCount == SIZE)
		{ Buyer[] temp = new Buyer[2*SIZE];
		  int i = 0;
		  for(Buyer s: buyers) {
			  temp[i++] = s; 
		  }
		  buyers = temp;
		}
		buyers[numberCount++] = buyer;
		
	}
	
	public void deleteBuy(int count) {
		if(numberCount == 0 || count < 0 || count > numberCount) {
			System.out.println("Cannot delete buy");
			return;
		}
		else {
			if(count == numberCount-1) --numberCount;
			else {
				for(int i=count; i< --numberCount; i++)
					buyers[i] = buyers[i+1];
			}
		}
		buyers[numberCount] = null;
	}
	
	
	
	public int getNumberCount() {
		return numberCount;
	}
	
    public void printInfoBuy() {
		for(int i = 0; i< numberCount; i++) {
			System.out.print(buyers[i].getName()+" ");
				System.out.print(buyers[i].getBuyCar().getName()+" ");
				System.out.print(buyers[i].getBuyCar().getPrice()+" ");
				System.out.println(buyers[i].getNumberCars()+" ");   
		}
			
	}
}
