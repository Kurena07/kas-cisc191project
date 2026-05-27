package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.rpg.actions.Action;
import domain.rpg.actions.AttackAction;
import domain.rpg.actions.SkillAction;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.actions.ItemAction;
import domain.rpg.actions.*;
import domain.rpg.data.characters.*;
import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Enemy;
import domain.rpg.data.items.*;
import domain.rpg.data.items.Item;
import domain.rpg.data.traits.classes.CharacterClass;
import domain.rpg.data.traits.classes.CharacterClass.Types;

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

class TestActions
{

	@Test
	void testAttackAction()
	{
		Player p = new Player();
		p.setCharClass(Types.WARRIOR);
		ArrayList<Character> player = new ArrayList<>(List.of(p));
		
		ArrayList<Character> enemyList = new ArrayList<>(List.of(
				new Enemy("a", 100, 10),
				new Enemy("b", 50, 5)
			));
		
		BattleManager m = new BattleManager(p);
		m.startBattle(enemyList);
		assertTrue(m.getCurrentTurn() instanceof Character);
		assertEquals(p, m.getCurrentTurn());
		
		Action a = new AttackAction();
		((AttackAction) a).performAction(p,enemyList.subList(0,1));
		assertEquals(85, enemyList.get(0).getCurrentHP());
		assertTrue(a.isActionComplete());
		
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
		
		assertTrue(m.getCurrentTurn() instanceof Enemy);
		
		a = new AttackAction();
		((AttackAction) a).performAction(enemyList.get(0), player);
		
		assertEquals(110, p.getCurrentHP());
		assertTrue(a.isActionComplete());
		
		
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
	}
	
	@Test 
	void testSkill()
	{
		Player p = new Player();
		p.setCharClass(Types.WARRIOR);
		
		assertFalse(p.getFirstSkill() == null);
		assertFalse(p.getSecondSkill() == null);
		
		ArrayList<Character> enemyList = new ArrayList<>(List.of(
				new Enemy("a", 100, 10),
				new Enemy("b", 50, 5)
			));
		
		p.getFirstSkill().useSkill(p, enemyList.subList(0,1));
		assertTrue(enemyList.getFirst().getCurrentHP() == 74);
		
		p.getSecondSkill().useSkill(p, enemyList);
		assertTrue(enemyList.getFirst().getCurrentHP() == 59);
		assertTrue(enemyList.getLast().getCurrentHP() == 35);
		
	}
	
	@Test
	void testSkillAction()
	{
		Player p = new Player();
		p.setCharClass(Types.WARRIOR);
		ArrayList<Character> player = new ArrayList<>(List.of(p));
		
		ArrayList<Character> enemyList = new ArrayList<>(List.of(
				new Enemy("a", 40, 10),
				new Enemy("b", 15, 5)
			));
		
		BattleManager m = new BattleManager(p);
		m.startBattle(enemyList);
		assertTrue(m.getCurrentTurn() instanceof Character);
		assertEquals(p, m.getCurrentTurn());
		
		//use skill
		Action a = new SkillAction(p.getFirstSkill());
		((SkillAction) a).performAction(p,enemyList.subList(0,1));
		assertEquals(14, enemyList.get(0).getCurrentHP());
		assertTrue(a.isActionComplete());
		
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
		
		assertTrue(m.getCurrentTurn() instanceof Enemy);
		//enemy attacks
		a = new AttackAction();
		((AttackAction) a).performAction(enemyList.get(0), player);
		
		assertEquals(110, p.getCurrentHP());
		
		m.nextTurn();
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
		
		assertTrue(m.getCurrentTurn() instanceof Player);
		
		//use second skill
		a = new SkillAction(p.getSecondSkill());
		((SkillAction)a).performAction(p, enemyList);
		assertTrue(a.isActionComplete());
		assertTrue(enemyList.isEmpty());
		assertTrue(m.hasBattleEnded());
		assertTrue(m.hasPlayerWon());
	}
	
	@Test
	void testItemAction()
	{
		Player p = new Player();
		p.setCharClass(Types.WARRIOR);
		ArrayList<Character> player = new ArrayList<>(List.of(p));
		
		ArrayList<Item> potionList = new ArrayList<>(List.of(
				new HealingPotion("Greater Potion", 20, "Heals 20 hp"),
				new ManaPotion("Phial", 10, "Recovers 10 mp")
				));
		
		assertTrue(potionList.getFirst() instanceof Item);
		p.setInventory(potionList);
		
		ArrayList<Character> enemyList = new ArrayList<>(List.of(
				new Enemy("a", 100, 10),
				new Enemy("b", 50, 5)
			));
		
		BattleManager m = new BattleManager(p);
		m.startBattle(enemyList);
		assertTrue(m.getCurrentTurn() instanceof Character);
		assertEquals(p, m.getCurrentTurn());
		
		Action a = new SkillAction(p.getFirstSkill());
		((SkillAction) a).performAction(p,enemyList.subList(0,1));
		assertEquals(74, enemyList.get(0).getCurrentHP());
		
		
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
		
		assertTrue(m.getCurrentTurn() instanceof Enemy);
		
		a = new AttackAction();
		a.performAction(enemyList.get(0), player);
		
		assertEquals(110, p.getCurrentHP());
		
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
		
		//try using phial
		a = new ItemAction(p.getItem(1), p);
		((ItemAction)a).performAction(p, null);
		assertEquals(60, p.getCurrentMP());
		assertTrue(a.isActionComplete());
		
		//try using potion
		a = new ItemAction(p.getItem(0), p);
		((ItemAction)a).performAction(p, null);
		assertEquals(120, p.getCurrentHP());
		assertTrue(a.isActionComplete());
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
	}

}
