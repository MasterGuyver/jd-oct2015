package b17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Launch {

	public static void main(String[] args) {
		List<String> sll = new ArrayList<>();
		System.out.println("Sorting A-Z");
		sll.add("Zsds");
		sll.add("RTJJ");
		sll.add("Abdd");
		sll.add("Oiu");
		sll.add("Utop");
		sll.add("Wii");
		sll.add("Xcio");
		
		Collections.sort(sll);
		
		for(String s: sll) {
			System.out.println(s);
		}
		
		System.out.println();
		System.out.println("Sorting Z-A");
		
		Collections.sort(sll,new ZAComparator());
		
		for(String s: sll) {
			System.out.println(s);
		}
		
		System.out.println();
		System.out.println("Sorting Z-A (Anonymus)");
		
		Collections.sort(sll,new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				String s1 = (String) o2;
				String s2 = (String) o1;
				int result = s1.compareToIgnoreCase(s2);
				if(result < 0) return -1;
				else if(result > 0) return 1;
				return 0;
			}
		}	);
	for(String s: sll) {
		System.out.println(s);
	}
}
}
