package fm9;

public class Journal implements Copy {

	@Override
	public void update(String message) {
		System.out.println(toString()+": "+message);
	}

}