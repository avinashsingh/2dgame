/**
 * 
 */
package com.avinash.puzzle.display;

import java.util.Arrays;

/**
 * @author avinashsingh
 *
 */
public class CliDisplay implements Display {
    
    private static final String ANSI_CLEAR_SCREEN = "\u001B[2J";
    private static final String ANSI_MOVE_TOP_LEFT = "\u001B[0;0H";
    
    private int displayWidth;
    private int displayHeight;
    char[][] pixelCharacters;
    Color[][] pixelColors;
    Color[][] pixelBackgroundColor;
    
    private int oldHashCode;
    
    
    
    public CliDisplay(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        pixelCharacters = new char[this.displayWidth][this.displayHeight];
        pixelColors = new Color[this.displayWidth][this.displayHeight];
        pixelBackgroundColor = new Color[this.displayWidth][this.displayHeight];
    }

    
    
    @Override
    public void draw(int x, int y, char character, Color color, Color backgroundColor) {
        this.pixelCharacters[x][y] = character;
        this.pixelColors[x][y] = color;
        this.pixelBackgroundColor[x][y] = backgroundColor;

    }

    
    
    @Override
    public void clear() {
        Arrays.stream(pixelCharacters).forEach((ind) -> Arrays.fill(ind, ' '));
        Arrays.stream(pixelColors).forEach((ind) -> Arrays.fill(ind, null));
        Arrays.stream(pixelBackgroundColor).forEach((ind) -> Arrays.fill(ind, null));
    }

    
    
    @Override
    public void complete() {
        int hashCode =  Arrays.deepHashCode(pixelCharacters);
        if (oldHashCode != hashCode) {
            oldHashCode = hashCode;
            System.out.print(ANSI_CLEAR_SCREEN);
            System.out.print(ANSI_MOVE_TOP_LEFT);
    
            for (int x=0; x < displayWidth; x++) {
                System.out.print(getAnsiAppendedtoCharacter(null, Color.BLUE, ' '));
            }
            System.out.println();
            for (int y=0; y < displayHeight; y++) {
                System.out.print(getAnsiAppendedtoCharacter(null, Color.BLUE, ' '));
                for (int x=0; x < displayWidth; x++) {
                    char character = pixelCharacters[x][y];
                    Color color = pixelColors[x][y];
                    Color backgroundColor = pixelBackgroundColor[x][y];
                    
                    System.out.print(getAnsiAppendedtoCharacter(color, backgroundColor, character));
                }
                System.out.print(getAnsiAppendedtoCharacter(null, Color.BLUE, ' '));
                System.out.println();
            }
            for (int x=0; x < displayWidth; x++) {
                System.out.print(getAnsiAppendedtoCharacter(null, Color.BLUE, ' '));
            }
            System.out.println();
        }
        
    }

    
    
    
    
    
    
    
    private String getAnsiAppendedtoCharacter(Color color, Color backgroundColor, char character) {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[");
        if (color == null) {
            sb.append("39");
        } else {
            sb.append(String.valueOf(30 + indexOfColor(color)));
        }
        
        sb.append(";");
        if (backgroundColor == null) {
            sb.append("49m");
        } else {
            sb.append(String.valueOf(40 + indexOfColor(backgroundColor))+"m");
        }
        
        sb.append(character == 0 ? ' ': character);
        return sb.toString();
    }


    
    
    

    private int indexOfColor(Color color) {
        return color.ordinal();
    }

}
