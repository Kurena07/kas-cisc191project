package domain.rpg.combat.view.main;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

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
import domain.rpg.data.characters.Boss;
import domain.rpg.data.characters.Character;

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

public class CombatPanels 
{

    private GameView gameView;
    private BattleController bc;

    // Top panel components
    private JPanel enemySpritePanel;
    private java.util.HashMap<Enemy, EnemySpriteComponent> enemySpriteMap = new java.util.HashMap<>();
    private JPanel playerStatsBar;
    private SelectionArrow selectionArrow;

    // Bottom panel components
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel actionButtonPanel;
    private SkillPanel skillPanel;
    private ItemPanel itemPanel;
    private TargetPanel targetPanel;
    private String turnText;
	private JPanel endPanel;
	private JButton endButton;

    private String selectedAction;
    
    // Player stats labels
    private JLabel playerNameLabel;
    private JLabel playerLevelLabel;
    private JLabel playerHpLabel;
    private JProgressBar hpBar;
    private JLabel playerMpLabel;
    private JProgressBar mpBar;

    public CombatPanels(GameView gameView, BattleManager manage) {
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
        // Top padding of 25px leaves room for the selection arrow above sprites
        enemySpritePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 25));

        // Use a JLayeredPane so the arrow floats above the sprites
        // without being affected by FlowLayout
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.BLACK);
        layeredPane.setOpaque(true);
        // null layout — we manually size the sprite panel to fill, arrow is absolute
        layeredPane.setLayout(null);

        // enemySpritePanel fills the entire layered pane
        layeredPane.add(enemySpritePanel, JLayeredPane.DEFAULT_LAYER);

        // Selection arrow floats on top, positioned absolutely by pointAt()
        selectionArrow = new SelectionArrow();
        selectionArrow.setVisible(false);
        layeredPane.add(selectionArrow, JLayeredPane.PALETTE_LAYER);

        // Keep enemySpritePanel sized to fill the layered pane
        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                enemySpritePanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                enemySpritePanel.revalidate();
            }
        });

        topPanel.add(layeredPane, BorderLayout.CENTER);

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
        playerNameLabel = new JLabel("Player");
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.weightx = 0;
        playerStatsBar.add(playerNameLabel, gbc);

        // Separator
        playerStatsBar.add(createSeparator(), separatorConstraints(1));

        // Level
        playerLevelLabel = new JLabel("1");
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
        playerHpLabel = new JLabel("100/100");
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
        mpBar = new JProgressBar(0, 50);
        mpBar.setValue(50);
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
        playerMpLabel = new JLabel("50/50");
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
        buildEndPanel();
        skillPanel = new SkillPanel(bc, this);
        itemPanel = new ItemPanel(bc);
        targetPanel = new TargetPanel(bc, this);
        		
        targetPanel.setOnTargetChanged(() -> {
            Enemy target = (Enemy) targetPanel.getSelectedTarget();
            EnemySpriteComponent sprite = getEnemySpriteMap().get(target);
            if (sprite != null) {
                // Convert sprite coordinates from enemySpritePanel space to layeredPane space
                Point spritePos = SwingUtilities.convertPoint(
                    enemySpritePanel, 
                    sprite.getCenterX(), 
                    sprite.getTopY(), 
                    selectionArrow.getParent()
                );
                selectionArrow.pointAt(spritePos.x, spritePos.y);
            }
        });

        cardPanel.add(actionButtonPanel, "actions");
        cardPanel.add(skillPanel, "skills");
        cardPanel.add(itemPanel, "items");
        cardPanel.add(targetPanel, "target");
        cardPanel.add(endPanel, "end");
 
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
        attackBtn.addActionListener(e -> //replace with ButtonActionListener
        {
        	if (bc.getEnemies().size() > 1)
        	{
        		setSelectedAction("Attack");
        		showTargetPanel();
        	}
        	else 
        	{
        		bc.useAttack(bc.getPlayer(), bc.getEnemies());
        		turnText += bc.getMessage() + "\n";
        		enemyAction();
        		updateUI();
        		checkBattleEnd();
        	}
        });
        actionButtonPanel.add(attackBtn);
 
        JButton skillBtn = createActionButton("Skill");
        skillBtn.addActionListener(e -> 
        {
        	setSelectedAction("Skill");
        	showSkillPanel();
        });
        actionButtonPanel.add(skillBtn);
 
        JButton itemBtn = createActionButton("Item");
        itemBtn.addActionListener(e -> showItemPanel());
        actionButtonPanel.add(itemBtn);
    }
    
	private void buildEndPanel()
	{
		endPanel = new JPanel(new GridBagLayout());
		endPanel.setBackground(Color.BLACK);
		
		endButton = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				g2.setColor(Color.BLACK);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
				
				g2.setColor(new Color(255, 255, 255, 170));
				g2.setStroke(new BasicStroke(1.5f));
				g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 8, 8);
				
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Monospaced", Font.BOLD, 18));
				FontMetrics fm = g2.getFontMetrics();
				String text = getText();
				int textX = (getWidth() - fm.stringWidth(text)) / 2;
				int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
				g2.drawString(text, textX, textY);
				
				g2.dispose();
			}
		};
		endButton.setContentAreaFilled(false);
		endButton.setBorderPainted(false);
		endButton.setFocusPainted(false);
		endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		endButton.setPreferredSize(new Dimension(300, 80));
		
		endPanel.add(endButton);
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
    
    public void showEndPanel()
    {
    	cardLayout.show(cardPanel, "end");
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
    
    public EnemySpriteComponent addEnemy(Enemy e) {
        EnemySpriteComponent enemy = new EnemySpriteComponent(e);
        enemySpritePanel.add(enemy);
        enemySpriteMap.put(e, enemy);
        enemySpritePanel.revalidate();
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
    
    public void enemyAction()
    {
		if (bc.isBossFight())
		{
			while (!bc.currentTurn().equals(bc.getPlayer()))
			{
				bc.bossTurn((Boss) bc.currentTurn());
				turnText += bc.getMessage() + "\n";
			}
		}
		else 
		{
			while (!bc.currentTurn().equals(bc.getPlayer()))
			{
				bc.enemyTurn(bc.currentTurn());
				if (!bc.getEnemies().isEmpty())
				{
					turnText += bc.getMessage() + "\n";
				}
			}	
		}
    }
    
	public void checkBattleEnd()
	{
		if (bc.getManager().hasBattleEnded())
		{
			if (getBc().getManager().hasPlayerWon())
			{
				addToTurnText("You win!");
				endButton.setText("Continue");
			}
			else
			{
				addToTurnText("You were defeated...");
				endButton.setText("Exit");
			}
			updateUI();
			showEndPanel();
		}
	}
    
//    public void 
  
    // ui update methods

    public void updatePlayerName() {
        playerNameLabel.setText(bc.getPlayer().getName());
    }

    public void updatePlayerLevel() {
        playerLevelLabel.setText("Lv. " + bc.getPlayer().getLevel());
    }

    public void updatePlayerHp() {
    	int currentHP = bc.getPlayer().getCurrentHP();
        int maxHP = bc.getPlayer().getMaxHP();
        hpBar.setMaximum(maxHP);
        hpBar.setValue(currentHP);
        playerHpLabel.setText(currentHP + " / " + maxHP);
    }

    public void updatePlayerMp() {
    	int currentMP = bc.getPlayer().getCurrentMP();
        int maxMP = bc.getPlayer().getMaxMP();
        mpBar.setMaximum(maxMP);
        mpBar.setValue(currentMP);
        playerMpLabel.setText(currentMP + " / " + maxMP);
    }
    
    public void updateUI()
    {
    	showActionButtons();
    	updatePlayerHp();
    	updatePlayerMp();
    	for (java.util.Map.Entry<Enemy, EnemySpriteComponent> entry : enemySpriteMap.entrySet()) {
    	    Enemy enemy = entry.getKey();
    	    EnemySpriteComponent sprite = entry.getValue();
    	    sprite.setHp(enemy.getCurrentHP());
    	}
    	targetPanel.clearSelectedTarget();
    	skillPanel.clearSkillDesc();
    	itemPanel.clearItemDesc();
    	selectionArrow.setVisible(false);
    	gameView.getTextBox().setText(turnText);
		turnText = "";
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
	
	// --- Public getters/setters ---
	
	public JPanel getEnemySpritePanel() {
		return enemySpritePanel;
	}
	
	public JPanel getActionButtonPanel() {
		return actionButtonPanel;
	}

	public GameView getGameView()
	{
		return gameView;
	}

	public void setGameView(GameView gameView)
	{
		this.gameView = gameView;
	}

	public BattleController getBc()
	{
		return bc;
	}

	public void setBc(BattleController bc)
	{
		this.bc = bc;
	}

	public JPanel getPlayerStatsBar()
	{
		return playerStatsBar;
	}

	public void setPlayerStatsBar(JPanel playerStatsBar)
	{
		this.playerStatsBar = playerStatsBar;
	}

	public SelectionArrow getSelectionArrow()
	{
		return selectionArrow;
	}

	public void setSelectionArrow(SelectionArrow selectionArrow)
	{
		this.selectionArrow = selectionArrow;
	}

	public JPanel getCardPanel()
	{
		return cardPanel;
	}

	public void setCardPanel(JPanel cardPanel)
	{
		this.cardPanel = cardPanel;
	}

	public CardLayout getCardLayout()
	{
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout)
	{
		this.cardLayout = cardLayout;
	}

	public SkillPanel getSkillPanel()
	{
		return skillPanel;
	}

	public void setSkillPanel(SkillPanel skillPanel)
	{
		this.skillPanel = skillPanel;
	}

	public ItemPanel getItemPanel()
	{
		return itemPanel;
	}

	public void setItemPanel(ItemPanel itemPanel)
	{
		this.itemPanel = itemPanel;
	}

	public TargetPanel getTargetPanel()
	{
		return targetPanel;
	}

	public void setTargetPanel(TargetPanel targetPanel)
	{
		this.targetPanel = targetPanel;
	}

	public String getSelectedAction()
	{
		return selectedAction;
	}

	public JLabel getPlayerNameLabel()
	{
		return playerNameLabel;
	}

	public void setPlayerNameLabel(JLabel playerNameLabel)
	{
		this.playerNameLabel = playerNameLabel;
	}

	public JLabel getPlayerLevelLabel()
	{
		return playerLevelLabel;
	}

	public void setPlayerLevelLabel(JLabel playerLevelLabel)
	{
		this.playerLevelLabel = playerLevelLabel;
	}

	public JLabel getPlayerHpLabel()
	{
		return playerHpLabel;
	}

	public void setPlayerHpLabel(JLabel playerHpLabel)
	{
		this.playerHpLabel = playerHpLabel;
	}

	public JProgressBar getHpBar()
	{
		return hpBar;
	}

	public void setHpBar(JProgressBar hpBar)
	{
		this.hpBar = hpBar;
	}

	public JLabel getPlayerMpLabel()
	{
		return playerMpLabel;
	}

	public void setPlayerMpLabel(JLabel playerMpLabel)
	{
		this.playerMpLabel = playerMpLabel;
	}

	public JProgressBar getMpBar()
	{
		return mpBar;
	}

	public void setMpBar(JProgressBar mpBar)
	{
		this.mpBar = mpBar;
	}

	public void setEnemySpritePanel(JPanel enemySpritePanel)
	{
		this.enemySpritePanel = enemySpritePanel;
	}

	public void setActionButtonPanel(JPanel actionButtonPanel)
	{
		this.actionButtonPanel = actionButtonPanel;
	}
	
	public java.util.HashMap<Enemy, EnemySpriteComponent> getEnemySpriteMap() {
	    return enemySpriteMap;
	}

	public String getTurnText()
	{
		return turnText;
	}

	public void addToTurnText(String text)
	{
		turnText += text + "\n";
	}

	public void setSelectedAction(String selectedAction)
	{
		this.selectedAction = selectedAction;
	}
	
	public JButton getEndButton()
	{
		return endButton;
	}
}