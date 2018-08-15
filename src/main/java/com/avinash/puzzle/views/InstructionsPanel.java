/**
 * 
 */
package com.avinash.puzzle.views;

import com.avinash.puzzle.display.Display;

/**
 * @author avinashsingh
 *
 */
public class InstructionsPanel extends Panel {

    private String instructions;
    
    public InstructionsPanel(int startX, int startY, int width, int height, String instructions) {
        super(startX, startY, width, height);
        this.instructions = instructions;
    }

    @Override
    public void onInput(int keyEvent) {
    }
    

    @Override
    public void drawView(Display display) {
        int startX = this.startX;
        int startY = this.startY;
        for (char c: instructions.toCharArray()) {
            if (c=='\n') {
                startY++;
                startX = this.startX;
            }
            display.draw(startX, startY, c, null, null);
            startX++;
        }
    }
}
