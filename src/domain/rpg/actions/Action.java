package domain.rpg.actions;

import domain.rpg.data.traits.*;
import domain.rpg.data.traits.skills.Skill;

import java.util.List;

import domain.rpg.combat.manager.BattleManager;
import domain.rpg.data.characters.Character;

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

public abstract class Action
{
	private boolean actionComplete;
	
	/**
	 * 
	 */
	public Action()
	{
	}
		
	@Override
	public String toString()
	{
		return "";
	}
	
	public abstract void performAction(Character user, List<Character> target);

	public boolean isActionComplete()
	{
		return actionComplete;
	}

	public void setActionComplete(boolean actionComplete)
	{
		this.actionComplete = actionComplete;
	}

	
	
}
