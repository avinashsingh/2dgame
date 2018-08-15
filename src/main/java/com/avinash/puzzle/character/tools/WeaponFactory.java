/**
 * 
 */
package com.avinash.puzzle.character.tools;

import java.util.ArrayList;
import java.util.List;

import com.avinash.puzzle.Utils;

/**
 * @author avinashsingh
 *
 */
public class WeaponFactory {
	private static List<Weapon> weapons;
	
	public static List<Weapon> getAllWeapons() {
		if (weapons == null) {
			weapons = new ArrayList<>();
			weapons.add(Weapon1.getInstance());
			weapons.add(Weapon2.getInstance());
			weapons.add(Weapon3.getInstance());
		}
		return new ArrayList<>(weapons);
	}
	
	public static Weapon getRandomWeapon () {
	    getAllWeapons();
	    return weapons.get(Utils.random(weapons.size()));
	}
}
