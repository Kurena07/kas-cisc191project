package domain.rpg.data.items;

import domain.rpg.data.characters.Character;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 11, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

public class ManaPotion extends Item
{	
	/**
	 * @param name
	 * @param amount
	 * @param desc
	 */
	public ManaPotion(String name, int amount, String desc)
	{
		super(name, amount, desc, "Mana Recover");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useItem(Character target)
	{
		int recover = getAmount() + target.getCurrentMP();
		if (recover > target.getMaxMP())
		{
			target.setCurrentMP(target.getMaxMP());
			System.out.println(getName() + " recovered mana. Mana is full now");
		}
		else
		{
			target.setCurrentMP(target.getCurrentMP() + getAmount());			
			System.out.println(getName() + " recovered " + getAmount() + " mana");
		}
	}

}
