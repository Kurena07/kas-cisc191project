package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.rpg.combat.controller.BattleController;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.combat.view.BattleScanner;
import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Enemy;
import domain.rpg.data.characters.Player;
import domain.rpg.data.items.HealingPotion;
import domain.rpg.data.items.Item;
import domain.rpg.data.items.ManaPotion;
import domain.rpg.data.traits.classes.CharacterClass.Types;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 20, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

class TestBattleScanner
{

	@Test
	void test()
	{
		Player player = new Player();
		player.setCharClass(Types.WARRIOR);
		ArrayList<Item> items = new ArrayList<>(List.of(
				new HealingPotion("Potion", 10, "Heals 10 hp"),
				new HealingPotion("Greater Potion", 20, "Heals 20 hp"),
				new ManaPotion("Phial", 10, "Recovers 10 mp"), 
				new ManaPotion("Large Phial", 20, "Recovers 20 mp")
				));
		player.setInventory(items);
		ArrayList<Character> enemies = new ArrayList<>(List.of(
				new Enemy("Slime", 70, 10), 
				new Enemy("Slime", 70, 10)
				));
		BattleManager bm = new BattleManager(player);
		BattleController bc = new BattleController(bm);
		bm.startBattle(enemies);
		BattleScanner scan = new BattleScanner(bc);
		
		// Source - https://stackoverflow.com/a/6416179
		// Posted by KrzyH, modified by community. See post 'Timeline' for change history
		// Retrieved 2026-05-20, License - CC BY-SA 4.0

		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
		System.setIn(in);

		// do your thing
		
		// optionally, reset System.in to its original
		System.setIn(sysInBackup);
		
		
	}

}
