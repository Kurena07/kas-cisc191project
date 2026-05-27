package domain.rpg.data.characters;
import domain.rpg.data.items.Item;
import domain.rpg.data.traits.classes.*;
import domain.rpg.data.items.*;

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
	private Skill[] skills = new Skill[2];
	private ArrayList<Item> inventory = new ArrayList<>();
	
	/**
	 * 
	 */
	public Player()
	{
		//TODO
		super("Player", 100, 10, 50);
		level = 1;
		
	}
	
	public void setStats(int hp, int att, int mp)
	{
		setMaxHP(hp);
		setAttack(att);
		setMaxMP(mp);
		resetStats();
	}
	
	public void resetStats()
	{
		setCurrentHP(getMaxHP());
		setCurrentMP(getMaxMP());
	}
	
  	public void setSkills(Skill skill1, Skill skill2)
	{
		skills[0] = skill1;
		skills[1] = skill2;
	}
  	
  	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(ArrayList<Item> inventory)
	{
		this.inventory = inventory;
	}
	
	public ArrayList<Item> getInventory()
	{
		return inventory;
	}
  	
  	public Skill getFirstSkill()
  	{
  		return skills[0];
  	}
  	
  	public Skill getSecondSkill()
  	{
  		return skills[1];
  	}
  	
  	public Skill[] getSkills()
  	{
  		return skills;
  	}
  	
  	public Item getItem(int index)
  	{
  		return inventory.get(index);
  	}
	
	public void levelUp()
	{
		//TODO
		level ++;
		setStats(getMaxHP() + 10, getAttack() + 10, getMaxMP() + 10);
		resetStats();
	}
	
	/**
	 * @param charClass the charClass to set
	 */
	public void setCharClass(CharacterClass.Types classType)
	{
		this.charClass = ClassFactory.fromType(classType);
		this.setStats(charClass.getBaseHP(), charClass.getBaseAttack(), charClass.getBaseMP());
		this.setSkills(charClass.getSkill(0), charClass.getSkill(1));
	}
	
	@Override
	public String toString()
	{
		return getName() + " | Level " + level + " | HP " + getCurrentHP() + "/" + getMaxHP() + 
				" | MP " + getCurrentMP() + "/" + getMaxMP();
	}

	public int getLevel()
	{
		return level;
	}

}
