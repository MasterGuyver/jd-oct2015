package b14;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DayBirthday {

	public static void main(String[] args) {
		Calendar c = new GregorianCalendar();
		c.set(1984,9, 2);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
	}
}
