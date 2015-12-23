package start;

import fields.BattleField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneEnd extends JPanel {
    private JFrame frame;
    public SceneEnd() {
        frame = new JFrame("Game End");
        frame.setLocation(300, 150);
        frame.setMinimumSize(new Dimension(BattleField.BF_WIDTH + 8, BattleField.BF_HEIGHT + 55));
        MenuDraw menu = new MenuDraw(frame);
        this.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Game again");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                frame.setVisible(false);
                //frame.dispose();
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
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
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
