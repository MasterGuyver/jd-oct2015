/**
 * Created by User on 17.11.2015.
 */
public class Ingredient extends AbstractSubject {
    private int amount;
    public Ingredient(String name) {
        setIngredient(name,0,0);
    }
    public Ingredient(String name, float price, int amount) {
        setIngredient(name,price,amount);
          }
    public void setIngredient(String name, float price, int amount) {
        super.setSubject(name,price);
        this.amount = amount;
    }

    public void setIngredientAmount(int amount) {
        this.amount = amount;
    }
    public int getIngredientAmount() {
        return amount;
    }
}
