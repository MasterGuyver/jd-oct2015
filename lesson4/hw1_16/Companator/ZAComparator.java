package b17;

import java.util.Comparator;

public class ZAComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		String s1 = (String) o2;
		String s2 = (String) o1;
		int result = s1.compareToIgnoreCase(s2);
		if(result < 0) return -1;
		else if(result > 0) return 1;
		return 0;
	}

}
