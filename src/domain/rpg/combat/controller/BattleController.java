package domain.rpg.combat.controller;

import java.util.ArrayList;
import java.util.List;

import domain.rpg.actions.*;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.data.characters.Boss;
import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Player;
import domain.rpg.data.items.Item;
import domain.rpg.data.traits.skills.Skill;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 6, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

public class BattleController
{
	private BattleManager bm;
	private Action action;
	
	/**
	 * 
	 */
	public BattleController(BattleManager manager)
	{
		bm = manager;
	}

	public void useAttack(Character user, List<Character> targets)
	{
		action = new AttackAction();
		action.performAction(user, targets);
		//TODO
		bm.hasBattleEnded();
		if (action.isActionComplete())
		{
			removeDeadEnemies(targets);
			bm.nextTurn();
		}
	}
	
	public void useSkill(Skill skill, Character user, List<Character> targets)
	{
		action = new SkillAction(skill);
		action.performAction(user, targets);
		//TODO
		bm.hasBattleEnded();
		if (action.isActionComplete())
		{
			removeDeadEnemies(targets);
			bm.nextTurn();
		}
	}
	
	
	public void useItem(Item item, Player user)
	{
		action = new ItemAction(item, user);
		action.performAction(user, null);		
		//TODO
		if (action.isActionComplete())
		{
			bm.nextTurn();
		}
	}
	
	public void enemyTurn(Character enemy)
	{
		if (bm.isAlive(enemy))
		{
			useAttack(enemy, getPlayerArray());
		}
		else 
		{
			bm.nextTurn();
			bm.getEnemies().remove(enemy);
		}
	}
	
//	//TODO
//	public void bossTurn(Boss boss)
//	{
//		
//	}
	
	public void removeDeadEnemies(List<Character> targets)
	{
		for (Character t : targets)
		{
			if (t.getCurrentHP() == 0)
			{
				bm.getEnemies().remove(t);
			}
		}
	}
	
	public Character currentTurn()
	{
		return bm.getCurrentTurn();
	}
	
	public String playerStats()
	{
		return bm.playerStats();
	}
	
	public String enemyStats(Character enemy)
	{
		return bm.enemyStats(enemy);
	}
	
	public ArrayList<Character> selectTarget(int choice)
	{
		return new ArrayList<Character>(getEnemies().subList(choice - 1, choice));
	}
	
	public Player getPlayer()
	{
		return bm.getPlayer();
	}
	
	public ArrayList<Character> getPlayerArray()
	{
		return bm.getPlayerArray();
	}
	
	public ArrayList<Character> getEnemies()
	{
		return bm.getEnemies();
	}
	
	public BattleManager getManager()
	{
		return bm;
	}
	
}
