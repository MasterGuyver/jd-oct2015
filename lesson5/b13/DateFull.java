package b13;

import java.util.Date;
import java.text.SimpleDateFormat;


public class DateFull {

	public static void main(String[] args) throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		System.out.println(sdf.format(date));
		Date dat = sdf.parse("02 Sep 1984");
		System.out.println(dat);
		System.out.println(sdf.format(dat));
	}

}
