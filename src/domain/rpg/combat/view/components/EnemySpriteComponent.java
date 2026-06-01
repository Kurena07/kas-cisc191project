package domain.rpg.combat.view.components;

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
import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Enemy;

/**
 * A visual component that displays an enemy's sprite, name, and HP bar.
 * Add this to the enemySpritePanel (null layout) in BattleView.
 *
 * Usage:
 *   EnemySpriteComponent enemyComp = new EnemySpriteComponent(enemy.getSprite(), enemy.getName(), enemy.getHp(), enemy.getMaxHp());
 *   enemyComp.setLocation(x, y);
 *   enemySpritePanel.add(enemyComp);
 */
public class EnemySpriteComponent extends JPanel {

    private JLabel spriteLabel;
    private JLabel nameLabel;
    private JProgressBar hpBar;
    private JLabel hpLabel;
    private int maxHp;
    private Enemy enemy;

    /**
     * @param sprite  The enemy's sprite image
     * @param name    The enemy's display name
     * @param currentHp Current HP
     * @param maxHp     Maximum HP
     */
    public EnemySpriteComponent(Enemy enemy) {
        this.maxHp = enemy.getMaxHP();
        this.enemy = enemy;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 0)); // transparent
        setOpaque(false);

        // --- Sprite ---
        spriteLabel = new JLabel(enemy.getSprite());
        spriteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(spriteLabel);

        add(Box.createVerticalStrut(4));

        // --- Name ---
        nameLabel = new JLabel(enemy.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(nameLabel);

        add(Box.createVerticalStrut(2));

        // --- HP bar + label row (centered) ---
        JPanel hpRow = new JPanel();
        hpRow.setLayout(new BoxLayout(hpRow, BoxLayout.X_AXIS));
        hpRow.setBackground(new Color(0, 0, 0, 0));
        hpRow.setOpaque(false);
        hpRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        hpBar = new JProgressBar(0, maxHp);
        hpBar.setValue(enemy.getCurrentHP());
        hpBar.setStringPainted(false);
        hpBar.setBackground(new Color(50, 50, 50));
        hpBar.setForeground(Color.WHITE);
        hpBar.setBorderPainted(true);
        hpBar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        int barWidth = Math.max(enemy.getSprite().getIconWidth() - 20, 60);
        hpBar.setMaximumSize(new Dimension(barWidth, 8));
        hpBar.setPreferredSize(new Dimension(barWidth, 8));
        hpRow.add(hpBar);

        hpRow.add(Box.createHorizontalStrut(4));

        hpLabel = new JLabel(enemy.getCurrentHP() + "/" + maxHp);
        hpLabel.setForeground(new Color(200, 200, 200));
        hpLabel.setFont(new Font("Monospaced", Font.PLAIN, 9));
        hpRow.add(hpLabel);

        add(hpRow);

        // Size: wide enough for sprite or hp row, tall enough for everything
        int hpRowWidth = barWidth + 4 + hpLabel.getPreferredSize().width;
        int width = Math.max(Math.max(enemy.getSprite().getIconWidth(), hpRowWidth), 80);
        int height = enemy.getSprite().getIconHeight() + 36; // sprite + name + hp row + gaps
        Dimension size = new Dimension(width, height);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        revalidate();
    }
    
    public void update()
    {
    	setHp(enemy.getCurrentHP());
    }

    /** Update the HP bar. */
    public void setHp(int currentHp) {
        hpBar.setValue(currentHp);
        hpLabel.setText(currentHp + "/" + maxHp);
    }

    /** Update max HP and current HP. */
    public void setHp(int currentHp, int newMaxHp) {
        this.maxHp = newMaxHp;
        hpBar.setMaximum(newMaxHp);
        hpBar.setValue(currentHp);
        hpLabel.setText(currentHp + "/" + newMaxHp);
    }

    /** Update the name label. */
    public void setName(String name) {
        nameLabel.setText(name);
    }

    /** Update the sprite image. */
    public void setSprite(ImageIcon sprite) {
        spriteLabel.setIcon(sprite);
    }

    /** Get the center X for positioning the selection arrow. */
    public int getCenterX() {
        return getX() + getWidth() / 2;
    }

    /** Get the top Y for positioning the selection arrow above this component. */
    public int getTopY() {
        return getY();
    }

	public Enemy getEnemy()
	{
		return enemy;
	}
}