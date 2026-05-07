package domain.rpg.managers;

import domain.rpg.data.traits.*;
import domain.rpg.data.traits.skills.Skill;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
 * 
 * Version/Date: May 4, 2026
 *
 * Responsibilities of class: Take the action the player takes and use the necessary methods
 *
 */
/**
 * Class is-a
 * Class is
 */

public class Action
{
	private BattleManager bm;
//	private BattleView view;
	private Skill skill;
	private Item item;
	
	/**
	 * 
	 */
	public Action()
	{
		
	}
	
	public Action(Skill skill)
	{
		
	}
	
	public Action(Item item)
	{
		
	}
		
	@Override
	public String toString()
	{
		return "";
	}

	public Skill getSkill()
	{
		return skill;
	}

	public Item getItem()
	{
		return item;
	}

}
