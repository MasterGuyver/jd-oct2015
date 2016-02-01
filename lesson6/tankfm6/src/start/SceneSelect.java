package start;

import tanks.*;
import fields.*;
import interfaces.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

public class SceneSelect {
    private String nameTank = "Tiger";
    private String[][] field = BattleField.fields[0];
    private JFrame frame;
    private JPanel panelComponents;

    public SceneSelect() {
        frame = new JFrame("BATTLE TANKS");
        frame.setLocation(300, 150);
        frame.setMinimumSize(new Dimension(BattleField.BF_WIDTH + 8, BattleField.BF_HEIGHT + 55));
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this game that has menu items");
        JMenuItem menuItemStart = new JMenuItem("Start", KeyEvent.VK_S);
        menuItemStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (frame.getContentPane().getComponents()[0].equals(panelComponents)) {
                    frame.setVisible(false);
                    new GameThread().start();
                } else {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(panelComponents);
                    frame.getContentPane().repaint();
                    frame.pack();
                }
            }
        });
        menuItemStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        menuItemStart.getAccessibleContext().setAccessibleDescription("This item used for start");
        JMenuItem menuItemEnd = new JMenuItem("End", KeyEvent.VK_E);
        menuItemEnd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuItemEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuItemStart.getAccessibleContext().setAccessibleDescription("This item used for quit");
        menu.add(menuItemStart);
        menu.add(menuItemEnd);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        panelComponents = new JPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panelComponents.setLayout(new GridBagLayout());
        JLabel ltank = new JLabel("Select tank:");
        JPanel selectTanks = new JPanel();
        selectTanks.setLayout(new GridLayout(0, 1));
        selectTanks.add(ltank);
        String[] selectSwitch = {"Tiger", "BT7"};
        JLabel tankPicture = new JLabel();
        tankPicture.setPreferredSize(new Dimension(160, 122));
        updateLabel(tankPicture, "tiger_up.png");
        JRadioButton tigerButton = new JRadioButton(selectSwitch[0]);
        tigerButton.setMnemonic(KeyEvent.VK_T);
        tigerButton.setActionCommand(selectSwitch[0]);
        tigerButton.setSelected(true);
        JRadioButton btButton = new JRadioButton(selectSwitch[1]);
        btButton.setMnemonic(KeyEvent.VK_B);
        btButton.setActionCommand(selectSwitch[1]);
        ButtonGroup groupTanks = new ButtonGroup();
        groupTanks.add(tigerButton);
        groupTanks.add(btButton);

        tigerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == selectSwitch[0]) {
                    updateLabel(tankPicture, "tiger_up.png");
                    nameTank = "Tiger";
                }
            }
        });

        btButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == selectSwitch[1]) {
                    tankPicture.setIcon(createImageIcon("magTank_UP.png"));
                    nameTank = "BT7";
                }
            }
        });
        selectTanks.add(tigerButton);
        selectTanks.add(btButton);
        panelComponents.add(selectTanks, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelComponents.add(tankPicture, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.PAGE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        JPanel selectFields = new JPanel();
        selectFields.setLayout(new BoxLayout(selectFields, BoxLayout.Y_AXIS));
        JLabel lbattle = new JLabel("Select field for Battle:");
        lbattle.setAlignmentX(panelComponents.RIGHT_ALIGNMENT);
        String[] selectBattle = {"Battle field 1(default)", "Battle field 2", "Battle field 3",
                "Battle field random"};
        JComboBox battleList = new JComboBox(selectBattle);
        battleList.setSelectedIndex(0);
        selectFields.add(lbattle);
        selectFields.add(battleList);
        panelComponents.add(selectFields, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.FIRST_LINE_END,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        JPanel pictureFieldsPanel = new JPanel();
        pictureFieldsPanel.setLayout(new BoxLayout(pictureFieldsPanel, BoxLayout.Y_AXIS));
        JLabel lfield = new JLabel("Fields preview:");
        lfield.setHorizontalAlignment(0);
        JLabel pictureField = new JLabel();
        pictureField.setFont(pictureField.getFont().deriveFont(Font.ITALIC));
        pictureField.setHorizontalAlignment(JLabel.CENTER);
        updateLabel(pictureField, getNameField(battleList.getSelectedIndex()));
        pictureField.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pictureField.setPreferredSize(new Dimension(250, 250 + 10));

        battleList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int index = cb.getSelectedIndex();
                updateLabel(pictureField, getNameField(index));
                field = BattleField.fields[index];
            }
        });

        pictureFieldsPanel.add(lfield);
        pictureFieldsPanel.add(pictureField);
        panelComponents.add(pictureFieldsPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Go");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new GameThread().start();
            }
        });

        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panelComponents.add(startButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.LAST_LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelComponents.add(exitButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().add(panelComponents);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static ImageIcon createImageIcon(String path) {
        if (path != null) {
            return new ImageIcon(path);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private static void updateLabel(JLabel picture, String name) {
        ImageIcon icon = createImageIcon(name);
        picture.setIcon(icon);
    }

    private static String getNameField(int index) {
        Random selector = new Random();
        int t = index;
        String[] res = {"BattleField1", "BattleField2", "BattleField3", "BattleField4", "BattleField5",
                "BattleField6"};
        if (index > 3) {
            t = selector.nextInt(6);
        }
        return res[t] + ".png";
    }

    private SceneEnd end = new SceneEnd(this);

    class GameThread extends Thread {
        public void run() {
            try {
                ActionField actionField = new ActionField(field, nameTank, frame.getLocation());
                actionField.runTheGame();
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(end);
                        frame.pack();
                        frame.setVisible(true);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    JFrame getJFrame() {
        return frame;
    }

    JPanel getJPanel() {
        return panelComponents;
    }
}
