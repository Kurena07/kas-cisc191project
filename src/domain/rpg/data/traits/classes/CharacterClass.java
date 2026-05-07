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
	private int baseStamina;
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
	public CharacterClass(String initName, int hp, int att, int stamina)
	{
		name = initName;
		baseHP = hp;
		baseAttack = att;
		baseStamina = stamina;
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

	public int getBaseStamina()
	{
		return baseStamina;
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
		return name + " HP: " + baseHP + " Attack: " + baseAttack + " Stamina: " + baseStamina;
	}
}
