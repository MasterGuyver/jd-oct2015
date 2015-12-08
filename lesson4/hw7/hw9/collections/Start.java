package hw6collections;
public class Start {

	public static void main(String[] args) {
		
	System.out.println("test all methods");
	System.out.println();
	
	SimpleStack ss = new SimpleStack();
	ss.push("test 1");
	
	ss.push("test 2");
	ss.push("test 3");
	
	System.out.println();
	ss.printStack();
	
	
	System.out.println();
	System.out.println("Peek element!");
	System.out.println();
	System.out.println((String)ss.peek());
	System.out.println();
	System.out.println("Pop element!");
	System.out.println();
	System.out.println((String)ss.pop());
	System.out.println();
	System.out.println("After pop element!");
	System.out.println();
	ss.printStack();
	ss.pop();
	ss.pop();
	ss.pop();
	}

}
