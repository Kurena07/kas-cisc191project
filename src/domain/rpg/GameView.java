package domain.rpg;


/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 1, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextArea textBox;
    private JScrollPane textScrollPane;

    public GameView() {
        setTitle("Battle RPG");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Wrapper panel to enforce 3/7 top + 15px gap + 4/7 bottom split
        JPanel splitWrapper = new JPanel(new GridBagLayout());
        splitWrapper.setBackground(Color.BLACK);
        splitWrapper.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // --- Top JPanel (3/7 of available space) with white outline ---
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        gbc.gridy = 0;
        gbc.weighty = 3.0; // 3/7 ratio
        gbc.insets = new Insets(0, 0, 15, 0);
        splitWrapper.add(topPanel, gbc);

        // --- Bottom JTextArea (4/7 of available space) with no outline ---
        textBox = new JTextArea();
        textBox.setBackground(Color.BLACK);
        textBox.setForeground(Color.WHITE);
        textBox.setFont(new Font("Monospaced", Font.PLAIN, 15));
        textBox.setEditable(false);
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        textBox.setCaretColor(Color.WHITE);
        textBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sample text
        textBox.setText("A wild Goblin appeared!\n"
                + "Hero attacks Goblin for 12 damage.\n"
                + "Goblin used Scratch! Hero takes 5 damage.\n");

        textScrollPane = new JScrollPane(textBox);
        textScrollPane.setBorder(BorderFactory.createEmptyBorder());
        textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textScrollPane.getViewport().setBackground(Color.BLACK);

        // Wrap in a bottom panel so BattleView can add buttons to SOUTH
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(textScrollPane, BorderLayout.CENTER);

        gbc.gridy = 1;
        gbc.weighty = 4.0; // 4/7 ratio
        gbc.insets = new Insets(0, 0, 0, 0);
        splitWrapper.add(bottomPanel, gbc);

        setContentPane(splitWrapper);
        
        setVisible(true);
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public JTextArea getTextBox() {
        return textBox;
    }

    public JScrollPane getTextScrollPane() {
        return textScrollPane;
    }
}