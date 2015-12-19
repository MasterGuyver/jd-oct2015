package fm9;

public class Subscriber {
	private SubscribeSystem examples;
	
	public Subscriber() {
		examples = new SubscribeSystem();
	}
		
	public void addExemplars(Copy[] massExs) {
		for(Copy o:massExs) {
			examples.addSubscribe(o);
		}
	}
	
	public void removeExemplar(Copy xs) {
			examples.removeSubscribe(xs);
	}
	
	public void notifySubscriber() {
			examples.notifySubscribes();
	}
	}
	
