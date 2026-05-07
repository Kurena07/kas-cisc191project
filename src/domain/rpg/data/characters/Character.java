package domain.rpg.data.characters;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 1, 2026
 *
 * Responsibilities of class: hold main stats and methods for a character
 *
 */
/**
 * Class is-an abstact class
 * Class is
 */

public abstract class Character
{
	private String name;
	private int currentHP;
	private int maxHP;
	private int attack;
	
	/**
	 * 
	 */
	public Character()
	{
		// TODO Auto-generated constructor stub
	}
	
	public int takeDamage(int damage)
	{
		if (damage > this.getCurrentHP())
		{
			return 0;
		}
		else
		{
			return this.getCurrentHP() - damage;
		}
	}
	
	public void attack(Character target)
	{
		target.setCurrentHP(target.takeDamage(this.getAttack()));
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCurrentHP()
	{
		return currentHP;
	}

	public void setCurrentHP(int currentHP)
	{
		this.currentHP = currentHP;
	}

	public int getMaxHP()
	{
		return maxHP;
	}

	public void setMaxHP(int maxHP)
	{
		this.maxHP = maxHP;
	}

	public int getAttack()
	{
		return attack;
	}

	public void setAttack(int attack)
	{
		this.attack = attack;
	}
	
	
}
