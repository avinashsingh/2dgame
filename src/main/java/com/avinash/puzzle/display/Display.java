/**
 * 
 */
package com.avinash.puzzle.display;

/**
 * @author avinashsingh
 *
 */
public interface Display {
    
    public void complete();

    public void draw(int x, int y, char character, Color color, Color backgroundColor);

    public void clear();

    public static enum Color {
        BLACK, RED, GREEN, YELLOW, BLUE, PINK, LBLUE, WHITE;
    }
    
}
