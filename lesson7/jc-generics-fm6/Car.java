/**
 * Created by Guyver on 25.01.16.
 */
public class Car {
    private int count;
    private String color;
    private int speed;
    private short id = 0;

    public Car(int countDoors, String color, int speed) {
        count = countDoors;
        this.color = color;
        this.speed = speed;
        ++id;
    }

    public String getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCount() {
        return count;
    }

    public short getId() {
        return id;
    }
}
