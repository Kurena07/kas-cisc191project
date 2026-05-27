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
 * A small triangle arrow component that can be positioned above an enemy sprite
 * in the enemySpritePanel to indicate the current target.
 *
 * Usage in BattleView:
 *
 *   // Create once
 *   SelectionArrow selectionArrow = new SelectionArrow();
 *   enemySpritePanel.add(selectionArrow);
 *   selectionArrow.setVisible(false);
 *
 *   // When a target is selected, position above the enemy sprite:
 *   // (x, y) is the top-center of the enemy sprite component
 *   selectionArrow.pointAt(enemySpriteX + enemySpriteWidth / 2, enemySpriteY);
 *
 *   // Hide when target selection is cancelled
 *   selectionArrow.setVisible(false);
 */
public class SelectionArrow extends JComponent {

    private static final int ARROW_WIDTH = 20;
    private static final int ARROW_HEIGHT = 16;
    private static final int GAP = 4; // gap between arrow tip and sprite

    public SelectionArrow() {
        setSize(ARROW_WIDTH, ARROW_HEIGHT);
        setVisible(false);
    }

    /**
     * Position the arrow so its tip points down at the given (x, y) coordinate.
     * Call this with the top-center point of the enemy sprite.
     *
     * @param x center x of the target (in enemySpritePanel coordinates)
     * @param y top y of the target (in enemySpritePanel coordinates)
     */
    public void pointAt(int x, int y) {
        setLocation(x - ARROW_WIDTH / 2, y - ARROW_HEIGHT - GAP);
        setVisible(true);
        getParent().repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);

        // Triangle pointing downward
        int[] xPoints = {0, ARROW_WIDTH, ARROW_WIDTH / 2};
        int[] yPoints = {0, 0, ARROW_HEIGHT};
        g2.fillPolygon(xPoints, yPoints, 3);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ARROW_WIDTH, ARROW_HEIGHT);
    }
}