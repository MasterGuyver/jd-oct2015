package hw6;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



public class SimpleStack {
	
	private List<Object> data;
	
	public SimpleStack() {
		data = new ArrayList<>();
	}
	
	public void push(Object o) {
		data.add(o);
	}
	
	public boolean isEmpty() {
		if(data.size() == 0) return true;
		return false;
	}
	
	public Object peek() {
		Object temp = null;
		if(isEmpty()) {
			throw new IllegalStateException("Stack is empty."); 
			}
		else {
			temp = data.get(0);
		}
		return temp;
	}
	
	public Object pop() {
		Object temp = peek();
		if(temp != null) {
			data.remove(temp);
		}
		return temp;
	}
	public void printStack() {
			if(data.size() == 0) {
				System.out.print("Stack is empty.");
			}
			System.out.print("{ ");
			System.out.print(Arrays.toString(data.toArray()));
			System.out.println(" }");
		}
		
		int getSize() {
			return data.size();
		}
		
}
