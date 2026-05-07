package domain.rpg.data.characters;
import domain.rpg.data.traits.*;
import domain.rpg.data.traits.classes.*;

import java.util.*;

import domain.rpg.data.traits.skills.*;

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
 * Player is-a Character
 * Class is
 */

public class Player extends Character
{
	private int level;
	private CharacterClass charClass;
	private int currentStamina;
	private int maxStamina;
	private Skill[] skills = new Skill[2];
	private ArrayList<Item> inventory = new ArrayList<>();
	
	/**
	 * 
	 */
	public Player()
	{
		//TODO
		setName("Player");
		level = 1;
	}

	public void useItem(Item item)
	{
		//TODO
		// check or simplify
		
		if (inventory.isEmpty())
		{
			//TODO no items message
			System.out.println("Your inventory is empty!");
		}
		else if (item.getName().equals("Healing Potion"))
		{
			if (getCurrentHP() == getMaxHP())
			{
				//message that we're already at max hp
			}
			else if (item.getAmount() + getCurrentHP() > getMaxHP())
			{
				setCurrentHP(getMaxHP());
				//message that hp is maxed out now
			}
			else
			{
				setCurrentHP(item.useItem(getCurrentHP()));
				//message
			}
		}
		else if (item.getName().equals("Stamina Potion"))
		{
			if (currentStamina == maxStamina)
			{
				//message that Stamina is already maxed out
			}
			else if (item.getAmount() + currentStamina > maxStamina)
			{
				currentStamina = maxStamina;
				//message
			}
			else
			{
				currentStamina = item.useItem(currentStamina);
				//message
			}
		}
		//remove item from inventory
		inventory.remove(item);
	}
	
	public boolean skillUsable(Skill skill)
	{
		if (skill.getCost() > currentStamina)
		{
			//TODO not enough energy message
			System.out.println("You don't have enough skill points!");
			return false;
		}
		else
		{
			//otherwise, consume skill points
			currentStamina -= skill.getCost();
			return true;
		}
	}
	
	public void setStats(int hp, int att, int Stamina)
	{
		setMaxHP(hp);
		setAttack(att);
		maxStamina = Stamina;
	}
	
	public void resetStats()
	{
		setCurrentHP(getMaxHP());
		currentStamina = maxStamina;
	}
	
  	public void setSkills(Skill skill1, Skill skill2)
	{
		skills[0] = skill1;
		skills[1] = skill2;
	}
  	
  	public Skill getSkill(int index)
  	{
  		return skills[index];
  	}
	
	public void levelUp()
	{
		//TODO
		level ++;
		setMaxHP(getMaxHP() + 10);
		setAttack(getAttack() + 10);
		resetStats();
		maxStamina += 10;
	}
	
	/**
	 * @param charClass the charClass to set
	 */
	public void setCharClass(CharacterClass.Types classType)
	{
		this.charClass = ClassFactory.fromType(classType);
		this.setStats(charClass.getBaseHP(), charClass.getBaseAttack(), charClass.getBaseStamina());
		this.setSkills(charClass.getSkill(0), charClass.getSkill(1));
	}
	
	@Override
	public String toString()
	{
		return getName() + "\n Level " + level + " HP: " + getMaxHP() + " Attack: " + getAttack() + " Stamina: " + maxStamina;
	}
}
