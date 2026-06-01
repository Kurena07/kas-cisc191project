package domain.rpg.combat.view.main;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

import domain.rpg.GameView;
import domain.rpg.combat.manager.BattleManager;
import domain.rpg.data.items.Item;
import domain.rpg.data.traits.skills.Skill;
import domain.rpg.data.characters.Character;
import domain.rpg.data.characters.Enemy;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 27, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

public class BattleView extends CombatPanels
{

	
	/**
	 * @param gameView
	 * @param manage
	 */
	public BattleView(GameView gameView, BattleManager manage)
	{
		super(gameView, manage);
		setUp();
		initializeBattle();
		getEndButton().addActionListener(e ->{
			if (!getBc().getManager().hasPlayerWon())
			{
				System.exit(0);
			}
			
		});
	}
	
	public void initializeBattle()
	{
		for (Item item : getBc().getPlayer().getInventory())
		{
			getItemPanel().addItem(item);			
		}
		for (Skill skill : getBc().getPlayer().getSkills())
		{
			getSkillPanel().addSkill(skill);
		}
		for (Character target : getBc().getEnemies())
		{
			getTargetPanel().addTarget(target);
		}
		for (Character target : getBc().getEnemies())
		{
			addEnemy((Enemy)target);
		}
		updatePlayerName();
		updatePlayerLevel();
		updateUI();
		
	}
	
	public void setUp()
	{
        getSkillPanel().getBackButton().addActionListener(e -> 
        	{
        		getCardLayout().show(getCardPanel(), "actions");
        		getSkillPanel().clearSkillDesc();
        	});
        getSkillPanel().getUseButton().addActionListener(e -> {
        	if (getSkillPanel().getSelectedSkill().isSingleTarget() && getBc().getEnemies().size() > 1)
        	{
        		showTargetPanel();
        	}
        	else
        	{
        		getBc().useSkill(getSkillPanel().getSelectedSkill(), getBc().getPlayer(), getBc().getEnemies());
        		addToTurnText(getBc().getMessage());
        		enemyAction();
        		updateUI();
        		checkBattleEnd();
        	}
        });
        	
        getItemPanel().getBackButton().addActionListener(e -> 
        	{
        		getCardLayout().show(getCardPanel(), "actions");
        		getItemPanel().clearItemDesc();
        	});
        
        getItemPanel().getUseButton().addActionListener(e ->
        	{
        		Item item = getItemPanel().getSelectedItem();
        		getBc().useItem(getItemPanel().getSelectedItem(), getBc().getPlayer());
        		addToTurnText(getBc().getMessage());
        		getItemPanel().removeItem(item);
        		enemyAction();
        		updateUI();
        		checkBattleEnd();
        	});
        
        getTargetPanel().getBackButton().addActionListener(e -> 
		    {
		    	getSelectionArrow().setVisible(false);
		    	if (getSelectedAction().equals("Attack"))
		    	{
		    		getCardLayout().show(getCardPanel(), "actions");
		    	}
		    	else if (getSelectedAction().equals("Skill"))
		    	{
		    		getCardLayout().show(getCardPanel(), "skills");
		    	}
		    	getTargetPanel().clearSelectedTarget();
		    	getSelectionArrow().setVisible(false);
		    });
        
        getTargetPanel().getSelectButton().addActionListener(e -> 
    	{
    		int index = getBc().getEnemies().indexOf(getTargetPanel().getSelectedTarget());
    		ArrayList<Character> temp = new ArrayList<>(getBc().getEnemies().subList(index, index+1));
    		if (getSelectedAction().equals("Attack"))
    		{
    			getBc().useAttack(getBc().getPlayer(), temp);
    			addToTurnText(getBc().getMessage());
    			updateUI();
    			enemyAction();
    		}
    		else if (getSelectedAction().equals("Skill"))
    		{
    			getBc().useSkill(getSkillPanel().getSelectedSkill(), getBc().getPlayer(), temp);
    			addToTurnText(getBc().getMessage());
    			enemyAction();
    		}
    		getSelectionArrow().setVisible(false);
    		updateUI();
    		checkBattleEnd();
    	});  
	}
	
}