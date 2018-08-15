/**
 * 
 */
package com.avinash.puzzle.views;

import java.awt.event.KeyEvent;
import java.util.List;

import com.avinash.puzzle.Commands;
import com.avinash.puzzle.display.Display;
import com.avinash.puzzle.pojo.MenuItem;

/**
 * @author avinashsingh
 *
 */
public class MenuPanel extends Panel {
	
    private static final String SELECTED_MENU_PREFIX = "-> ";
	private List<MenuItem> menuItems;
	private int currentIndex;
	

	public MenuPanel(int startX, int startY, int width, int height, List<MenuItem> menuItems) {
		super(startX, startY, width, height);
		this.menuItems = menuItems;
	}

	@Override
	public void drawView(Display display) {
		
		// draw me
		int startX = this.startX;
		int startY = this.startY;
		
		
		for (int menuIndex = 0; menuIndex < menuItems.size(); menuIndex++) {
		    MenuItem menuItem = menuItems.get(menuIndex);
		    if (currentIndex == menuIndex) {
		        // add current item prefix
		        for (char c : SELECTED_MENU_PREFIX.toCharArray()) {
		            display.draw(startX, startY, c, null, null);
		            startX++;
		        }
		    } else {
		        for (char c : SELECTED_MENU_PREFIX.toCharArray()) {
                    display.draw(startX, startY, ' ', null, null);
                    startX++;
                }
		    }
		        
	        for (char c : menuItem.getName().toCharArray()) {
	            display.draw(startX, startY, c, null, null);
	            if (++startX > this.startX + width) {
	                break;
	            }
	        }
		    
		    startX = this.startX;
		    startY++;
		}
	}

	@Override
	public void onInput(int keyEvent) {
		if(keyEvent == Commands.DOWN_ARROW) {
		    // go down in menu
		    currentIndex = (currentIndex + 1) % menuItems.size();
		} else if (keyEvent == Commands.UP_ARROW) {
		    // go up in menu
		    currentIndex = (menuItems.size() + currentIndex - 1) % menuItems.size();
		} else if (keyEvent == KeyEvent.VK_ENTER) {
		    // select the item
		    menuItems.get(currentIndex).getCommandExecutor().execute();
		    // reset pointer
		    currentIndex = 0;
		}
	}

}
