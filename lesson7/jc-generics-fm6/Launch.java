/**
 * Created by Guyver on 25.01.16.
 */
public class Launch {
    public static void main(String[] args) {
        Container<Car> container = new Container<>();
        container.add(new Toyota(4,"Black",250,"Sidan"));
        container.add(new Shevrolet("Sidan",4,"Blue",100));
        container.add(new Shevrolet("Sidan", 2, "Black", 100));
        container.delete(1);
        System.out.println("First car color: " + container.getElement(0).getColor());
        System.out.println("Second car color: " + container.getElement(1).getColor());
    }
}
