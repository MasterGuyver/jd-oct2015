/**
 * Created by User on 17.11.2015.
 */
public class Storage {
    private Ingredient[][] ingredients;
    private Service service = new Service();
    public Storage() {
        ingredients = new Ingredient[26][26];
    }
    public Storage(int size) {
        ingredients = new Ingredient[26][size];
    }
    public void addToStorage(String name, float price, int amount) {
        service.addSubject(ingredients,new Ingredient(name,price,amount));

    }
    public void removeFromStorage(String name) {
        if(service.deleteSubject(ingredients,name)>-1) {
            System.out.println("Ingredient "+name+" was deleted from storage."+"\n");
        }
        else {
            System.out.println("Ingredient "+name+" was not found in storage."+"\n");
        }
    }
    public Ingredient findInStorage(String name) {
        int[] position = service.findSubject(ingredients,name);
        if(position[0] > -1 && position[1] >-1) {
            return ingredients[position[0]][position[1]]; }
        else
            return null;
    }
}
