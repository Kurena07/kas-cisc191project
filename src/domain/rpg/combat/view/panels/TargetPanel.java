package domain.rpg.combat.view.panels;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import domain.rpg.data.characters.Character;
import domain.rpg.data.traits.classes.CharacterClass;
import domain.rpg.data.traits.skills.SkillFactory;
import domain.rpg.data.traits.skills.Skill.Types;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 21, 2026
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
import java.awt.event.*;

public class TargetPanel extends JPanel {

    private JPanel targetListPanel;
    private JButton selectButton;
    private JButton backButton;
    private Character selectedTarget;
    private Runnable onTargetChanged;
    private Type actionType;
    
    enum Type
    {
    	ATTACK, 
    	SKILL
    }

    public TargetPanel() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0, 160));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Select Target",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 12),
                Color.WHITE
        ));

        GridBagConstraints gbc = new GridBagConstraints();

        // --- Left: target list with 2 columns (takes most space) ---
        targetListPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        targetListPanel.setBackground(Color.BLACK);

        JScrollPane targetScroll = new JScrollPane(targetListPanel);
        targetScroll.setBackground(Color.BLACK);
        targetScroll.getViewport().setBackground(Color.BLACK);
        targetScroll.setBorder(BorderFactory.createEmptyBorder());
        targetScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        targetScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        targetScroll.setMinimumSize(new Dimension(200, 0));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(targetScroll, gbc);

        // --- Right top: Select button (large) ---
        selectButton = new JButton("Select") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.BLACK);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

                g2.setColor(isEnabled() ? new Color(255, 255, 255, 170) : new Color(255, 255, 255, 50));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 8, 8);

                g2.setColor(isEnabled() ? Color.WHITE : new Color(100, 100, 100));
                g2.setFont(new Font("Monospaced", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth("Select")) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString("Select", textX, textY);

                g2.dispose();
            }
        };
        selectButton.setContentAreaFilled(false);
        selectButton.setBorderPainted(false);
        selectButton.setFocusPainted(false);
        selectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectButton.setEnabled(false);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 3, 5);
        gbc.ipadx = 180;
        add(selectButton, gbc);

        // --- Right bottom: Back button ---
        backButton = new JButton("Back") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.BLACK);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 6, 6);

                g2.setColor(new Color(255, 255, 255, 130));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 6, 6);

                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth("Back")) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString("Back", textX, textY);

                g2.dispose();
            }
        };
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(0, 32));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 0, 5, 5);
        gbc.ipadx = 180;
        add(backButton, gbc);
    }

    // --- Public methods ---

    public void addTarget(Character target) {
        JButton btn = createListButton(target.getName());
        btn.addActionListener(e -> {
            selectedTarget = target;
            selectButton.setEnabled(true);

            for (Component c : targetListPanel.getComponents()) {
                if (c instanceof JButton) {
                    ((JButton) c).setContentAreaFilled(false);
                }
            }
            btn.setContentAreaFilled(true);
            btn.setBackground(new Color(40, 40, 40));

            if (onTargetChanged != null) {
                onTargetChanged.run();
            }
        });
        targetListPanel.add(btn);
        targetListPanel.revalidate();
    }

    public void clearTargets() {
        targetListPanel.removeAll();
        selectedTarget = null;
        selectButton.setEnabled(false);
        targetListPanel.revalidate();
        targetListPanel.repaint();
    }

    public void setOnTargetChanged(Runnable callback) {
        this.onTargetChanged = callback;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public Character getSelectedTargetName() {
        return selectedTarget;
    }

    // --- Helpers ---

    private JButton createListButton(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.setFont(new Font("Monospaced", Font.PLAIN, 13));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 30));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!btn.isContentAreaFilled() || btn.getBackground().equals(Color.BLACK)) {
                    btn.setContentAreaFilled(true);
                    btn.setBackground(new Color(50, 50, 50));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (btn.getText() != null && !btn.getText().equals(selectedTarget.getName())) {
                    btn.setContentAreaFilled(false);
                }
            }
        });

        return btn;
    }

	public Type getActionType()
	{
		return actionType;
	}

	public void setActionType(Type action)
	{
		this.actionType = action;
	}
    
    
    
}