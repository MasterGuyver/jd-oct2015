/**
 * Created by Guyver on 02.12.15.
 */
public class Except {
    public static void main(String[] args) {
        try {
            return;
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("I want to be executed!");
        }
    }
}
