import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by Guyver on 18.12.15.
 */
public class Figure extends JPanel implements MouseListener {
    private Color color;
    Figure() {
        color = new Color(2);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(400, 200, 500, 500);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        color = new Color(new Random().nextInt(255),new Random().nextInt(253),new Random().nextInt(255));
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
