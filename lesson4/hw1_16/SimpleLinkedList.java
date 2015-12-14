
import java.util.Comparator;
import java.util.Iterator;

public class SimpleLinkedList implements Iterable<Object> {
	
	private class Node {
		Object obj;
		Node ref;
	}
	
	
	@Override
	public Iterator<Object> iterator() {
		return new SLLIterator();
	}
	
	class SLLIterator implements Iterator<Object> {
		private Node cp;
		private Node prev;
		
		public SLLIterator() {
			
		}
		
		@Override
		public void remove() {
			if(!hasNext() && prev == null) {
				//one
				cp = null;
				root = null;
			}
			else if(!hasNext() && prev !=null) {
				//last
				prev.ref = null;
				cp = null;
			}
			else if(hasNext() && prev ==null) {
				//first
				root = cp.ref;
				cp =root;
			}
			else {
				//middle
				prev.ref = cp.ref;
				cp = cp.ref;
			}
			size--;
		}

		@Override
		public boolean hasNext() {
			return (cp == null && root != null) || 
				   (cp != null && cp.ref != null);
		}

		@Override
		public Object next() {
			if(cp == null && root != null) {
				cp = root;
				return cp.obj;
			}
			if(hasNext()) {
				prev = cp;
				cp =cp.ref;
				return cp.obj;
			}
			
			throw new IllegalStateException("List has no more elements. ");
		}
	}
	public class ZAComparator implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			String s1 = (String) o1;
			String s2 = (String) o2;
			int result = s1.compareToIgnoreCase(s2);
			if(result < 0) return -1;
			else if(result > 0) return 1;
			return 0;
		}
		
	}	
	private Node root;
	private int size;

	public SimpleLinkedList() {
		size = 0;
	}

	public void addFirst(Object o) {
		Node node = new Node();
		node.obj = o;
		if (root != null) {
			node.ref = root;
		}
		root = node;
		size++;
	}

	public void addLast(Object o) {
		Node node = new Node();
		node.obj = o;
		if (root == null) {
			root = node;
		}
		else {
			Node last = root;
			Node cp = root;
			while (last.ref != null) {
				last = cp;
				cp = cp.ref;
			}
			last.ref = node;
		}
		size++;
	}

	public void addAfter(Object prev, Object o) {
		Node prevPointer = null;
		Node cp = root;
		
		do {
			if (cp.obj == prev) {
				prevPointer = cp;
				break;
			}
			cp =cp.ref;
		}
		while(cp!=null && cp.ref != null);
		if(prevPointer == null) {
			throw new IllegalStateException("List does not contains prev objects");
		}
		Node n = new Node();
		n.obj = o;
		if(prevPointer.ref != null) {
			n.ref = prevPointer.ref;
		}
		prevPointer.ref = n;
		size++;
 		}

	public void printList() {
		if(size == 0) {
			System.out.print("List is empty");
		}
		System.out.print("{ ");
		Node cp = root;
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
