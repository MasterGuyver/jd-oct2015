/**
 * Created by Guyver on 25.01.16.
 */
public class Shevrolet extends Car {
    String type;
    public Shevrolet(String type, int doors, String color, int speed) {
        super(doors,color,speed);
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
