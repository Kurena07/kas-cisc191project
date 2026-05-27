package domain.rpg.actions;

import java.util.List;

import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Player;
import domain.rpg.data.characters.Enemy;
import domain.rpg.data.items.Item;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 6, 2026
 *
 * Responsibilities of class: 
 * Check if the item can be used or not. If it can, use the item, remove from inventory
 * and set action as complete. If not, display relevant message and set action as incomplete
 *
 */
/**
 * Class is-an Action
 * Class is
 */

public class ItemAction extends Action
{
	Item item;
	Player p;
	/**
	 * 
	 */
	public ItemAction(Item item, Player user)
	{
		this.item = item;
		p = user;
	}
	
	@Override
	public void performAction(Character user, List<Character> target)
	{
		if (!user.equals(p))
		{
			setActionComplete(false);
			//TODO error message
			
		}
		else if (p.getInventory().isEmpty())
		{
			//TODO no items message
			setActionComplete(false);
			System.out.println("Your inventory is empty!");
		}
		else if (!p.getInventory().contains(item))
		{
			//TODO doesnt contain this item message
			setActionComplete(false);
			System.out.println("You don't have this item!");
		}
		else
		{
			item.useItem(p);
			p.getInventory().remove(item);
			setActionComplete(true);
		}
	}
}
