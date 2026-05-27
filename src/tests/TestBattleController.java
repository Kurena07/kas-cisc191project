package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.rpg.combat.controller.BattleController;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.combat.view.BattleScanner;
import domain.rpg.data.characters.Boss;
import domain.rpg.data.characters.Enemy;
import domain.rpg.data.characters.Player;
import domain.rpg.data.items.HealingPotion;
import domain.rpg.data.items.Item;
import domain.rpg.data.items.ManaPotion;
import domain.rpg.data.traits.classes.CharacterClass.Types;
import domain.rpg.data.traits.skills.*;
import domain.rpg.data.characters.Character;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 13, 2026
 *
 * Responsibilities of class: Test the battle controller
 *
 */

class TestBattleController
{

	@Test
	void testSetup()
	{
		Player p = new Player();
		BattleManager bm = new BattleManager(p);
		BattleController c = new BattleController(bm);
		
		//set up player
		p.setCharClass(Types.WARRIOR);
		ArrayList<Item> items = new ArrayList<>(List.of(
				new HealingPotion("Potion", 10, "Heals 10 hp"),
				new HealingPotion("Greater Potion", 20, "Heals 20 hp"),
				new ManaPotion("Phial", 10, "Recovers 10 mp"), 
				new ManaPotion("Large Phial", 20, "Recovers 20 mp")
				));
		p.setInventory(items);
		assertEquals(items, p.getInventory());
		
		//Single enemy
		ArrayList<Character> enemy = new ArrayList<>(List.of(
				new Enemy("Goblin", 100, 10)
				));
		assertEquals(1, enemy.size());
		
		//two enemies
		ArrayList<Character> enemies = new ArrayList<>(List.of(
				new Enemy("Slime", 70, 10), 
				new Enemy("Slime", 70, 10)
				));
		assertEquals(2, enemies.size());
		
		//check stats for single enemy battle
		bm.startBattle(enemy);
		assertEquals(p.getMaxHP(), p.getCurrentHP());
		assertEquals(60, p.getCurrentMP());
		assertEquals(100, enemy.getFirst().getCurrentHP());
		
		//check that stats and turn queue reset with new battle
		bm.startBattle(enemies);
		assertEquals(p.getMaxHP(), p.getCurrentHP());
		assertEquals(60, p.getCurrentMP());
		for (Character e : enemies)
		{
			assertEquals(e.getMaxHP(), e.getCurrentHP());
		}
		assertTrue(bm.getCurrentTurn() instanceof Player);
	}
	
	@Test
	void testUseAttack()
	{
		Player p = new Player();
		BattleManager bm = new BattleManager(p);
		BattleController c = new BattleController(bm);
		
		//set up player
		p.setCharClass(Types.WARRIOR);
		ArrayList<Item> items = new ArrayList<>(List.of(
				new HealingPotion("Potion", 10, "Heals 10 hp"),
				new HealingPotion("Greater Potion", 20, "Heals 20 hp"),
				new ManaPotion("Phial", 10, "Recovers 10 mp"), 
				new ManaPotion("Large Phial", 20, "Recovers 20 mp")
				));
		p.setInventory(items);
		assertEquals(items, p.getInventory());
		
		//Single enemy
		ArrayList<Character> enemy = new ArrayList<>(List.of(
				new Enemy("Goblin", 100, 10)
				));
		assertEquals(1, enemy.size());
		
		//two enemies
		ArrayList<Character> enemies = new ArrayList<>(List.of(
				new Enemy("Slime", 70, 10), 
				new Enemy("Slime", 70, 10)
				));
		assertEquals(2, enemies.size());
		
		//test attack with single enemy battle
		bm.startBattle(enemy);
		
		//first turn is player, use attack
		assertTrue(bm.getCurrentTurn() instanceof Player);
		c.useAttack(p, enemy);
		
		//first turn is enemy, use attack
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		c.useAttack(enemy.getFirst(), bm.getPlayerArray());
		
		//first turn is player, lost hp
		assertTrue(bm.getCurrentTurn() instanceof Player);
		assertEquals(110, p.getCurrentHP());
		
		//test attack with multi enemy battle
		bm.startBattle(enemies);
		
		//first turn is player, use attack
		assertTrue(bm.getCurrentTurn() instanceof Player);
		c.useAttack(p, enemy);
		
		Character current = bm.getCurrentTurn();
		
		//first turn is enemy, use attack, player hp lowered
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		c.useAttack(current, bm.getPlayerArray());
		assertEquals(110, p.getCurrentHP());
		
		//first turn is enemy, use attack, payer hp is lowered
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		c.useAttack(current, bm.getPlayerArray());
		assertEquals(100, p.getCurrentHP());
		
		//first turn is player
		assertTrue(bm.getCurrentTurn() instanceof Player);
	}
	
