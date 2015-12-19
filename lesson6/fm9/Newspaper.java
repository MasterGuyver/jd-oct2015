package fm9;

public class Newspaper implements Copy {
	@Override
	public void update(String message) {
		System.out.println(toString()+": "+message);
	}

}
