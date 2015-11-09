
public class GreetingService {
	public static void main(String[] args) {
		int len = args.length;
		if (len < 1) {
			System.out.println("You must enter keywords after program name in console");
		} else {
			System.out.print("Hello");
			for (int i = 0; i < len; i++) {
				System.out.print(" " + args[i]);
			}
			System.out.println();
		}
	}
}
