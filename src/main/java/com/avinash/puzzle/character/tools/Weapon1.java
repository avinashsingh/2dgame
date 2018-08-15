/**
 * 
 */
package com.avinash.puzzle.character.tools;

/**
 * @author avinashsingh
 *
 */
public class Weapon1 implements Weapon {

	public static final Weapon1 WEAPON = new Weapon1();
	
	public static Weapon1 getInstance () {
		return WEAPON;
	}
	
	private Weapon1 () {}
	
	@Override
	public int getPowerIndex() {
		return 89;
	}

	@Override
	public String getName() {
		return "Weapon1";
	}

	protected Object readResolve() {
        return WEAPON;
    }
}
