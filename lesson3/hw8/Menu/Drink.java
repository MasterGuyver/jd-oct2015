/**
 * Created by User on 27.11.2015.
 */
public class Drink extends AbstractSubject {
    public Drink() {
        super();
    }
    public void doOrder(String name,String[] ingredients,int[] amounts,Storage storage) {
        Ingredient temp;
        float price=0;
        int number;
        for(int i = 0;i<ingredients.length;i++) {
            temp = storage.findInStorage(ingredients[i]);
            if(temp == null) {
                System.out.print("Ingredient "+ingredients[i]+" not found in Storage. ");
                System.out.println("Cannot perform Order "+name+"\n");
                price = -1;
                break;
            }
            else {
                number = temp.getIngredientAmount() - amounts[i];
                if(number > 0 || number == 0) {
                    temp.setIngredientAmount(number);
                    price +=temp.getPrice()*number;
                }
                else {
                    System.out.println("Cannot perform Order "+name);
                    System.out.print(", because amount ingredient "+ingredients[i]+" are missing in Storage"+"\n");
                    price = -1;
                    break;
                }
            }
        }
        if(price >= 0) {
            super.setSubject(name,price);
        }
    }
}
