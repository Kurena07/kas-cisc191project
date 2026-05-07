package domain.rpg.data.traits.skills;
import java.util.List;

import domain.rpg.data.characters.Character;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 1, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

public class SkillFactory
{
	public static Skill fromType(Skill.Types skillName)
	{
		String desc = "";
		switch (skillName)
		{
			//TODO
			case ENEMY_SKILL: 
				desc = ""; 
				return new Skill("", -1, -1, desc) {
					public void useSkill(Character target, Character user)
					{
						//TODO
					}
				};
			case CHARGED_SLASH:
				desc = "";
				return new Skill("Charged Slash", -1, -1, desc) {
					public void useSkill(Character target, Character user)
					{
						int dmg = (int) (user.getAttack()* 1.75);
						target.takeDamage(dmg);
					}
				};
			case WIDE_SLASH:
				desc = "";
				return new Skill("Wide Slash", -1, -1, desc) {
					public void useSkill(List<Character> targets, Character user)
					{
						for (Character target : targets)
						{
							user.attack(target);
						}
					}
				};
			default:
				return null;
		}
	}	
	
}
