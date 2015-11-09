
public class Launcher {

	public static void main(String[] args) {
		Reference ref1 = new Reference("ref1");
		Reference ref2 = new Reference("ref2");
		Reference ref3 = new Reference("ref3");
		ref1.SetRef(ref2);
		ref2.SetRef(ref3);
		ref3.SetRef(ref1);

		ref1.printRef();
		ref2.printRef();
		ref3.printRef();
	}

}
