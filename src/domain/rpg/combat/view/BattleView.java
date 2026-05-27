package domain.rpg.combat.view;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import domain.rpg.GameView;
import domain.rpg.combat.controller.BattleController;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.combat.view.components.EnemySpriteComponent;
import domain.rpg.combat.view.components.SelectionArrow;
import domain.rpg.combat.view.panels.ItemPanel;
import domain.rpg.combat.view.panels.SkillPanel;
import domain.rpg.combat.view.panels.TargetPanel;
import domain.rpg.data.characters.Enemy;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * 
 * Version/Date: May 20, 2026
 *
 * Responsibilities of class: 
 * Create the view for the combat system
 * Have the 3 action buttons, the player stats (name, level, hp, mp),
 * and enemy sprits
 *	
 */
/**
 * Class is-a
 * Class is
 */

import javax.swing.*;
import java.awt.*;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
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

public class BattleView 
{

    private GameView gameView;
    private BattleController bc;

    // Top panel components
    private JPanel enemySpritePanel;
    private JPanel playerStatsBar;
    private SelectionArrow selectionArrow;

    // Bottom panel components
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel actionButtonPanel;
    private SkillPanel skillPanel;
    private ItemPanel itemPanel;
    private TargetPanel targetPanel;

    // Player stats labels
    private JLabel playerNameLabel;
    private JLabel playerLevelLabel;
    private JLabel playerHpLabel;
    private JProgressBar hpBar;
    private JLabel playerMpLabel;
    private JProgressBar mpBar;

    public BattleView(GameView gameView, BattleManager manage) {
        this.gameView = gameView;
        bc = new BattleController(manage);
        buildTopPanel();
        buildBottomPanels();
    }
    
