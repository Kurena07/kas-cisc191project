package domain.rpg.combat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private String message;
	
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
		else 
		{
			message = "Action didn't work";
		}
		message = action.getMessage();
	}
	
	public void useSkill(Skill skill, Character user, List<Character> targets)
	{
		action = new SkillAction(skill);
		action.performAction(user, targets);
		//TODO
		bm.hasBattleEnded();
		if (action.isActionComplete())
		{
			bm.nextTurn();
			removeDeadEnemies(targets);
		}
		message = action.getMessage();
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
		message = action.getMessage();
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
	
	//TODO
	public void bossTurn(Boss boss)
	{
		Random random = new Random();
		Boolean skill = random.nextInt(4) == 0;
		if (skill == true && (boss.getCurrentMP() >= boss.getSkill().getCost()))
		{
			useSkill(boss.getSkill(), boss, getPlayerArray());
		}
		else
		{
			useAttack(boss, getPlayerArray());
		}
	}
	
	
	public boolean isBossFight()
	{
		if (currentTurn().getIsBoss() == true)
		{
			return true;
		}
		else 
		{
			return false;			
		}
	}
	
	public void removeDeadEnemies(List<Character> targets)
	{
	    List<Character> dead = new ArrayList<>();
	    for (Character t : targets)
	    {
	        if (t.getCurrentHP() == 0)
	        {
	            dead.add(t);
	        }
	    }
	    bm.getEnemies().removeAll(dead);
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
	
	public ArrayList<Character> getTarget(Character enemy)
	{
		int index = getEnemies().indexOf(enemy);
		return new ArrayList<>(getEnemies().subList(index, index+1));
	}
	
	public BattleManager getManager()
	{
		return bm;
	}
	
	public String getMessage()
	{
		return message;
	}
	
}
