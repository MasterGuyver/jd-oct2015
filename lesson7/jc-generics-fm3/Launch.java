/**
 * Created by Guyver on 25.01.16.
 */
public class Launch {
    public static void main(String[] args) {
        Container<Integer> container = new Container<>();
        container.add(45);
        container.add(83838);
        container.add(34);
        container.add(-34);
        container.delete(-345);
        System.out.println("First element: " + container.getElement(0));
    }
}
