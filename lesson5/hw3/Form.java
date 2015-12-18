import javax.swing.*;
import java.awt.*;

/**
 * Created by Guyver on 18.12.15.
 */

public class Form {
    Form() {
    JFrame f = new JFrame("Quadro");
    f.setMinimumSize(new Dimension(800, 600));
    f.setLocation(300, 100);
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Figure quad = new Figure();    
    f.getContentPane().add(quad);
    f.pack();
    f.setVisible(true);
}
    public static void main(String[] args) {
        new Form();
    }
}
