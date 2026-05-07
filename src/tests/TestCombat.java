package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.rpg.data.characters.Enemy;
import domain.rpg.data.characters.Player;
import domain.rpg.managers.Action;
import domain.rpg.managers.BattleManager;

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

class TestCombat
{

	@Test
	void test()
	{
		Player p = new Player();
		ArrayList<Enemy> enemyList = (ArrayList<Enemy>)List.of(
				new Enemy("a", 100, 10),
				new Enemy("b", 50, 5)
			);
		
		BattleManager m = new BattleManager(p);
		
		assertTrue(m.getCurrentTurn() instanceof Character);
		assertEquals(p, m.getCurrentTurn());
		
		Action a = new AttackAction(15);
		a.performAction(p,enemyList.get(0));
		assertEquals(85, enemyList.get(0).getCurrentHP());
		
		
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
		
		assertTrue(m.getCurrentTurn() instanceof Enemy);
		
		a = new AttackAction();
		a.performAction(enemyList.get(0), p);
		
		
		
		
		m.nextTurn();
		assertFalse(m.hasBattleEnded());
	}
	
	

}
