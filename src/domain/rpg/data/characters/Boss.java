package domain.rpg.data.characters;

import domain.rpg.data.traits.skills.Skill;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
 * 
 * Version/Date: May 4, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Boss is-an Enemy
 * Class is
 */

public class Boss extends Enemy
{
	private int currentMP;
	private int maxMP;
	private Skill skill;
	
	/**
	 * 
	 */
	public Boss(String name, int hp, int att, int mp, Skill initSkill)
	{
		// TODO Auto-generated constructor stub
		super(name, hp, att);
		skill = initSkill;
		maxMP = mp;
	}
	
	public void useSkill(Skill skill)
	{
		if (skill.getCost() > currentMP)
		{
			//TODO not enough energy message
		}
		else
		{
			//otherwise, consume skill points
			currentMP -= skill.getCost();
			
			//TODO
			//and use skill
		}
	}

	public int getCurrentMP()
	{
		return currentMP;
	}

	public void setCurrentMP(int currentMP)
	{
		this.currentMP = currentMP;
	}

	public int getMaxMP()
	{
		return maxMP;
	}

	public void setMaxMP(int maxMP)
	{
		this.maxMP = maxMP;
	}

	public Skill getSkill()
	{
		return skill;
	}

	public void setSkill(Skill skill)
	{
		this.skill = skill;
	}
	
	
	
}
