package domain.rpg.data.characters;

import javax.swing.ImageIcon;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
 * 
 * Version/Date: May 4, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Enemy is-a Character
 * Class is
 */

public class Enemy extends Character
{
	private boolean isBoss = false;
	private ImageIcon sprite;
	/**
	 * 
	 */
	public Enemy(String name, int hp, int att)
	{
		// TODO check
		super(name, hp, att, 0);
	}
	
	/**
	 * 
	 */
	public Enemy(String name, int hp, int att, ImageIcon sprite)
	{
		// TODO check
		super(name, hp, att, 0);
		this.sprite = sprite;
	}
	
	public boolean isBoss()
	{
		return isBoss;
	}
	
	public void setBoss(boolean isBoss)
	{
		this.isBoss = isBoss;
	}
	
	@Override
	public String toString()
	{
		return getName() + " | HP " + getCurrentHP() + "/" + getMaxHP() + 
				" | MP " + getCurrentMP() + "/" + getMaxMP();
	}

	public ImageIcon getSprite()
	{
		return sprite;
	}
}
