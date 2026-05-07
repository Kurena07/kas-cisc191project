package domain.rpg;

import java.util.ArrayList;
import java.util.Scanner;

import domain.rpg.data.characters.*;
import domain.rpg.data.traits.classes.CharacterClass;
import domain.rpg.data.traits.skills.Skill;
import domain.rpg.data.traits.skills.SkillFactory;
import domain.rpg.managers.*;
import domain.rpg.views.BattleView;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
 * 
 * Version/Date: May 4, 2026
 *
 * Responsibilities of class: orchestrate the whole game 
 *
 */
/**
 * Class is-a
 * Class is
 */

public class Game
{
	private BattleManager bm;
	private StoryManager sm;
	private Player player;
	
	/**
	 * 
	 */
	public Game()
	{
//		BattleView view = new BattleView();
		player = new Player();
		player.setCharClass(CharacterClass.Types.WARRIOR);
		System.out.println(player.toString());
//		player.levelUp();
//		System.out.println(player.toString());
		player.resetStats();
		Enemy enemy = new Enemy("Test", 100, 10);
		enemy.attack(player);
		System.out.println(player.getCurrentHP());
		player.attack(enemy);
		System.out.println(enemy.getCurrentHP());
		
		ArrayList<Enemy> list = new ArrayList<>();
		Enemy enemy1 = new Enemy("Test", 120, 10);
//		list.add(enemy1);
//		list.add(enemy);
//		player.attack(list.get(0));
//		System.out.println(list.get(0).getCurrentHP());
		
//		Scanner keyboard = new Scanner(System.in);
//		int target = keyboard.nextInt() - 1;
//		player.attack(list.get(target));
//		System.out.println(list.get(target).getCurrentHP());
//		target = keyboard.nextInt() - 1;
//		player.attack(list.get(target));
//		System.out.println(list.get(target).getCurrentHP());
		
		
		player.setSkills(SkillFactory.fromType(Skill.Types.CHARGED_SLASH), null);
		player.getSkill(0).useSkill(enemy1, player);
		System.out.println(enemy1.getCurrentHP());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new Game();

	}

}
