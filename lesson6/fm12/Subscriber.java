package fm12;
import java.util.Observable;

public class Subscriber extends Observable {
	public Subscriber() {
	}
	
	public void Subscribe() {
		setChanged();
		notifyObservers(new Action());
		
	}
}
