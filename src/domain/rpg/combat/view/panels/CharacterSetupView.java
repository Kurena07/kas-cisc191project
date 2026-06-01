package domain.rpg.combat.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import domain.rpg.GameView;
import domain.rpg.data.traits.classes.CharacterClass;
import domain.rpg.data.traits.skills.Skill;

/**
 * Character creation screen with two steps:
 * Step 1: Enter name and choose a class (3-panel split: classes, stats, skills)
 * Step 2: Guild card confirmation with Yes/No buttons
 *
 * Usage:
 *   CharacterSetupView setup = new CharacterSetupView(gameView);
 *   setup.addClass("Warrior", "A sturdy close-combat fighter.", 150, 30, 25, 20, skillsList);
 *   setup.getYesButton().addActionListener(e -> { ... start battle ... });
 *   setup.getNoButton().addActionListener(e -> setup.showSetup());
 */
public class CharacterSetupView {

    private GameView gameView;

    // Card layout to swap between setup and guild card
    private CardLayout mainCardLayout;
    private JPanel mainCardPanel;

    // Step 1 components
    private JPanel setupPanel;
    private JTextField nameField;
    private JPanel classListPanel;
    private JTextArea statsArea;
    private JTextArea skillsArea;
    private JButton selectClassButton;

    // Step 2 components
    private JPanel guildCardPanel;
    private JPanel cardComponent;
    private JLabel cardNameLabel;
    private JLabel cardClassLabel;
    private JLabel cardRankLabel;
    private JButton yesButton;
    private JButton noButton;

    // Data
    private List<CharacterClass> classes = new ArrayList<>();
    private CharacterClass selectedClass;
    private String playerName;
    private ImageIcon guildLogo;

    public CharacterSetupView(GameView gameView) {
        this.gameView = gameView;

        mainCardLayout = new CardLayout();
        mainCardPanel = new JPanel(mainCardLayout);
        mainCardPanel.setBackground(Color.BLACK);

        buildSetupScreen();
        buildGuildCardScreen();

        mainCardPanel.add(setupPanel, "setup");
        mainCardPanel.add(guildCardPanel, "guildcard");

        mainCardLayout.show(mainCardPanel, "setup");

        // Add to GameView's top panel and bottom panel
        gameView.getTopPanel().add(mainCardPanel, BorderLayout.CENTER);
    }

    // =========================================================================
    // STEP 1: Setup Screen
    // =========================================================================

    private void buildSetupScreen() {
        setupPanel = new JPanel(new BorderLayout(0, 5));
        setupPanel.setBackground(Color.BLACK);
        setupPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // --- Top section: title + name input ---
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBackground(Color.BLACK);

        JLabel title = new JLabel("Create Your Character");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        topSection.add(title);
        topSection.add(Box.createVerticalStrut(8));

        // Name row
        JPanel nameRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        nameRow.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
        nameRow.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);
        nameField.setCaretColor(Color.WHITE);
        nameField.setFont(new Font("Monospaced", Font.PLAIN, 13));
        nameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        nameRow.add(nameField);

        nameRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        topSection.add(nameRow);
        topSection.add(Box.createVerticalStrut(5));

        JLabel instructions = new JLabel("Choose a class to view its stats and skills");
        instructions.setForeground(new Color(150, 150, 150));
        instructions.setFont(new Font("Monospaced", Font.PLAIN, 11));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        topSection.add(instructions);

        setupPanel.add(topSection, BorderLayout.NORTH);

