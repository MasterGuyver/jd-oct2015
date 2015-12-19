package fm12;
import java.util.Observer;
import java.util.Observable;
public abstract class Copy implements Observer {
	private String name;
	private int numer;
	public Copy(String name, int numer) {
		this.name = name;
		this.numer = numer;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Action) 
		{	if(this instanceof Journal) {
			System.out.println("New number of Journal "+((Journal) this).getName()+" is "+((Journal) this).getNumer()+1+".");
		}
		if(this instanceof Newspaper) {
			System.out.println("New number of Newspaper "+((Newspaper) this).getName()+" is "+((Newspaper) this).getNumer()+1+".");
		}
	}
		else {
		System.out.println("No news!");
		}
	}

	public String getName() {
		return name;
	}
	public int getNumer() {
		return numer;
	}
	
}
