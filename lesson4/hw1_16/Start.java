package b9_15;
import java.util.Iterator;
public class Start {

	public static void main(String[] args) {
		
	System.err.println("test all methods");
	System.out.println();
	
	SimpleLinkedList all = new SimpleLinkedList();
	all.addFirst("test 1");
	
	all.addFirst("test 2");
	all.addFirst("test 3");
	System.out.println("Iterator one!");
	System.out.println();
	// First method
	for(Object o : all) {
		String s = (String) o;
		System.out.println(s);
	}
	System.out.println();
	System.out.println("Iterator two!");
	System.out.println();
	//Second method
	for(Iterator<Object> it =all.iterator(); it.hasNext();) {
		Object o = it.next();
		System.out.println(o);
	}
	
	System.out.println();
	System.out.println("Remove element!");
	System.out.println();
	for(Iterator<Object> it =all.iterator(); it.hasNext();) {
		it.next();
		it.remove();
		break;
	}
	
	all.printList();
	
	System.out.println();
	
	all.addFirst("test 3");
	System.out.print(all.getSize()+ " ");
	all.printList();
	
	String mark = new String("mark");
	
	all.addLast(mark);
	all.addLast("test 0");
	System.out.print(all.getSize()+" ");
	all.printList();
	
    String middle = new String("middle");
	
	all.addAfter(middle,mark);
	System.out.print(all.getSize()+" ");
	all.printList();
	
	all.addAfter("middle (-1)", mark);
	System.out.println(all.getSize()+" ");
	all.printList();
	
	System.out.println();
	System.err.println("test addAfter, link size == 1");
	System.out.println();
	
	all = new SimpleLinkedList();
	all.addLast(mark);
	System.out.print(all.getSize()+ " ");
	all.printList();
	
	all.addAfter("last", mark);
	System.out.print(all.getSize()+" ");
	all.printList();
	
	System.out.println();
	System.err.println("test addAfter, no prev element exception");
	System.out.println();
	
	all = new SimpleLinkedList();
	all.addLast("test");
	System.out.print(all.getSize()+ " ");
	all.printList();
	
	all.addAfter("last",mark);
	System.out.print(all.getSize()+" ");
	all.printList();
	
	}

}
