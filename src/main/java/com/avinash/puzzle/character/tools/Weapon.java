/**
 * 
 */
package com.avinash.puzzle.character.tools;

import java.io.Serializable;

/**
 * A weapon can have any positive power index.
 * 
 * @author avinashsingh
 *
 */
public interface Weapon extends Serializable {

	/**
	 * ranges b/w 0 - 359, 
	 * @return
	 */
	public int getPowerIndex();
	
	public String getName();
	
}
