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
    private int maxHp;

    /**
     * @param sprite  The enemy's sprite image
     * @param name    The enemy's display name
     * @param currentHp Current HP
     * @param maxHp     Maximum HP
     */
    public EnemySpriteComponent(ImageIcon sprite, String name, int currentHp, int maxHp) {
        this.maxHp = maxHp;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 0)); // transparent
        setOpaque(false);

        // --- Sprite ---
        spriteLabel = new JLabel(sprite);
        spriteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(spriteLabel);

        add(Box.createVerticalStrut(4));

        // --- Name ---
        nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(nameLabel);

        add(Box.createVerticalStrut(2));

        // --- HP bar ---
        hpBar = new JProgressBar(0, maxHp);
        hpBar.setValue(currentHp);
        hpBar.setStringPainted(false);
        hpBar.setBackground(new Color(50, 50, 50));
        hpBar.setForeground(Color.WHITE);
        hpBar.setBorderPainted(true);
        hpBar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        hpBar.setMaximumSize(new Dimension(sprite.getIconWidth(), 8));
        hpBar.setPreferredSize(new Dimension(sprite.getIconWidth(), 8));
        hpBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(hpBar);

        // Size the whole component to fit
        int width = Math.max(sprite.getIconWidth(), 80);
        int height = sprite.getIconHeight() + 30; // sprite + name + bar + spacing
        setSize(width, height);
    }

    /** Update the HP bar. */
    public void setHp(int currentHp) {
        hpBar.setValue(currentHp);
    }

    /** Update max HP and current HP. */
    public void setHp(int currentHp, int newMaxHp) {
        this.maxHp = newMaxHp;
        hpBar.setMaximum(newMaxHp);
        hpBar.setValue(currentHp);
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
}