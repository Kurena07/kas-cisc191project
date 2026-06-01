package domain.rpg.data.traits.skills;

import java.util.*;

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
	private int cost;
	private Character user;
	private Character target;
	private boolean singleTarget;
	private String message;
	
	public enum Types {
		ENEMY_SKILL,
		CHARGED_SLASH,
		WIDE_SLASH,
		FIRE_BLAST,
		LIFE_DRAIN,
		CRITICAL_STRIKE,
		RAPID_STRIKE
	};
	
	/**
	 * 
	 */
	public Skill(String name, int pointCost, boolean single, String desc)
	{
		this.name = name;
		cost = pointCost;
		description = desc;
		singleTarget = single;
	}
	
	public void useSkill(Character user, List<Character> targets) {}
	
	public void useMP(Character user)
	{
		user.setCurrentMP(user.getCurrentMP() - cost);
	}
	
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
	
	public void setCharacters(Character target, Character user)
	{
		this.user = user;
		this.target = target;
	}

	public boolean isSingleTarget()
	{
		return singleTarget;
	}

	public void setSingleTarget(boolean singleTarget)
	{
		this.singleTarget = singleTarget;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
}
