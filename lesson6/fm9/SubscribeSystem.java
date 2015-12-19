package fm9;

import java.util.ArrayList;
import java.util.List;

public class SubscribeSystem implements Subscribe {
private List<Copy> exsemplars; 
	
	public SubscribeSystem() {
		exsemplars = new ArrayList<>();
	}
	
	@Override
	public void addSubscribe(Copy exs) {
		exsemplars.add(exs);
	}
	
	@Override
	public void removeSubscribe(Copy exs) {
		exsemplars.remove(exs);
	}
	
	@Override
	public void notifySubscribes() {
		for (Copy o : exsemplars) {
			if(o instanceof Journal) {
			o.update("this Journal is coming on Monday!");
			}
			else {
				o.update("this Newspaper is coming on Tuesday!");
			}
		}
	}
}