        // --- Three-panel split ---
        JPanel splitPanel = new JPanel(new GridBagLayout());
        splitPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 5);

        // Left: Class list (1/4)
        classListPanel = new JPanel();
        classListPanel.setLayout(new BoxLayout(classListPanel, BoxLayout.Y_AXIS));
        classListPanel.setBackground(Color.BLACK);

        JScrollPane classScroll = new JScrollPane(classListPanel);
        classScroll.setBackground(Color.BLACK);
        classScroll.getViewport().setBackground(Color.BLACK);
        classScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Classes",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 11),
                Color.WHITE
        ));
        classScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        classScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx = 0;
        gbc.weightx = 1.0;
        splitPanel.add(classScroll, gbc);

        // Middle: Stats (1/3)
        statsArea = new JTextArea();
        statsArea.setBackground(Color.BLACK);
        statsArea.setForeground(new Color(200, 200, 200));
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        statsArea.setEditable(false);
        statsArea.setLineWrap(true);
        statsArea.setWrapStyleWord(true);
        statsArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JScrollPane statsScroll = new JScrollPane(statsArea);
        statsScroll.setBackground(Color.BLACK);
        statsScroll.getViewport().setBackground(Color.BLACK);
        statsScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Stats",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 11),
                Color.WHITE
        ));

        gbc.gridx = 1;
        gbc.weightx = 1.5;
        splitPanel.add(statsScroll, gbc);

        // Right: Skills (remaining)
        skillsArea = new JTextArea();
        skillsArea.setBackground(Color.BLACK);
        skillsArea.setForeground(new Color(200, 200, 200));
        skillsArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        skillsArea.setEditable(false);
        skillsArea.setLineWrap(true);
        skillsArea.setWrapStyleWord(true);
        skillsArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JScrollPane skillsScroll = new JScrollPane(skillsArea);
        skillsScroll.setBackground(Color.BLACK);
        skillsScroll.getViewport().setBackground(Color.BLACK);
        skillsScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Skills",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 11),
                Color.WHITE
        ));

        gbc.gridx = 2;
        gbc.weightx = 2.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        splitPanel.add(skillsScroll, gbc);

        setupPanel.add(splitPanel, BorderLayout.CENTER);

        // --- Select Class button (goes in bottom panel) ---
        selectClassButton = createStyledButton("Select Class", 16);
        selectClassButton.setPreferredSize(new Dimension(240, 40));
        selectClassButton.setEnabled(false);
        selectClassButton.addActionListener(e -> {
            playerName = nameField.getText().trim();
            if (playerName.isEmpty()) {
                // Flash the name field border red briefly
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                Timer timer = new Timer(1000, ev -> {
                    nameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                });
                timer.setRepeats(false);
                timer.start();
                return;
            }
            if (selectedClass == null) return;

            // Update guild card and show it
            cardNameLabel.setText(playerName);
            cardClassLabel.setText(selectedClass.getName());
            mainCardLayout.show(mainCardPanel, "guildcard");
            cardComponent.repaint();
            showGuildCardBottom();
        });

        JPanel selectWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectWrapper.setBackground(Color.BLACK);
        selectWrapper.add(selectClassButton);

        // Store for later swapping in bottom panel
        setupPanel.putClientProperty("bottomComponent", selectWrapper);
    }

    // =========================================================================
    // STEP 2: Guild Card Screen
    // =========================================================================

    private void buildGuildCardScreen() {
        guildCardPanel = new JPanel(new GridBagLayout());
        guildCardPanel.setBackground(Color.BLACK);

        // The card itself
        cardComponent = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Silver gradient background
                GradientPaint silver = new GradientPaint(
                        0, 0, new Color(208, 208, 208),
                        getWidth(), getHeight(), new Color(176, 176, 176)
                );
                g2.setPaint(silver);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

                // Border
                g2.setColor(new Color(136, 136, 136));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 16, 16);

                // Vertical divider
                int divX = 160;
                g2.setColor(new Color(136, 136, 136));
                g2.setStroke(new BasicStroke(1));
                g2.drawLine(divX, 15, divX, getHeight() - 15);

                // Guild logo circle
                g2.setColor(new Color(85, 85, 85));
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(30, 20, 100, 100);

                // Guild logo image or placeholder
                if (guildLogo != null) {
                    int logoSize = 90;
                    g2.drawImage(guildLogo.getImage(), 35, 25, logoSize, logoSize, null);
                } else {
                    g2.setFont(new Font("Monospaced", Font.PLAIN, 10));
                    FontMetrics fm = g2.getFontMetrics();
                    String t1 = "Guild";
                    String t2 = "Logo";
                    g2.drawString(t1, 80 - fm.stringWidth(t1) / 2, 68);
                    g2.drawString(t2, 80 - fm.stringWidth(t2) / 2, 81);
                }

                // "GUILD CARD" title
                g2.setColor(new Color(51, 51, 51));
                g2.setFont(new Font("Monospaced", Font.BOLD, 16));
                g2.drawString("GUILD CARD", divX + 20, 35);

                // Labels
                g2.setColor(new Color(85, 85, 85));
                g2.setFont(new Font("Monospaced", Font.PLAIN, 10));
                g2.drawString("NAME", divX + 20, 58);
                g2.drawString("CLASS", divX + 180, 58);
                g2.drawString("JOB", divX + 20, 100);

                // Values
                g2.setColor(new Color(34, 34, 34));
                g2.setFont(new Font("Monospaced", Font.BOLD, 14));
                String name = cardNameLabel.getText();
                String cls = cardClassLabel.getText();
                g2.drawString(name, divX + 20, 75);
                g2.drawString(cls, divX + 180, 75);

                g2.setFont(new Font("Monospaced", Font.PLAIN, 13));
                g2.drawString("Adventurer", divX + 20, 117);

                // Bottom line
                g2.setColor(new Color(170, 170, 170));
                g2.setStroke(new BasicStroke(0.5f));
                g2.drawLine(divX + 20, 135, getWidth() - 20, 135);

                // Issued text
                g2.setColor(new Color(102, 102, 102));
                g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
                g2.drawString("Issued by the Adventurers' Guild", divX + 20, 150);
                g2.drawString("License No. 00001", divX + 20, 163);

                // Rank circle (bottom-right)
                int rankCX = getWidth() - 75;
                int rankCY = getHeight() - 60;
                int rankR = 45;
                g2.setColor(new Color(85, 85, 85));
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(rankCX - rankR, rankCY - rankR, rankR * 2, rankR * 2);

                g2.setFont(new Font("Monospaced", Font.PLAIN, 10));
                FontMetrics fm2 = g2.getFontMetrics();
                String rankTitle = "RANK";
                g2.drawString(rankTitle, rankCX - fm2.stringWidth(rankTitle) / 2, rankCY - 10);

                g2.setColor(new Color(51, 51, 51));
                g2.setFont(new Font("Monospaced", Font.BOLD, 26));
                fm2 = g2.getFontMetrics();
                String rank = cardRankLabel.getText();
                g2.drawString(rank, rankCX - fm2.stringWidth(rank) / 2, rankCY + 20);

                g2.dispose();
            }
        };
        cardComponent.setPreferredSize(new Dimension(560, 190));
        cardComponent.setOpaque(false);

        // Hidden labels to hold data
        cardNameLabel = new JLabel("Player");
        cardClassLabel = new JLabel("Class");
        cardRankLabel = new JLabel("F");

        guildCardPanel.add(cardComponent);
    }

    // =========================================================================
    // BOTTOM PANEL CONTENT
    // =========================================================================

    private void showGuildCardBottom() {
        gameView.getBottomPanel().removeAll();
        gameView.getBottomPanel().add(gameView.getTextScrollPane(), BorderLayout.CENTER);

        JPanel confirmPanel = new JPanel();
        confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.Y_AXIS));
        confirmPanel.setBackground(Color.BLACK);
        confirmPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel questionLabel = new JLabel("Is this information correct?");
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPanel.add(questionLabel);
        confirmPanel.add(Box.createVerticalStrut(5));

        JLabel warningLabel = new JLabel("This cannot be changed once confirmed.");
        warningLabel.setForeground(new Color(120, 120, 120));
        warningLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPanel.add(warningLabel);
        confirmPanel.add(Box.createVerticalStrut(15));

        // Yes / No buttons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonRow.setBackground(Color.BLACK);

        yesButton = createStyledButton("Yes", 16);
        yesButton.setPreferredSize(new Dimension(150, 45));

        noButton = createStyledButton("No", 16);
        noButton.setPreferredSize(new Dimension(150, 45));
        noButton.addActionListener(e -> showSetup());

        buttonRow.add(yesButton);
        buttonRow.add(noButton);

        confirmPanel.add(buttonRow);

        gameView.getBottomPanel().add(confirmPanel, BorderLayout.SOUTH);
        gameView.getBottomPanel().revalidate();
        gameView.getBottomPanel().repaint();
    }

    public void showSetupBottom() {
        gameView.getBottomPanel().removeAll();
        gameView.getBottomPanel().add(gameView.getTextScrollPane(), BorderLayout.CENTER);

        JPanel selectWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectWrapper.setBackground(Color.BLACK);
        selectWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        selectWrapper.add(selectClassButton);

        gameView.getBottomPanel().add(selectWrapper, BorderLayout.SOUTH);
        gameView.getBottomPanel().revalidate();
        gameView.getBottomPanel().repaint();
    }

    // =========================================================================
    // PUBLIC METHODS
    // =========================================================================

    /**
     * Add a class option to the setup screen.
     * @param charClass A CharacterClass from ClassFactory
     */
    public void addClass(CharacterClass charClass) {
        classes.add(charClass);
        String name = charClass.getName();

        JButton btn = createClassButton(name);
        btn.addActionListener(e -> {
            selectedClass = charClass;
            selectClassButton.setEnabled(true);

            // Highlight selected
            for (Component c : classListPanel.getComponents()) {
                if (c instanceof JButton) {
                    ((JButton) c).setContentAreaFilled(false);
                }
            }
            btn.setContentAreaFilled(true);
            btn.setBackground(new Color(40, 40, 40));

            // Update stats
            statsArea.setText(name + "\n\n"
                    + "HP:  " + charClass.getBaseHP() + "    MP:  " + charClass.getBaseMP() + "\n"
                    + "ATK: " + charClass.getBaseAttack() + "\n");

            // Update skills
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                Skill skill = charClass.getSkill(i);
                if (skill != null) {
                    sb.append(skill.getName()).append("\n");
                    sb.append(skill.getDescription()).append("\n");
                    sb.append("MP Cost: ").append(skill.getCost()).append("\n\n");
                }
            }
            skillsArea.setText(sb.toString().trim());
        });
        classListPanel.add(btn);
        classListPanel.revalidate();
    }

    /** Show the setup screen (step 1). */
    public void showSetup() {
        mainCardLayout.show(mainCardPanel, "setup");
        showSetupBottom();
    }

    /** Show the guild card screen (step 2). */
    public void showGuildCard() {
        mainCardLayout.show(mainCardPanel, "guildcard");
        showGuildCardBottom();
    }

    /** Set the guild logo image. */
    public void setGuildLogo(ImageIcon logo) {
        this.guildLogo = logo;
    }

    /** Set the rank displayed on the guild card. */
    public void setRank(String rank) {
        cardRankLabel.setText(rank);
    }

    /** Get the player's entered name. */
    public String getPlayerName() {
        return playerName;
    }

    /** Get the selected class name. */
    public String getSelectedClassName() {
        return selectedClass != null ? selectedClass.getName() : null;
    }

    /** Get the selected CharacterClass object. */
    public CharacterClass getSelectedClass() {
        return selectedClass;
    }

    /** Get the Yes button to attach your own action. */
    public JButton getYesButton() {
        return yesButton;
    }

    /** Get the No button. */
    public JButton getNoButton() {
        return noButton;
    }

    /** Get the Select Class button. */
    public JButton getSelectClassButton() {
        return selectClassButton;
    }

    /** Remove the setup view from GameView. */
    public void remove() {
        gameView.getTopPanel().remove(mainCardPanel);
        gameView.getBottomPanel().removeAll();
        gameView.getBottomPanel().add(gameView.getTextScrollPane(), BorderLayout.CENTER);
        gameView.getTopPanel().revalidate();
        gameView.getTopPanel().repaint();
        gameView.getBottomPanel().revalidate();
        gameView.getBottomPanel().repaint();
    }

    /** Make the setup view visible or hidden. */
    public void setVisible(boolean visible) {
        mainCardPanel.setVisible(visible);
    }

    // =========================================================================
    // HELPERS
    // =========================================================================

    private JButton createClassButton(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.setFont(new Font("Monospaced", Font.PLAIN, 13));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        btn.setPreferredSize(new Dimension(0, 28));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!btn.isContentAreaFilled()) {
                    btn.setContentAreaFilled(true);
                    btn.setBackground(new Color(50, 50, 50));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selectedClass == null || !text.equals(selectedClass.getName())) {
                    btn.setContentAreaFilled(false);
                }
            }
        });

        return btn;
    }

    private JButton createStyledButton(String text, int fontSize) {
        JButton button = new JButton(text) {
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
                g2.setFont(new Font("Monospaced", Font.BOLD, fontSize));
                FontMetrics fm = g2.getFontMetrics();
                String t = getText();
                int textX = (getWidth() - fm.stringWidth(t)) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(t, textX, textY);

                g2.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

}