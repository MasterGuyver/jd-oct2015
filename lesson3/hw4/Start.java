/**
 * Created by Guyver on 02.12.15.
 */
public class Start {
    public static void main(String[] args) {
        maexception();
        return;
    }
    static void maexception() {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new IllegalStateException();
        } finally {
            System.out.println("I want to be printed.");
        }
    }
}
