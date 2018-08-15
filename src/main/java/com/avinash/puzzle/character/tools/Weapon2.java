/**
 * 
 */
package com.avinash.puzzle.character.tools;

/**
 * @author avinashsingh
 *
 */
public class Weapon2 implements Weapon {

	public static final Weapon2 WEAPON = new Weapon2();
	
	public static Weapon2 getInstance () {
		return WEAPON;
	}
	
	private Weapon2 () {}
	
	@Override
	public int getPowerIndex() {
		return 170;
	}

	@Override
	public String getName() {
		return "Weapon2";
	}

	protected Object readResolve() {
        return WEAPON;
    }
}
