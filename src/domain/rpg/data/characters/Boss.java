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
	public Boss(String name, int hp, int att, int mp, Skill initSkill, String image)
	{
		// TODO Auto-generated constructor stub
		super(name, hp, att, image);
		setMaxMP(mp);
		setCurrentMP(mp);
		skill = initSkill;
		setIsBoss(true);
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
