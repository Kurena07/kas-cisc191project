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
				return new Skill("Enemy Skill", 15, true, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						//TODO
						useMP(user);
						int dmg = (int) (user.getAttack() * 2);
						targets.getFirst().setCurrentHP(targets.getFirst().takeDamage(dmg));
					}
				};
			case CHARGED_SLASH:
				desc = "A powerful overhead slash, dealing extra damage to a single target";
				return new Skill("Charged Slash", 20, true, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						useMP(user);
						int dmg = (int) (user.getAttack()* 2);
						targets.getFirst().setCurrentHP(targets.getFirst().takeDamage(dmg));
						setMessage(targets.getFirst().getName() + " took " + dmg + " damage!");
					}
				};
			case WIDE_SLASH:
				desc = "A sweeping horizontal slash that hits all enemies.";
				return new Skill("Wide Slash", 15, false, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						useMP(user);
						for (Character target : targets)
						{
							user.attack(target);
						}
						setMessage("Enemies took " + user.getAttack() + " damage!");
					}
				};
			case FIRE_BLAST:
				desc = "A large fireball that explodes on impact, hitting all enemies. ";
				return new Skill("Fire Blast", 25, false, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						useMP(user);
						for (Character target : targets)
						{
							user.attack(target);
						}
						setMessage("Enemies took " + user.getAttack() + " damage!");
					}
				};
			case LIFE_DRAIN:
				desc = "Siphons the life force of the target, healing the user.";
				return new Skill("Life Drain", 30, true, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						useMP(user);
						//drain hp from enemy and recover
						Character target = targets.getFirst();
						int damage = target.takeDamage((int)(target.getCurrentHP() * 0.20));
						user.setCurrentHP(user.getCurrentHP() + damage);
						if (user.getCurrentHP() > user.getMaxHP())
						{
							user.setCurrentHP(user.getMaxHP());
						}
						setMessage(target.getName() + " lost " + damage	+ " health. You recovered " + damage + " health");
					}
				};
			case CRITICAL_STRIKE:
				desc = "A precise strike aimed at a vital point, with a chance to deal triple damage.";
				return new Skill("Critical Strike", 15, true, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						java.util.Random random = new java.util.Random();
						useMP(user);
						int multiplier = random.nextInt(9);
						if (multiplier%2 == 0)
						{
							user.attack(targets.getFirst());
							setMessage(targets.getFirst().getName() + " took " + user.getAttack() + " damage!");
						}
						else if (multiplier%2 == 1)
						{
							int dmg = (user.getAttack() * 3);
							targets.getFirst().setCurrentHP(targets.getFirst().takeDamage(dmg));
							setMessage("Crit hit! " + targets.getFirst().getName() + " took " + dmg + " damage!");
						}
					}
				};
			case RAPID_STRIKE:
				desc = "A flurry of weak dagger strikes that hits an enemy 2-4 times";
				return new Skill("Rapid Strike", 20, true, desc) {
					public void useSkill(Character user, List<Character> targets)
					{
						java.util.Random random = new java.util.Random();
						useMP(user);
						int hits = random.nextInt(3) + 2;
						int dmg = (int) (user.getAttack() * 0.6);
						int totalDmg = dmg * hits;
						targets.getFirst().setCurrentHP(targets.getFirst().takeDamage(totalDmg));
						setMessage("You hit " + hits + " times! " + targets.getFirst().getName() + " took " + totalDmg + " damage!");
					}
				};
			default:
				return null;
		}
	}	
	
}
