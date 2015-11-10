package hw5;

public enum Genre {
Fantasy(0),Food(1),Computers(2),Fiction(3),History(4);
private int id;
private Genre(int id) {
	this.id = id;
}
public int getId() {
	return id;
}

}
