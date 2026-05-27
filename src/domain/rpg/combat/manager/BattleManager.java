package domain.rpg.combat.manager;

import java.util.*;

import domain.rpg.actions.Action;
import domain.rpg.data.characters.*;
import domain.rpg.data.characters.Character;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
 * 
 * Version/Date: May 4, 2026
 *
 * Responsibilities of class: start and manage combat by using user input and having predetermined actions for enemies
 *
 */
/**
 * Class is-a
 * Class is
 */

public class BattleManager
{
	private ArrayList<Character> player;
	private ArrayList<Character> enemies = new ArrayList<>();
	private Queue<Character> turn = new LinkedList<>();
	private boolean playerWins;
	
	/**
	 * 
	 */
	public BattleManager(Player user)
	{
		player = new ArrayList<>(List.of(user));
	}
	
	public void startBattle(ArrayList<Character> enemyList)
	{
		resetTurn();
		enemies = enemyList;
		
		//add all participants into turn queue, starting with player
		getPlayer().resetStats(); //make sure player stats are set to max
		turn.add(getPlayer());
		for (Character enemy : enemies)
		{
			//make sure enemy stats are set to max
			enemy.setCurrentHP(enemy.getMaxHP());
			enemy.setCurrentMP(enemy.getMaxMP());
			//add enemy to turn
			turn.add(enemy);
		}
	}
	
	public boolean isAlive(Character target)
	{
		if (target.getCurrentHP() == 0)
			return false;
		return true;
	}
	
	public void resetTurn()
	{
		while (!turn.isEmpty())
			turn.remove();
	}
	
	public Character getCurrentTurn()
	{
		return turn.peek();
	}
	
	public void nextTurn()
	{
		Character temp = turn.poll();
		if (temp.getCurrentHP() != 0)
		{
			turn.add(temp);
		}
	}
	
	public boolean hasBattleEnded()
	{
		//TODO
		if (!isAlive(getPlayer()) || enemies.isEmpty())
		{
			if (enemies.isEmpty())
			{
				setPlayerWins(true);
			}
			else if (!isAlive(getPlayer()))
			{
				setPlayerWins(false);
			}
			return true;
		}
		return false;
	}
	
	public Player getPlayer()
	{
		return (Player) player.getFirst();
	}
	
	public ArrayList<Character> getPlayerArray()
	{
		return player;
	}
	
	public ArrayList<Character> getEnemies()
	{
		return enemies;
	}
	
//	public ArrayList<Character> getEnemy(int i)
//	{
//		return (ArrayList<Character>) enemies.subList(i, i+1);
//	}
	
	public String playerStats()
	{
		return player.toString();
	}
	
	public String enemyStats(Character enemy)
	{
		return "[" + enemy.toString() + "]";
	}

	public boolean hasPlayerWon()
	{
		return playerWins;
	}

	public void setPlayerWins(boolean playerWins)
	{
		this.playerWins = playerWins;
	}
}
