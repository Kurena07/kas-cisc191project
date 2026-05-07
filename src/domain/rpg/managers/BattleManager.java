package domain.rpg.managers;

import java.util.*;

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
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private Queue<Character> turn = new LinkedList<>();
	
	/**
	 * 
	 */
	public BattleManager(Player user)
	{
		player = user;
	}
	
	public void startBattle(ArrayList<Enemy> enemyList)
	{
		Scanner keyboard = new Scanner(System.in);
		enemies = enemyList;
		
		//add all participants into turn queue, starting with player
		turn.add(player);
		for (Enemy enemy : enemies)
			turn.add(enemy);
		
		//TODO
		while (!turn.isEmpty())
		{
			//TODO
			//0. take out the character first in turn queue/order
			Character current = turn.poll();
			if (current == player)
			{
				//1. if/when it's the player's turn, take action based on the UI input
				int target = keyboard.nextInt() - 1; //replace with UI input
				player.attack(enemies.get(target));
				//1.1. check if the  targeted enemy is alive or not
				if (!isAlive(enemies.get(target)))
					enemies.remove(target);
				//1.2. check if ALL the enemies are alive or not
				//		if not, end battle
				if (enemies.isEmpty())
					break;
			}
			else if (current.getClass().isInstance(enemies)) 
			{				
				//2. if/when it's the enemy's turn, just attack
				current.attack(player);
				//2.1.  if it's the boss, manage whether it uses skill or not
				
				//2.2. check if the player is alive or not
				if(!isAlive(player))
				{
					//TODO
					// end the battle/game over if player is dead
				}
			}
			
			//3. add current character back to the end of turn queue/order
			turn.add(current);
		}
		keyboard.close();
		//battle ends
		//message
	}
	
	public boolean isAlive(Character target)
	{
		if (player.getCurrentHP() == 0)
			return false;
		return true;
	}
}
