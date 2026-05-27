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
				return new Skill("", 15, true, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						//TODO
						useMP(user);
						int dmg = (int) (user.getAttack() * 2);
						targets.getFirst().setCurrentHP(targets.getFirst().takeDamage(dmg));
					}
				};
			case CHARGED_SLASH:
				desc = "";
				return new Skill("Charged Slash", 5, true, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						useMP(user);
						int dmg = (int) (user.getAttack()* 1.75);
						targets.getFirst().setCurrentHP(targets.getFirst().takeDamage(dmg));
					}
				};
			case WIDE_SLASH:
				desc = "";
				return new Skill("Wide Slash", 10, false, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						useMP(user);
						for (Character target : targets)
						{
							user.attack(target);
						}
					}
				};
//			case MAGIC:
//				desc = "";
//				return new Skill("", 10, false, desc) {
//					public void useSkill(Character user, List<Character> targets)
//					{
//						useMP(user);
//						for (Character target : targets)
//						{
//							user.attack(target);
//						}
//					}
//				};
//			case LIFE_DRAIN:
//				desc = "";
//				return new Skill("", 10, false, desc) {
//					public void useSkill(Character user, List<Character> targets)
//					{
//						useMP(user);
//						//drain hp from enemy and recover
//					}
//				};
//			case CRITICAL_STRIKE:
//				desc = "";
//				return new Skill("", 10, false, desc) {
//					public void useSkill(Character user, List<Character> targets)
//					{
//						useMP(user);
//						//x2 damage
//						//random chance of doing x3 damage
//					}
//				};
//			case DOUBLEHIT:
//				desc = "";
//				return new Skill("", 10, false, desc) {
//					public void useSkill(Character user, List<Character> targets)
//					{
//						useMP(user);
//						//hit multiple time at random enemies
//						//or hit one enemy multiple times (random amount)
//					}
//				};
			default:
				return null;
		}
	}	
	
}
