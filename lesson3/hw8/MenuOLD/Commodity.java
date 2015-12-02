package hw5;
public class Commodity extends AbstractComponents {
	private String name;
	private int price;

	public Commodity() {
		super();
		this.name = null;
		this.price = 0;
	}

	public void addProduct(String name, String[] componentsItem,
			int[] pricesItem, int[] portionsItem) {
		this.name = name;
		if (componentsItem.length == pricesItem.length
				&& componentsItem.length == portionsItem.length) {
			for (int i = 0; i < pricesItem.length; i++) {
				addComponent(componentsItem[i], pricesItem[i],
						portionsItem[i]);
			}
		} else
			System.out.println("Cannot add Item");
	}

	public int getPrice() {
		for(int i = 0; i<super.getCount(); i++ ) {
			price += prices[i]*portions[i];
		}
		return price;
	}
	
	public String getName() {
		return name;
	}

	public void printInfo() {
		System.out.println(name);
		System.out.println("Components");
		for(int i = 0; i< super.getCount(); i++) {
			System.out.println("Component: "+ names[i]+" Portions: "+portions[i]+" Price: "+prices[i]+"$");
		}
	}
}