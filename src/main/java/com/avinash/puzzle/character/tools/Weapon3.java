/**
 * 
 */
package com.avinash.puzzle.character.tools;

/**
 * @author avinashsingh
 *
 */
public class Weapon3 implements Weapon {

	public static final Weapon3 WEAPON = new Weapon3();
	
	public static Weapon3 getInstance () {
		return WEAPON;
	}
	
	private Weapon3 () {}
	
	@Override
	public int getPowerIndex() {
		return 290;
	}

	@Override
	public String getName() {
		return "Weapon3";
	}
	
	protected Object readResolve() {
        return WEAPON;
    }

}
