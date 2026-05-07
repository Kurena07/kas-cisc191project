package domain.rpg.views;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Version/Date: May 1, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Class is-a
 * Class is
 */

public class BattleView extends JFrame 
{
	private JButton[] button = new JButton[3];
	
	/**
	 * 
	 */
	public BattleView()
	{
		setLayout(new GridLayout(1,3));
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button[0] = new JButton("Attack");
//		button[0].addActionListener(this);
		button[1] = new JButton("Skill");
//		button[1].addActionListener(this);
		button[2] = new JButton("Item");
//		button[2].addActionListener(this);
		
		add(button[0]);
		add(button[1]);
		add(button[2]);
		
		setVisible(true);
		
		
	}

	
}
