package b5;

import java.util.ArrayList;

public class Classroom {

	private ArrayList<Student> students;

	public Classroom() {
		students = new ArrayList<>();
	}

	public void enter(Student s) {
		students.add(s);
	}

	public void leave(Student s) {
				if(isPresent(s)) students.remove(s);
	}
		

	public int getCount() {
		return students.size();
	}

	public boolean isPresent(Student s) {
		return students.contains(s);
	}
	
	public void printStudentsInfo() {
		Student s = null;
		for(int i=0; i<students.size(); i++) {
			s = students.get(i);
			System.out.println(s.toString());
		}
	}
	
	public ArrayList<Student> getStudents() {
		return new ArrayList<>(students);
	}
}
