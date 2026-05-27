package domain.rpg.data.items;

import domain.rpg.data.characters.Character;

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
 * Class is-a
 * Class is
 */

public abstract class Item
{
	private String name;
	private String description;
	private int effectAmount;
	private final String type;
	
	/**
	 * 
	 */
	public Item(String name, int amount, String desc, String type)
	{
		this.name = name;
		effectAmount = amount;
		description = desc;
		this.type = type;
	}
	
	public abstract void useItem(Character target);
	
	@Override
	public String toString()
	{
		return name + " (" + description + ")";
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the effectAmount
	 */
	public int getAmount()
	{
		return effectAmount;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	public String getType()
	{
		return type;
	}

	
}
