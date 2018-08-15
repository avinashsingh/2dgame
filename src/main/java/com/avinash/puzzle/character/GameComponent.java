/**
 * 
 */
package com.avinash.puzzle.character;

import java.io.Serializable;

import com.avinash.puzzle.display.Display.Color;

/**
 * @author avinashsingh
 *
 */
public abstract class GameComponent implements Serializable {
    
	private static final long serialVersionUID = 8775822478155752311L;
	
	protected int x;
    protected int y;
    protected final char c;
    protected final Color color;
    
    protected GameComponent(char c, Color color) {
    	this.c = c;
    	this.color = color;
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getC() {
		return c;
	}

    public Color getColor() {
        return color;
    }
}
