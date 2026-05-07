package domain.rpg.data.traits.skills;

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

public class Skill
{
	private String name;
	private String description;
	private int effectAmount;
	private int cost;
	private Character user;
	private Character target;
	
	public enum Types {
		ENEMY_SKILL,
		CHARGED_SLASH,
		WIDE_SLASH
	};
	
	/**
	 * 
	 */
	public Skill(String name, int amount, int pointCost, String desc)
	{
		this.name = name;
		effectAmount = amount;
		cost = pointCost;
		description = desc;
	}
	
	public void useSkill(Character target, Character user) {}
	
	public int getCost()
	{
		//TODO
		return cost;
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "";
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public int getEffectAmount()
	{
		return effectAmount;
	}
	
	public void setCharacters(Character target, Character user)
	{
		this.user = user;
		this.target = target;
	}
}
