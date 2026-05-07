package domain.rpg.data.traits;

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

public class Item
{
	private String name;
	private String description;
	private int effectAmount;
	
	/**
	 * 
	 */
	public Item(String name, int amount, String desc)
	{
		this.name = name;
		effectAmount = amount;
		description = desc;
	}
	
	public int useItem(int target)
	{
		return target += effectAmount;
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "";
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

	
}
