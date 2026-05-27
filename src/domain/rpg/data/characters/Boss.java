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
	private Skill skill;
	
	/**
	 * 
	 */
	public Boss(String name, int hp, int att, int mp, Skill initSkill)
	{
		// TODO Auto-generated constructor stub
		super(name, hp, att);
		setMaxMP(mp);
		setCurrentMP(mp);
		skill = initSkill;
		setBoss(true);
	}
	
	public void useSkill(Skill skill)
	{
		if (skill.getCost() > getCurrentMP())
		{
			//TODO not enough energy message
		}
		else
		{
			//otherwise, consume skill points
			setCurrentMP(getCurrentMP() - skill.getCost());
			
			//TODO
			//and use skill
		}
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
