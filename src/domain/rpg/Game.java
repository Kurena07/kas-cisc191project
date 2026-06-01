package domain.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.rpg.actions.AttackAction;
import domain.rpg.combat.controller.BattleController;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.combat.view.BattleScanner;
import domain.rpg.combat.view.main.BattleView;
import domain.rpg.combat.view.main.CombatPanels;
import domain.rpg.data.characters.*;
import domain.rpg.data.characters.Character;
import domain.rpg.data.items.HealingPotion;
import domain.rpg.data.items.Item;
import domain.rpg.data.items.ManaPotion;
import domain.rpg.data.traits.classes.CharacterClass;
import domain.rpg.data.traits.classes.CharacterClass.Types;
import domain.rpg.data.traits.skills.Skill;
import domain.rpg.data.traits.skills.SkillFactory;
import domain.rpg.story.StoryManager;

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
	private BattleScanner scan;
	private BattleController bc;
	
	
	/**
	 * 
	 */
	public Game()
	{
		player = new Player();
		player.setName("Player");
		player.setCharClass(Types.WARRIOR);
		ArrayList<Item> items = new ArrayList<>(List.of(
				new HealingPotion("Potion", 10, "Heals 10 hp"),
				new HealingPotion("Greater Potion", 20, "Heals 20 hp"),
				new ManaPotion("Phial", 10, "Recovers 10 mp"), 
				new ManaPotion("Large Phial", 20, "Recovers 20 mp")
				));
		player.setInventory(items);
		ArrayList<Character> enemies = new ArrayList<>(List.of(
				new Enemy("Slime", 70, 10, "/domain/rpg/images/Slime.png"), 
				new Enemy("Slime", 70, 10, "/domain/rpg/images/Slime.png")
				));
		ArrayList<Character> enemy = new ArrayList<>(List.of(
				new Enemy("Slime", 70, 10, "/domain/rpg/images/Slime.png")
				));
		ArrayList<Character> boss = new ArrayList<>(List.of(
				new Boss("Boss Slime", 200, 20, 50, SkillFactory.fromType(Skill.Types.ENEMY_SKILL), "/domain/rpg/images/Slime.png")));
		bm = new BattleManager(player);
		bm.startBattle(enemies);
//		scan = new BattleScanner(bm);
		GameView view = new GameView();
		BattleView battle = new BattleView(view, bm);
		battle.setVisible(true);
		

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
