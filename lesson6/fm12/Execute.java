package fm12;

import java.util.Observer;

public class Execute {

	public static void main(String[] args) {
		System.out.println("Subscribe");
		System.out.println();
		
		Subscriber s = new Subscriber();
		
		Observer o1 = new Journal("Good morning",2);
		Observer o2 = new Newspaper("Faking sheat",3);
		Observer o3 = new Newspaper("Faking!",5);

		s.addObserver(o1);
		s.addObserver(o2);
		s.addObserver(o3);
		
		s.Subscribe();
		System.out.println();
		
		s.deleteObserver(o2);

		s.Subscribe();
		System.out.println();
		}
	}

