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
	private ImageIcon sprite;
	/**
	 * 
	 */
	public Enemy(String name, int hp, int att, String image)
	{
		// TODO check
		super(name, hp, att, 0);
		sprite = new ImageIcon(getClass().getResource(image));
		setIsBoss(false);
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
