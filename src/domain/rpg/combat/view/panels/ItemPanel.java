package domain.rpg.combat.view.panels;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import domain.rpg.data.items.Item;

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

public class ItemPanel extends JPanel {

    private JPanel itemListPanel;
    private JLabel itemNameLabel;
    private JTextArea itemDescArea;
    private JButton itemUseButton;
    private JButton backButton;
    private String selectedItemName;

    public ItemPanel() {
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(0, 160));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;

        // --- Left box: item list with 2 columns (2/3 width), scrollable ---
        itemListPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        itemListPanel.setBackground(Color.BLACK);

        JScrollPane itemScroll = new JScrollPane(itemListPanel);
        itemScroll.setBackground(Color.BLACK);
        itemScroll.getViewport().setBackground(Color.BLACK);
        itemScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Inventory",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 12),
                Color.WHITE
        ));
        itemScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemScroll.setMinimumSize(new Dimension(600, 0));
        itemScroll.setPreferredSize(new Dimension(600, 0));

        gbc.gridx = 0;
        gbc.weightx = 2.0;
        gbc.insets = new Insets(0, 0, 0, 3);
        add(itemScroll, gbc);

        // --- Right box: item details (1/3 width) ---
        JPanel itemDetailPanel = new JPanel(new BorderLayout(0, 5));
        itemDetailPanel.setBackground(Color.BLACK);
        itemDetailPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Details",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 12),
                Color.WHITE
        ));

        // Info section
        JPanel itemInfoPanel = new JPanel();
        itemInfoPanel.setLayout(new BoxLayout(itemInfoPanel, BoxLayout.Y_AXIS));
        itemInfoPanel.setBackground(Color.BLACK);
        itemInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        itemNameLabel = new JLabel(" ");
        itemNameLabel.setForeground(Color.WHITE);
        itemNameLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        itemNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemInfoPanel.add(itemNameLabel);
        itemInfoPanel.add(Box.createVerticalStrut(5));

        itemDescArea = new JTextArea(" ");
        itemDescArea.setForeground(new Color(200, 200, 200));
        itemDescArea.setBackground(Color.BLACK);
        itemDescArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        itemDescArea.setEditable(false);
        itemDescArea.setLineWrap(true);
        itemDescArea.setWrapStyleWord(true);
        itemDescArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemInfoPanel.add(itemDescArea);

        itemDetailPanel.add(itemInfoPanel, BorderLayout.CENTER);

        // Use button
        itemUseButton = new JButton("Use");
        styleUseButton(itemUseButton);
        itemUseButton.setEnabled(false);

        JPanel useBtnWrapper = new JPanel(new BorderLayout());
        useBtnWrapper.setBackground(Color.BLACK);
        useBtnWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        useBtnWrapper.add(itemUseButton, BorderLayout.CENTER);
        itemDetailPanel.add(useBtnWrapper, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 3, 0, 3);
        add(itemDetailPanel, gbc);

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

    public void addItem(Item item) {
        JButton btn = createListButton(item.getName());
        btn.setName(item.getName());
        btn.addActionListener(e -> {
            selectedItemName = item.getName();
            itemNameLabel.setText(item.getName());
            itemDescArea.setText(item.getDescription());
            itemUseButton.setEnabled(true);
        });
        itemListPanel.add(btn);
        itemListPanel.revalidate();
    }

    public void clearItems() {
        itemListPanel.removeAll();
        itemNameLabel.setText(" ");
        itemDescArea.setText(" ");
        itemUseButton.setEnabled(false);
        selectedItemName = null;
        itemListPanel.revalidate();
        itemListPanel.repaint();
    }

    public JButton getUseButton() {
        return itemUseButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public String getSelectedItemName() {
        return selectedItemName;
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