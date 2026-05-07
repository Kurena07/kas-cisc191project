package domain.rpg.data.characters;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
 * 
 * Version/Date: May 4, 2026
 *
 * Responsibilities of class: 
 *
 */
/**
 * Enemy is-a Character
 * Class is
 */

public class Enemy extends Character
{
	/**
	 * 
	 */
	public Enemy(String name, int hp, int att)
	{
		// TODO check
		setName(name);
		setCurrentHP(hp);
		setMaxHP(hp);
		setAttack(att);
	}
	

	
}
