/**
 * Created by User on 17.11.2015.
 */
public abstract class AbstractSubject {
    private String name;
    private float price;
   public AbstractSubject() {
        price = 0;
    }
    protected void setSubject(String name, float price) {
        this.name = name;
        this.price = price;
    }
    protected String getName() {
        return name;
    }
    protected float getPrice() {
        return price;
    }
}
