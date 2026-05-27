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

public class HealingPotion extends Item
{

	/**
	 * @param name
	 * @param amount
	 * @param desc
	 */
	public HealingPotion(String name, int amount, String desc)
	{
		super(name, amount, desc, "Heal");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useItem(Character target)
	{
		int recover = getAmount() + target.getCurrentHP();
		if (recover > target.getMaxHP())
		{
			target.setCurrentHP(target.getMaxHP());
			System.out.println(getName() + " healed hp");
		}
		else
		{
			target.setCurrentHP(target.getCurrentHP() + getAmount());			
			System.out.println(getName() + " recovered " + getAmount() + " health");
		}
	}	
}
