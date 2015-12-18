package elements;

public class Eagle extends Block {
	
	public Eagle() {
		this(256, 512);
	}
	
	public Eagle(int x, int y) {
		super(x, y); 
		setImage("Eagle.png");
	}
	
}