    private void buildTopPanel() {
        JPanel topPanel = gameView.getTopPanel();
        
        //TODO fix to fit gameView topPanel later
        // --- Enemy sprite area ---
        enemySpritePanel = new JPanel();
        enemySpritePanel.setBackground(Color.BLACK);
        enemySpritePanel.setLayout(new BorderLayout());
        
        //add selection arrow
        selectionArrow = new SelectionArrow();
        enemySpritePanel.add(selectionArrow);
        selectionArrow.setVisible(false);
        
        topPanel.add(enemySpritePanel, BorderLayout.CENTER);

        // --- Player stats bar spanning the bottom of the top panel ---
        playerStatsBar = new JPanel();
        playerStatsBar.setBackground(Color.BLACK);
        playerStatsBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(255, 255, 255, 90)),
                BorderFactory.createEmptyBorder(6, 0, 6, 0)
        ));
        playerStatsBar.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 15, 4, 15);

        // Name
        playerNameLabel = new JLabel("Hero");
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.weightx = 0;
        playerStatsBar.add(playerNameLabel, gbc);

        // Separator
        playerStatsBar.add(createSeparator(), separatorConstraints(1));

        // Level
        playerLevelLabel = new JLabel("Lv. 12");
        playerLevelLabel.setForeground(Color.WHITE);
        playerLevelLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 15, 4, 15);
        playerStatsBar.add(playerLevelLabel, gbc);

        // Separator
        playerStatsBar.add(createSeparator(), separatorConstraints(3));

        // HP label
        JLabel hpLabel = new JLabel("HP");
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        gbc.gridx = 4;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 15, 4, 5);
        playerStatsBar.add(hpLabel, gbc);

        // HP bar
        hpBar = new JProgressBar(0, 100);
        hpBar.setValue(100);
        hpBar.setStringPainted(false);
        hpBar.setPreferredSize(new Dimension(150, 12));
        hpBar.setBackground(new Color(50, 50, 50));
        hpBar.setForeground(Color.WHITE);
        hpBar.setBorderPainted(false);
        gbc.gridx = 5;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 0, 4, 5);
        playerStatsBar.add(hpBar, gbc);

        // HP text
        playerHpLabel = new JLabel("100 / 100");
        playerHpLabel.setForeground(new Color(200, 200, 200));
        playerHpLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        gbc.gridx = 6;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 5, 4, 15);
        playerStatsBar.add(playerHpLabel, gbc);

        // Separator
        playerStatsBar.add(createSeparator(), separatorConstraints(7));

        // MP label
        JLabel mpLabel = new JLabel("MP");
        mpLabel.setForeground(Color.WHITE);
        mpLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        gbc.gridx = 8;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 15, 4, 5);
        playerStatsBar.add(mpLabel, gbc);

        // MP bar
        mpBar = new JProgressBar(0, 60);
        mpBar.setValue(60);
        mpBar.setStringPainted(false);
        mpBar.setPreferredSize(new Dimension(120, 12));
        mpBar.setBackground(new Color(50, 50, 50));
        mpBar.setForeground(Color.WHITE);
        mpBar.setBorderPainted(false);
        gbc.gridx = 9;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 0, 4, 5);
        playerStatsBar.add(mpBar, gbc);

        // MP text
        playerMpLabel = new JLabel("60 / 60");
        playerMpLabel.setForeground(new Color(200, 200, 200));
        playerMpLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        gbc.gridx = 10;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(4, 5, 4, 15);
        playerStatsBar.add(playerMpLabel, gbc);

        topPanel.add(playerStatsBar, BorderLayout.SOUTH);
    }


    private void buildBottomPanels() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.BLACK);
 
        buildActionButtons();
        skillPanel = new SkillPanel();
        itemPanel = new ItemPanel();
        targetPanel = new TargetPanel();
        skillPanel.getBackButton().addActionListener(e -> cardLayout.show(cardPanel, "actions"));
        itemPanel.getBackButton().addActionListener(e -> cardLayout.show(cardPanel, "actions"));
        targetPanel.getBackButton().addActionListener(e -> 
        {
        	selectionArrow.setVisible(false);
        	cardLayout.show(cardPanel, "actions");
        
        });
 
        cardPanel.add(actionButtonPanel, "actions");
        cardPanel.add(skillPanel, "skills");
        cardPanel.add(itemPanel, "items");
        cardPanel.add(targetPanel, "target");
 
        cardLayout.show(cardPanel, "actions");
 
        gameView.getBottomPanel().add(cardPanel, BorderLayout.SOUTH);
    }
 
    // --- Action Buttons ---
 
    private void buildActionButtons() {
        actionButtonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        actionButtonPanel.setBackground(Color.BLACK);
        actionButtonPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 60), 0),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
 
        JButton attackBtn = createActionButton("Attack");
        attackBtn.addActionListener(e -> 
        {
        	if (bc.getEnemies().size() > 1)
        	{
        		bc.useAttack(bc.getPlayer(), bc.selectTarget(-1));
        	}
        	else 
        	{
        		bc.useAttack(bc.getPlayer(), bc.getEnemies());
        	}
        });
        actionButtonPanel.add(attackBtn);
 
        JButton skillBtn = createActionButton("Skill");
        skillBtn.addActionListener(e -> showSkillPanel());
        actionButtonPanel.add(skillBtn);
 
        JButton itemBtn = createActionButton("Item");
        itemBtn.addActionListener(e -> showItemPanel());
        actionButtonPanel.add(itemBtn);
    }
    
    public void showActionButtons() {
        cardLayout.show(cardPanel, "actions");
    }
 
    public void showSkillPanel() {
        cardLayout.show(cardPanel, "skills");
    }
 
    public void showItemPanel() {
        cardLayout.show(cardPanel, "items");
    }
    
    public void showTargetPanel() {
        cardLayout.show(cardPanel, "target");
    }

    private JButton createActionButton(String name) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background
                g2.setColor(Color.BLACK);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

                // White border
                g2.setColor(new Color(255, 255, 255, 170));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 8, 8);

                // Button name
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Monospaced", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int nameX = (getWidth() - fm.stringWidth(name)) / 2;
                g2.drawString(name, nameX, getHeight() / 2 - 2);

                g2.dispose();
            }
        };

        button.setPreferredSize(new Dimension(0, 140));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        //TODO add action listener for battle buttons
        button.addActionListener(null);

        return button;
    }

    private JSeparator createSeparator() {
        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setForeground(new Color(255, 255, 255, 40));
        sep.setPreferredSize(new Dimension(1, 30));
        return sep;
    }
    
    public EnemySpriteComponent addEnemy(Enemy e, int x, int y) {
        EnemySpriteComponent enemy = new EnemySpriteComponent(e.getSprite(), e.getName(),
        		e.getCurrentHP(), e.getMaxHP());
        enemy.setLocation(x, y);
        enemySpritePanel.add(enemy);
        enemySpritePanel.repaint();
        return enemy;
    }

    private GridBagConstraints separatorConstraints(int gridx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(8, 0, 8, 0);
        return gbc;
    }
    
    // 

    // --- Public getters/setters ---

    public JPanel getEnemySpritePanel() {
        return enemySpritePanel;
    }

    public void setPlayerName(String name) {
        playerNameLabel.setText(name);
    }

    public void setPlayerLevel(int level) {
        playerLevelLabel.setText("Lv. " + level);
    }

    public void setPlayerHp(int current, int max) {
        hpBar.setMaximum(max);
        hpBar.setValue(current);
        playerHpLabel.setText(current + " / " + max);
    }

    public void setPlayerMp(int current, int max) {
        mpBar.setMaximum(max);
        mpBar.setValue(current);
        playerMpLabel.setText(current + " / " + max);
    }

    public JPanel getActionButtonPanel() {
        return actionButtonPanel;
    }
    
    public void updateUI()
    {
    	
    }
	
	public void setVisible(boolean visible) {
        enemySpritePanel.setVisible(visible);
        playerStatsBar.setVisible(visible);
        actionButtonPanel.setVisible(visible);
        gameView.getBottomPanel().revalidate();
        gameView.getBottomPanel().repaint();
        gameView.getTopPanel().revalidate();
        gameView.getTopPanel().repaint();
    }
}
