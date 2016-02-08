/**
 * Created by Guyver on 25.01.16.
 */
public class Toyota extends Car {
    private String type;

    public Toyota(int countDoors, String color, int speed, String type) {
        super(countDoors, color,speed);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
