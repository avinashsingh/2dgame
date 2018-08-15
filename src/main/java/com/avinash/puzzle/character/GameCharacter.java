/**
 * 
 */
package com.avinash.puzzle.character;

import java.util.ArrayList;
import java.util.List;

import com.avinash.puzzle.Drawable;
import com.avinash.puzzle.character.tools.Weapon;
import com.avinash.puzzle.display.Display;
import com.avinash.puzzle.display.Display.Color;

/**
 * @author avinashsingh
 *
 */
public class GameCharacter extends GameComponent implements Drawable {

	private static final long serialVersionUID = -7011109164864777074L;
    
	private String name;
	private int experience;
	private List<Weapon> weapons;
	
    private GameCharacter(char c, Color color, String name, List<Weapon> weapons) {
        super(c, color);
        this.name = name;
        this.weapons = weapons;
    }

    public void move (int dx, int dy) {
    	this.x = this.x + dx;
    	this.y = this.y + dy;
    }

	@Override
	public void draw(Display display) {
		display.draw(x, y, c, Color.BLUE, null);
	}
	
	
    public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getName() {
		return name;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}


	public static class Builder {
    	private String name;
    	private List<Weapon> weapons;
    	private Color color;
    	private char displayChar;
    	
    	public Builder name (String name) {
    		this.name = name;
    		return this;
    	}
    	
    	public Builder addWeapon (Weapon weapon) {
    		if (this.weapons == null) {
    			this.weapons = new ArrayList<>();
    		}
    		this.weapons.add(weapon);
    		return this;
    	}
    	
    	public Builder color (Color color) {
            this.color = color;
            return this;
        }
    	
    	public Builder displayChar (char c) {
            this.displayChar = c;
            return this;
        }
    	
    	public GameCharacter build () {
    		return new GameCharacter(this.displayChar == 0 ? 'P' : this.displayChar, color, name, weapons);
    	}
    }

    
}
