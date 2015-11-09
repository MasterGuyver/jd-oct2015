
public class Reference {
private String name;
private Reference ref;
public Reference(String name) {
	this.name = name;
}
public void SetRef(Reference ref) {
	this.ref = ref;
}
public Reference getRef() {
	return ref;
}
public String getName() {
	return name;
}
public void printRef() {
	System.out.println(name+" -> "+ref.getName());
}
}
