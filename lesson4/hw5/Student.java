package b5;

public class Student {
	String name;
	String secondName;
	
	public Student() {
		this(null, null);
	}
	
	public Student(String name, String secondName) {
		this.setStudent(name,secondName);
	}
	
	public void setStudent(String name, String secondName) {
		this.name = name;
		this.secondName = secondName;
	}

	public String getName() {
		return name;
	}

	public String getSecondName() {
		return secondName;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student s = (Student) obj;
		if(name.equals(s.name) && secondName.equals(s.secondName)
				&& name!=null && secondName!=null)	 {
			return true;
		}
		}
		return false;
	}

	@Override
	public String toString() {
		String s = secondName +" "+name;
		return s;
	}
	
}