	@Test
	void testUseSkill()
	{
		Player p = new Player();
		BattleManager bm = new BattleManager(p);
		BattleController c = new BattleController(bm);
		
		//set up player
		p.setCharClass(Types.WARRIOR);
		ArrayList<Item> items = new ArrayList<>(List.of(
				new HealingPotion("Potion", 10, "Heals 10 hp"),
				new HealingPotion("Greater Potion", 20, "Heals 20 hp"),
				new ManaPotion("Phial", 10, "Recovers 10 mp"), 
				new ManaPotion("Large Phial", 20, "Recovers 20 mp")
				));
		p.setInventory(items);
		assertEquals(items, p.getInventory());
		
		//Single enemy
		ArrayList<Character> enemy = new ArrayList<>(List.of(
				new Enemy("Goblin", 100, 10)
				));
		assertEquals(1, enemy.size());
		
		//two enemies
		ArrayList<Character> enemies = new ArrayList<>(List.of(
				new Enemy("Slime", 70, 10), 
				new Enemy("Slime", 70, 10)
				));
		assertEquals(2, enemies.size());
		
		//test skill with single enemy battle
		bm.startBattle(enemy);

		//first turn is player, use skill
		assertTrue(bm.getCurrentTurn() instanceof Player);
		c.useSkill(p.getFirstSkill(), p, enemy);
		//enemy lost 26 hp
		assertEquals(74, enemy.getFirst().getCurrentHP());
		
		//first turn is enemy, use attack
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		c.useAttack(enemy.getFirst(), bm.getPlayerArray());
		
		//first turn is player, lost hp
		assertTrue(bm.getCurrentTurn() instanceof Player);
		assertEquals(110, p.getCurrentHP());
		
		//test skill with multi enemy battle
		bm.startBattle(enemies);
		
		//first turn is player, use skill
		assertTrue(bm.getCurrentTurn() instanceof Player);
		c.useSkill(p.getSecondSkill(), p, enemies);
		//enemies lost hp
		assertEquals(55, enemies.getFirst().getCurrentHP());
		assertEquals(55, enemies.getLast().getCurrentHP());	
	}
	
	@Test
	void testUseItem()
	{
		Player p = new Player();
		BattleManager bm = new BattleManager(p);
		BattleController c = new BattleController(bm);
		
		//set up player
		p.setCharClass(Types.WARRIOR);
		ArrayList<Item> items = new ArrayList<>(List.of(
				new HealingPotion("Potion", 10, "Heals 10 hp"),
				new HealingPotion("Greater Potion", 20, "Heals 20 hp"),
				new ManaPotion("Phial", 10, "Recovers 10 mp"), 
				new ManaPotion("Large Phial", 20, "Recovers 20 mp")
				));
		p.setInventory(items);
		assertEquals(items, p.getInventory());
		
		//Single enemy
		ArrayList<Character> enemy = new ArrayList<>(List.of(
				new Enemy("Goblin", 100, 10)
				));
		assertEquals(1, enemy.size());
		
		//two enemies
		ArrayList<Character> enemies = new ArrayList<>(List.of(
				new Enemy("Slime", 70, 10), 
				new Enemy("Slime", 70, 10)
				));
		assertEquals(2, enemies.size());
		
		//test skill with single enemy battle
		bm.startBattle(enemy);

		//first turn is player, use skill
		assertTrue(bm.getCurrentTurn() instanceof Player);
		c.useSkill(p.getFirstSkill(), p, enemy);
		//enemy lost 26 hp
		assertEquals(74, enemy.getFirst().getCurrentHP());
		
		//first turn is enemy, use attack
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		c.useAttack(enemy.getFirst(), bm.getPlayerArray());
		
		//first turn is player, lost hp
		assertTrue(bm.getCurrentTurn() instanceof Player);
		assertEquals(110, p.getCurrentHP());
		
		assertEquals(4, p.getInventory().size());
		c.useItem(p.getItem(0), p);
		assertEquals(120, p.getCurrentHP());
		assertEquals(3, p.getInventory().size());
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		
		//test skill with multi enemy battle
		bm.startBattle(enemies);
		
		//first turn is player, use skill
		assertTrue(bm.getCurrentTurn() instanceof Player);
		c.useSkill(p.getSecondSkill(), p, enemies);
		//enemies lost hp
		assertEquals(55, enemies.getFirst().getCurrentHP());
		assertEquals(55, enemies.getLast().getCurrentHP());	
		
		Character current = bm.getCurrentTurn();
		
		//first turn is enemy, use attack, player hp lowered
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		c.useAttack(current, bm.getPlayerArray());
		assertEquals(110, p.getCurrentHP());
		
		//first turn is enemy, use attack, payer hp is lowered
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
		c.useAttack(current, bm.getPlayerArray());
		assertEquals(100, p.getCurrentHP());
		
		assertTrue(bm.getCurrentTurn() instanceof Player);
		assertEquals(3, p.getInventory().size());
		c.useItem(p.getInventory().get(1), p);
		assertEquals(60, p.getCurrentMP());
		assertEquals(2, p.getInventory().size());
		assertTrue(bm.getCurrentTurn() instanceof Enemy);
	}
}
