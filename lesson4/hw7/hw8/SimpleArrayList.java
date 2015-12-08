package hw5;

import java.util.Comparator;
import java.util.Iterator;

public class SimpleArrayList implements Iterable<Object> {
	private int max = 1000;
	private int size;
	private int count;
	private Object[] obj;
	
	@Override
	public Iterator<Object> iterator() {
		return new SLLIterator();
	}
	
	class SLLIterator implements Iterator<Object> {
		Object cp;
		Object prev;
		int pos;
		public SLLIterator() {
			
		}
		
		@Override
		public void remove() {
			if(!hasNext() && prev == null) {
				//one
				obj[0] = null;
				cp = null;
			}
			else if(!hasNext() && prev != null) {
				//last
				obj[max-1] = null;
			}
			else if(hasNext() && prev == null ) {
				//first
				shiftLeft(pos);;
			}
			else {
				//middle
				shiftLeft(pos);
			}
			size--;
		}
		
		/*public void remove(Object cp) {
			int i = position(cp);
			if (i < 0) {
				throw new IllegalStateException(
						"Cannot remove element, incorrect name.");
			} else {
				if (i == max - 1) {
					obj[i] = null;
				}
				else if (obj[i] == cp) {
					shiftLeft(i);
				}
				size--;
			}
		}
*/

		@Override
		public boolean hasNext() {
			boolean flag = false;
			if(cp != null) {
				pos = position(cp);
				if(pos < max-1 && pos >=0 && obj[pos+1] != null) {
					flag = true;
				}
			}
			return (cp == null && obj[0]!=null ) || 
			(cp != null && flag);
		}

		@Override
		public Object next() {
			if(cp == null && obj[0] != null) {
				cp = obj[0];
				return cp;
			}
			if(hasNext()) {
				prev = obj[pos];
				cp =obj[pos+1];
				return cp;
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

	public SimpleArrayList() {
		obj = new Object[max];
		size = 0;
		count = -1;
	}

	private void extend() {
		Object[] temp = new Object[2 * max];
		for (int j = 0; j < max && obj[j] != null; j++) {
			temp[j] = obj[j];
		}
		max = 2 * max;
		obj = temp;
	}
	
	private void shiftRight(int i) {
		for (int j = i; j > 0; j--) {
			obj[j] = obj[j - 1];
		}
	}
	
	private void shiftLeft(int i) {
		for (int j = i; j < max - 1 && obj[j] != null; j++)
			{
				obj[j] = obj[j + 1];
			}
	}
	
	private int position(Object o) {
		int i = 0;
		for (; i < max && obj[i] != null; i++) {
			if (obj[i] == o) {
				break;
			}
		}
		if(obj[i]!= o) i = -1;
		return i;
	}
	
	public void addFirst(Object o) {
		if (obj[0] != null) {
			int i = position(null);
			if (i == max - 1) {
				extend();
			}
			shiftRight(i);
		}
		obj[0] = o;
		size++;
	}

	public void addLast(Object o) {
		 if (size == max) {
			extend();
		}
		 else if(size>0) {
			obj[size++] = o;
		 }
		 else {
			 throw new IllegalStateException("Cannjt add element size of list is null");
		 }
	}

	public void addAfter(Object o, Object prev) {
		int i = position(prev);
		if (i == max - 1) {
			addLast(o);
		}
	    else if (obj[i + 1] != null) {
			shiftRight(size+1);
			obj[i + 1] = o;
			size++;
		}
		else if (i < 0 ) {
			throw new IllegalStateException(
					"List does not contains prev objects.");
		}
	}

	public void printList() {
		if (size == 0) {
			System.err.println("List is empty");
		}
		System.out.print("{ ");
		for (int i = 0; i < size - 1; i++) {
			System.out.print(obj[i] + ", ");
		}
		System.out.print(obj[size - 1]);
		System.out.println(" }");
	}

	public int getSize() {
		return size;
	}

	public Object getObject() {
		++count;
		Object o;
		if(count < size) {
			o = obj[count];
		}
		else {
			o = obj[size];
		}
		return o;
	}
	
	public Object[] getArray() {
		return obj;
	}
}
