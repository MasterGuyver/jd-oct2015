package hw5;
public abstract class AbstractComponents {
	private int count = 0;
	final static private int SIZE = 5;
	protected String[] names;
	protected int[] portions;
	protected int[] prices;

	public AbstractComponents() {
		this.names = new String[SIZE];
		this.prices = new int[SIZE];
		this.portions = new int[SIZE];
	}

	public int posFindComponent(String name) {
		int i = 0;
		for (String s : names) {
			if (name.equals(s))
				return i;
		}
		return -1;
	}

	protected void addComponent(String name, int price, int portion) {
		if (posFindComponent(name) >= 0)
			System.out.println("Error, dublicated ingridient!");
		else {
			int len = names.length;
			if (count < len) {
				names[count] = name;
				prices[count] = price;
				portions[count++] = portion;
			} else {
				String[] tmpNames = new String[2 * len];
				int[] tmpPrices = new int[2 * len];
				int[] tmpPortions = new int[2 * len];
				for (int i = 0; i < len; i++) {
					tmpNames[i] = names[i];
					tmpPrices[i] = prices[i];
					tmpPortions[i] = portions[i];
				}
				names = tmpNames;
				prices = tmpPrices;
				names[count] = name;
				prices[count] = price;
				portions[count++] = portion;
			}
		}
	}
	
	private int getElement(String name, int[] mass) {
		int position = posFindComponent(name);
		if (position >= 0)
			return mass[position];
		return 0;
	}

	public int getPriceComponent(String name) {
		return getElement(name,prices);
	}

	public int getPortionComponent(String name) {
		return getElement(name,portions);
	}
	
	public int getCount() {
		return count;
	}
}
