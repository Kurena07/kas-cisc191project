package domain.rpg.actions;

import java.lang.annotation.Target;
import java.util.List;

import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Player;
import domain.rpg.data.characters.Enemy;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 6, 2026
 *
 * Responsibilities of class: 
 * Manage attack option. 
 *
 ]
 */
/**
 * Class is-an Action
 * Class is
 */

public class AttackAction extends Action
{
	

	/**
	 * 
	 */
	public AttackAction()
	{
		
	}
	
	@Override
	public void performAction(Character user, List<Character> target)
	{
		user.attack(target.getFirst());
		System.out.println(toString(user, target.getFirst()));
		setActionComplete(true);
	}
	
	public String toString(Character user, Character target)
	{
		return user.getName() + " attacked " + target.getName() + " and did " + user.getAttack() 
		+ " damage!";
	}
}
