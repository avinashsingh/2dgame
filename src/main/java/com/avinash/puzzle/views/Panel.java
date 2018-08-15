/**
 * 
 */
package com.avinash.puzzle.views;

import com.avinash.puzzle.Drawable;
import com.avinash.puzzle.display.Display;
import com.avinash.puzzle.display.Display.Color;
import com.avinash.puzzle.listener.UserInputListener;

/**
 * @author avinashsingh
 *
 */
public abstract class Panel implements Drawable, UserInputListener {
	
	protected final int startX;
	protected final int startY;
	protected final int width;
	protected final int height;
	
	private Color backgroundColor;
	
	public Panel(int startX, int startY, int width, int height) {
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Display display) {
		
		// clear the panel area for new input and avoid overwriting
		for (int yIndex = startY; yIndex < startY + height; yIndex++) {
			for (int xIndex = startX; xIndex < startX + width; xIndex++) {
				display.draw(xIndex, yIndex, ' ', null, backgroundColor);
			}
		}
		
		drawView(display);
	}
	
	protected void setBackgroundColor (Color color) {
	    backgroundColor = color;
	}
	
	public abstract void drawView (Display display);
	
}
