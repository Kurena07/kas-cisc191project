package domain.rpg.actions;

import java.util.List;

import domain.rpg.data.characters.Character;
import domain.rpg.data.traits.skills.*;
import domain.rpg.data.traits.skills.Skill;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 6, 2026
 *
 * Responsibilities of class: 
 * Determine whether the skill can be used or not. If it can, use the skill and 
 * set action as complete. If not, display message and set action as incomplete
 *
 */
/**
 * Class is-an Action
 * Class is
 */

public class SkillAction extends Action
{
	Skill skill;
	/**
	 * 
	 */
	public SkillAction(Skill skill)
	{
		this.skill = skill;
	}
	
	@Override
	public void performAction(Character user, List<Character> targets)
	{
		if (skill.getCost() > user.getCurrentMP())
		{
			setActionComplete(false);
			setMessage("You don't have enough skill points!");			
		}
		else
		{
			skill.useSkill(user, targets);
			setActionComplete(true);
			setMessage(user.getName() + " used " + skill.getName() + ". \n" + skill.getMessage());
		}
	}

}
