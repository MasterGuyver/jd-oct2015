/**
 * Created by User on 27.11.2015.
 */
public class Launch {
   public static void main(String[] args) {
        Storage storage = new Storage(50);
        storage.addToStorage("Water",0,15);
        storage.addToStorage("Black tea",4,30);
        storage.addToStorage("Green tea",3,20);
        storage.addToStorage("Milk",1,56);
        storage.addToStorage("Cofee",5,20);
        storage.addToStorage("Sugar",0.5f,300);
        storage.addToStorage("Bergamot",0.2f,100);
        Drink[] drinks = new Drink[5];
        for(int i=0;i<5;i++) {
             drinks[i] = new Drink();
        }
        drinks[0].doOrder("Green tea",new String[]{"Green tea","Water","Sugar"},new int[]{1,1,2},storage);
        System.out.println(drinks[0].getName() +" "+ drinks[0].getPrice()+"\n");
        drinks[1].doOrder("Black Cofee",new String[]{"Cofee","Water","Sugar"},new int[]{1,1,1},storage);
        System.out.println(drinks[1].getName() +" "+ drinks[1].getPrice()+"\n");
        drinks[2].doOrder("Cofee Milk",new String[]{"Cofee","Milk","Water","Sugar"},new int[]{1,1,1,2},storage);
        System.out.println(drinks[2].getName() +" "+ drinks[2].getPrice()+"\n");
        drinks[3].doOrder("Bread",new String[]{"Bread"},new int[]{1},storage);
        storage.addToStorage("Bread",0.1f,5);
        drinks[3].doOrder("Bread",new String[]{"Bread"},new int[]{1},storage);
        System.out.println(drinks[3].getName() +" "+ drinks[3].getPrice()+"\n");
        storage.removeFromStorage("Water");
        drinks[4].doOrder("Green tea",new String[]{"Green tea","Water","Sugar"},new int[]{1,1,2},storage);
    }
}
