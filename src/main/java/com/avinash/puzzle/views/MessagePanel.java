/**
 * 
 */
package com.avinash.puzzle.views;

import java.awt.event.KeyEvent;

import com.avinash.puzzle.display.Display;
import com.avinash.puzzle.display.Display.Color;
import com.avinash.puzzle.listener.CommandExecutor;

/**
 * @author avinashsingh
 *
 */
public class MessagePanel extends Panel {

    private String message;
    private CommandExecutor commandExecutor;
    
    public MessagePanel(int startX, int startY, int width, int height, CommandExecutor commandExecutor) {
        super(startX, startY, width, height);
        setBackgroundColor(Color.WHITE);
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void onInput(int keyEvent) {
        if (keyEvent == KeyEvent.VK_ENTER) {
            // close this window
            this.commandExecutor.execute();
        }
    }

    @Override
    public void drawView(Display display) {
        String finalMessage = message + " Press Enter to close this";
        int x = this.startX;
        int y = this.startY;

        for (char c: finalMessage.toCharArray()) {
            try {
            display.draw(x++, y, c, Color.BLACK, Color.WHITE);
            } catch (Exception e) {
                System.err.println("x,y " + x +", " + y);
                System.exit(1);
            }
            if (x >= this.startX + width) {
                x = this.startX;
                y++;
            }
            if (y >= this.startY + height) {
                break;
            }
        }
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
