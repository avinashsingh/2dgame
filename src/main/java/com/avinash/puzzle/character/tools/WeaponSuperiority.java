/**
 * 
 */
package com.avinash.puzzle.character.tools;

/**
 * Compare 2 power indexes, if difference is greater than 180,
 * add 360 to smaller one
 * find smallest multiple of 90 between them.
 * The weapon with bigger gap wins.
 * Eg1 W1 PI=88, W2 PI=189, smallest multiple 90, W2 wins
 * Eg2 W2 PI=189, W3 PI=235, smallest multiple 180, W3 wins
 * Eg3 W3 PI=235, W4 PI=361, smallest multiple 360, W3 wins 
 * in case of draw, random weapon wins
 * @author avinashsingh
 *
 */
public class WeaponSuperiority {
	
	public WeaponSuperiority () {}
	
	public Weapon findSuperior (Weapon weapon1, Weapon weapon2) {
		int weapon1PowerIndex = weapon1.getPowerIndex();
		int weapon2PowerIndex = weapon2.getPowerIndex();
		if (weapon1PowerIndex == weapon2PowerIndex) {
			return Math.random() > 0.5 ? weapon1 : weapon2;
		}
		if (weapon1PowerIndex - weapon2PowerIndex > 180) {
			while(weapon1PowerIndex - weapon2PowerIndex > 180) {
				weapon2PowerIndex += 360;
			}
		} else if (weapon2PowerIndex - weapon1PowerIndex > 180) {
			while (weapon2PowerIndex - weapon1PowerIndex > 180) {
				weapon1PowerIndex += 360;
			}
		}
		
		int minPowerIndex = Math.min(weapon1PowerIndex, weapon2PowerIndex);
		int multipleOf90 = (int)(Math.ceil(minPowerIndex/90.0)*90);
		
		return Math.abs(weapon1PowerIndex - multipleOf90) > Math.abs(weapon2PowerIndex - multipleOf90) 
				? weapon1 : weapon2;
	}

}
