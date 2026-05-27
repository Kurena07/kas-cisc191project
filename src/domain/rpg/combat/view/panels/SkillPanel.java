package domain.rpg.combat.view.panels;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import domain.rpg.data.traits.skills.Skill;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 20, 2026
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SkillPanel extends JPanel {

    private JPanel skillListPanel;
    private JLabel skillNameLabel;
    private JTextArea skillDescArea;
    private JLabel skillMpCostLabel;
    private JButton skillUseButton;
    private JButton backButton;
    private String selectedSkillName;

    public SkillPanel() {
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(0, 160));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        // --- Left box: skill list with 2 columns (2/3 width) ---
        skillListPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        skillListPanel.setBackground(Color.BLACK);

        JScrollPane skillScroll = new JScrollPane(skillListPanel);
        skillScroll.setBackground(Color.BLACK);
        skillScroll.getViewport().setBackground(Color.BLACK);
        skillScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Skills",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 12),
                Color.WHITE
        ));
        skillScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        skillScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        skillScroll.setMinimumSize(new Dimension(600, 0));
        skillScroll.setPreferredSize(new Dimension(600, 0));

        gbc.gridx = 0;
        gbc.weightx = 2.0;
        gbc.insets = new Insets(0, 0, 0, 3);
        add(skillScroll, gbc);

        // --- Right box: skill details (1/3 width) ---
        JPanel skillDetailPanel = new JPanel(new BorderLayout(0, 5));
        skillDetailPanel.setBackground(Color.BLACK);
        skillDetailPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Details",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 12),
                Color.WHITE
        ));

        // Info section
        JPanel skillInfoPanel = new JPanel();
        skillInfoPanel.setLayout(new BoxLayout(skillInfoPanel, BoxLayout.Y_AXIS));
        skillInfoPanel.setBackground(Color.BLACK);
        skillInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        skillNameLabel = new JLabel(" ");
        skillNameLabel.setForeground(Color.WHITE);
        skillNameLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        skillNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        skillInfoPanel.add(skillNameLabel);
        skillInfoPanel.add(Box.createVerticalStrut(5));

        skillDescArea = new JTextArea(" ");
        skillDescArea.setForeground(new Color(200, 200, 200));
        skillDescArea.setBackground(Color.BLACK);
        skillDescArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        skillDescArea.setEditable(false);
        skillDescArea.setLineWrap(true);
        skillDescArea.setWrapStyleWord(true);
        skillDescArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        skillInfoPanel.add(skillDescArea);
        skillInfoPanel.add(Box.createVerticalStrut(8));

        skillMpCostLabel = new JLabel(" ");
        skillMpCostLabel.setForeground(new Color(180, 180, 180));
        skillMpCostLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        skillMpCostLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        skillInfoPanel.add(skillMpCostLabel);

        skillDetailPanel.add(skillInfoPanel, BorderLayout.CENTER);

        // Use button
        skillUseButton = new JButton("Use");
        styleUseButton(skillUseButton);
        skillUseButton.setEnabled(false);

        JPanel useBtnWrapper = new JPanel(new BorderLayout());
        useBtnWrapper.setBackground(Color.BLACK);
        useBtnWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        useBtnWrapper.add(skillUseButton, BorderLayout.CENTER);
        skillDetailPanel.add(useBtnWrapper, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 3, 0, 3);
        add(skillDetailPanel, gbc);

        // --- Back button (bottom-right, outside boxes) ---
        backButton = new JButton("Back");
        styleBackButton(backButton);

        JPanel backWrapper = new JPanel(new BorderLayout());
        backWrapper.setBackground(Color.BLACK);
        backWrapper.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        backWrapper.add(backButton, BorderLayout.SOUTH);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 3, 0, 0);
        add(backWrapper, gbc);
    }

    // --- Public methods ---

    public void addSkill(Skill skill) {
        JButton btn = createListButton(skill.getName());
        btn.addActionListener(e -> {
            selectedSkillName = skill.getName();
            skillNameLabel.setText(skill.getName());
            skillDescArea.setText(skill.getDescription());
            skillMpCostLabel.setText("MP Cost: " + skill.getCost());
            skillUseButton.setEnabled(true);
        });
        skillListPanel.add(btn);
        skillListPanel.revalidate();
    }

    public void clearSkills() {
        skillListPanel.removeAll();
        skillNameLabel.setText(" ");
        skillDescArea.setText(" ");
        skillMpCostLabel.setText(" ");
        skillUseButton.setEnabled(false);
        selectedSkillName = null;
        skillListPanel.revalidate();
        skillListPanel.repaint();
    }

    public JButton getUseButton() {
        return skillUseButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public String getSelectedSkillName() {
        return selectedSkillName;
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
                btn.setContentAreaFilled(true);
                btn.setBackground(new Color(40, 40, 40));
            }
            public void mouseExited(MouseEvent e) {
                btn.setContentAreaFilled(false);
            }
        });

        return btn;
    }

    private void styleUseButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.setFont(new Font("Monospaced", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 28));
    }

    private void styleBackButton(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.setFont(new Font("Monospaced", Font.PLAIN, 12));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(60, 28));
    }
}
