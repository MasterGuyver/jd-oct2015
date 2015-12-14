
public class Start {

	public static void main(String[] args) {
		
	System.err.println("test all methods");
	System.out.println();
	
	SimpleArrayList sal = new SimpleArrayList();
	sal.addFirst("test 1");
	sal.addFirst("test 2");
	sal.addFirst("test 3");
	System.out.println("Remove element!");
	System.out.println();
	for(Object o : sal) {
		String s = (String) o;
		System.out.println(s);
	}
	//sal.remove("test 2");
	
	System.out.println();
	System.out.print(sal.getSize()+ " ");
	sal.printList();
	
	System.out.println();
	System.out.println("Add first element!");
	sal.addFirst("test 3");
	System.out.println();
	System.out.print(sal.getSize()+ " ");
	sal.printList();
	
	String mark = new String("mark");
	
	sal.addLast(mark);
	sal.addLast("test 0");
	System.out.print(sal.getSize()+" ");
	sal.printList();
	
    String middle = new String("middle");
	
	sal.addAfter(mark,middle);
	System.out.print(sal.getSize()+" ");
	sal.printList();
	
	sal.addAfter(mark,"middle (-1)");
		sal.addAfter("middle (-1)","z");
		sal.addAfter("z","z");
		sal.addAfter("z","z");
		sal.addAfter("z","z");
		sal.addAfter("test 0","пщз");
	System.out.println(sal.getSize()+" ");
	sal.printList();
	
	System.out.println();
	System.err.println("test addAfter, link size == 1");
	System.out.println();
	
	sal = new SimpleArrayList();
	sal.addFirst(mark);
	System.out.print(sal.getSize()+ " ");
	sal.printList();
	
	sal.addAfter(mark,"last");
	System.out.print(sal.getSize()+" ");
	sal.printList();
	
	System.out.println();
	System.err.println("test addAfter, no prev element exception");
	System.out.println();
	
	sal = new SimpleArrayList();
	sal.addFirst("test");
	System.out.print(sal.getSize()+ " ");
	sal.printList();
	
	sal.addAfter(mark,"last");
	System.out.print(sal.getSize()+" ");
	System.out.println("object "+sal.getObject());
	sal.printList(); 	
	}

}
