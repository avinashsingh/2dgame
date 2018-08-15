/**
 * 
 */
package com.avinash.puzzle.views;

import com.avinash.puzzle.ExperienceChangeListener;
import com.avinash.puzzle.display.Display;
import com.avinash.puzzle.display.Display.Color;

/**
 * @author avinashsingh
 *
 */
public class ScorePanel extends Panel implements ExperienceChangeListener {
    
    private int score;

    public ScorePanel(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void onInput(int keyEvent) {
    }

    @Override
    public void drawView(Display display) {
        int x = this.startX;
        int y = this.startY;
        
        for (char c: (" Experience: " + score + " ").toCharArray()) {
            display.draw(x++, y, c, Color.BLACK, Color.WHITE);
        }
        for (char c: (" Press 'r' to save the game, 'q' to quit the game").toCharArray()) {
            display.draw(x++, y, c, null, null);
        }
        
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void onChange(int experience) {
        this.score = experience;
    }

    
}
