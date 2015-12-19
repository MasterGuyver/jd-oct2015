package fm9;

public class Launcher {

	public static void main(String[] args) {
		Subscriber[] subscribers = new Subscriber[5];
		Copy[] o = new Copy[3];
		for(int i=0; i <5; i++) {
			for(int j = 0; j< 3; j++) {
				if (i*j / 2 >2) {
					o[j] = new Journal();
				}
				else {
					o[j] = new Newspaper();
				}
			}
			subscribers[i] = new Subscriber();
			subscribers[i].addExemplars(o);
		}
		subscribers[0].notifySubscriber();
	}

}
