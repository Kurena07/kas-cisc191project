package domain.rpg.data.traits.classes;

/**
 * Lead Author:
 * @author Kurena Simmons
 * 
 * Date Made: May 1, 2026
 * 
 * Version/Date: May 4, 2026
 *
 * Responsibilities of class: manage the recursion for each character class
 *
 */
/**
 * Class is-a
 * Class is
 */

public class ClassFactory
{
	public static CharacterClass fromType(CharacterClass.Types className)
	{
		switch (className)
		{
			//TODO
			case WARRIOR:
				return new CharacterClass("Warrior", 120, 15, 60);
			case MAGE:
				return new CharacterClass("Mage", 70, 22, 100);
			case ROGUE: 
				return new CharacterClass("Rogue", 90, 11, 75);
			default:
				return null;
		}
	}	
	
}
