package domain.rpg.combat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import domain.rpg.combat.controller.BattleController;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.data.characters.Boss;
import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Enemy;
import domain.rpg.data.items.Item;
import domain.rpg.data.traits.skills.Skill;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 13, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

public class BattleScanner
{
	private BattleController c;
	private Scanner input;
	private boolean scannerOpen;
	private int choice;
	/**
	 * 
	 */
	public BattleScanner(BattleManager manage)
	{	
		input = new Scanner(System.in);
		c = new BattleController(manage);
		scannerOpen = true;
		try 
		{
			while (scannerOpen)
			{
				battleStats();
				
				while (c.currentTurn().equals(c.getPlayer()))
				{
					playerTurn();
				}
				if (c.isBossFight())
				{
					while (!c.currentTurn().equals(c.getPlayer()))
					{
						c.bossTurn((Boss) c.currentTurn());
						System.out.println(c.getMessage());
					}
				}
				else 
				{
					while (!c.currentTurn().equals(c.getPlayer()))
					{
						c.enemyTurn(c.currentTurn());
						if (!c.getEnemies().isEmpty())
						{
							System.out.println(c.getMessage());
						}
					}	
				}
				
				if (c.getManager().hasBattleEnded())
				{
					if (c.getManager().hasPlayerWon())
					{
						System.out.println("You win!");
					}
					else
					{
						System.out.println("Game Over. You Died");
					}
					scannerOpen = false;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			input.close();
		}
	}
	
	public void playerTurn()
	{
		System.out.println("1. Attack\n" + "2. Skill\n" + "3. Item");
		choice = input.nextInt();
		
		//take action based on input
		if (choice == 1) //Attack
		{
			if (c.getEnemies().size() > 1)
			{
				c.useAttack(c.getPlayer(), selectTarget());
			}
			else
			{
				c.useAttack(c.getPlayer(), c.getEnemies());
			}
		}
		else if (choice == 2) //Skill
		{
			//get the skills
			Skill first = c.getPlayer().getFirstSkill();
			Skill second = c.getPlayer().getSecondSkill();
			System.out.println("1. " + first.getName());
			System.out.println("2. " + second.getName());
			choice = input.nextInt();
			
			
			if (choice == 1)
			{
				if (first.isSingleTarget() && c.getEnemies().size() > 1)
				{
					c.useSkill(c.getPlayer().getFirstSkill(), c.getPlayer(),
						selectTarget());
				}
				else
				{
					c.useSkill(c.getPlayer().getFirstSkill(), c.getPlayer(),
							c.getEnemies());
				}
			}
			else if (choice == 2)
			{
				if (second.isSingleTarget() && c.getEnemies().size() > 1)
				{
					c.useSkill(c.getPlayer().getSecondSkill(), c.getPlayer(),
						selectTarget());
				}
				else
				{
					c.useSkill(c.getPlayer().getSecondSkill(), c.getPlayer(),
							c.getEnemies());
				}
			}
			else 
			{
				System.out.println("Invalid input");
				playerTurn();
			}
		}
		else if (choice == 3) //Item
		{
			//print out all items
			ArrayList<Item> temp = c.getPlayer().getInventory();
			for (Item item : temp)
			{
				System.out.println((temp.indexOf(item) + 1) + ". " + item.toString());
			}
			//based on the choice, use the item with the number - 1 to get index
			choice = input.nextInt();
			Item item = temp.get(choice - 1);
			c.useItem(item, c.getPlayer());
		}
		else 
		{
			System.out.println("Invalid input");
			playerTurn();
		}
		System.out.println(c.getMessage());
	}
	
	public ArrayList<Character> selectTarget()
	{
		System.out.println("Select target");
		enemyOptions();
		choice = input.nextInt();
		return c.selectTarget(choice);
	}
	
	public void enemyOptions()
	{
		int i = 1;
		for (Character enemy : c.getEnemies())
		{
			{
				System.out.println(i + ". " + enemy.getName() + " | " + enemy.getCurrentHP() 
				+ "/" + enemy.getMaxHP());
				i++;
			}
		}
	}
	
	public void battleStats()
	{
		System.out.println(c.playerStats());
		for (Character enemy : c.getEnemies())
			System.out.println(c.enemyStats(enemy));
	}
	
	public void closeScanner()
	{
		input.close();
		scannerOpen = false;
	}
	
	public int getChoice()
	{
		return choice;
	}
	
}
