package domain.rpg.data.traits.classes;

import domain.rpg.data.traits.skills.Skill;

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

public class CharacterClass
{
	private String name;
	private int baseHP;
	private int baseAttack;
	private int baseMP;
	private Skill[] skills = new Skill[2];
	
	public enum Types {
		WARRIOR,
		MAGE,
		ROGUE
	};
	
	//TODO
	
	/**
	 * 
	 */
	public CharacterClass(String initName, int hp, int att, int mp, Skill skill1, Skill skill2)
	{
		name = initName;
		baseHP = hp;
		baseAttack = att;
		baseMP = mp;
		setSkills(skill1, skill2);
	}
	
	public void setSkills(Skill skill1, Skill skill2)
	{
		skills[0] = skill1;
		skills[1] = skill2;
	}
	
	public int getBaseHP()
	{
		return baseHP;
	}

	public int getBaseAttack()
	{
		return baseAttack;
	}

	public int getBaseMP()
	{
		return baseMP;
	}

	public String getName()
	{
		return name;
	}
	
	public Skill getSkill(int index)
	{
		return skills[index];
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return name + " HP: " + baseHP + " Attack: " + baseAttack + " MP: " + baseMP;
	}
}
