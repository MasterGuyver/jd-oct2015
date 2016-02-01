package start;

import fields.BattleField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneEnd extends JPanel {
    public SceneEnd(SceneSelect select) {
        this.setLayout(new GridBagLayout());
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Game again");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                select.getJFrame().getContentPane().removeAll();
                select.getJFrame().getContentPane().add(select.getJPanel());
                select.getJFrame().getContentPane().repaint();
                select.getJFrame().pack();
            }});
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});
        this.add(startButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.LAST_LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(exitButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, BattleField.BF_WIDTH + 8, BattleField.BF_HEIGHT + 55);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Serif", Font.PLAIN, 80));
        g.drawString("Game Over",115,280);
    }
}
