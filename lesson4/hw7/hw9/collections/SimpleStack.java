package hw6collections;

public class SimpleStack {
	
	private class Entry{
		Object obj;
		Entry ref;
	}
		
	private Entry first;
	private int size = 0;
	
	public SimpleStack() {
		size = 0;
	}
	
	public void push(Object o) {
		Entry entry = new Entry();
		entry.obj = o;
		if (first != null) {
			entry.ref = first;
		}
		first = entry;
		size++;
	}
	
	public boolean isEmpty() {
		if(size == 0) return true;
		return false;
	}
	
	public Object peek() {
		Object temp = null;
		if(isEmpty()) {
			throw new IllegalStateException("Stack is empty."); 
			}
		else {
			temp = first.obj;
		}
		return temp;
	}
	
	public Object pop() {
		Object temp = peek();
		if(temp != null) {
			first = first.ref;
			size--;
		}
		return temp;
	}
	public void printStack() {
			if(size == 0) {
				System.out.print("Stack is empty.");
			}
			System.out.print("{ ");
			Entry cp = first;
			while(cp.ref != null) {
				System.out.print(cp.obj+", ");
				cp = cp.ref;
			}
			System.out.print(cp.obj);
			System.out.println(" }");
		}
		
		int getSize() {
			return size;
		}
		
}
