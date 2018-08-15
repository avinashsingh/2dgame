/**
 * 
 */
package com.avinash.puzzle.views;

import java.awt.event.KeyEvent;
import java.util.List;

import com.avinash.puzzle.Game;
import com.avinash.puzzle.character.GameCharacter;
import com.avinash.puzzle.character.tools.Weapon;
import com.avinash.puzzle.character.tools.WeaponFactory;
import com.avinash.puzzle.display.Display;

/**
 * @author avinashsingh
 *
 */
public class CreateCharacterPanel extends Panel {
    
    private static final String NAME_MSG = "Please choose a character between a-z or A-Z to represent your character";
    private static final String CHOOSE_WEAPON_MESSAGE = "Please choose your weapons, the more weapons you choose, the lesser experince you get on winning. You can choose a maximum of %d more weapons";
    private static final String ATLEAST_ONE_WEAPON_MESSAGE = "Please choose atleast 1 weapon";
    private static final String NO_WEAPON_EXISTS = "No Weapon with this number exists";
    
    private String tempMsg;
    
    protected int step = 0;
    protected GameCharacter.Builder gameCharacterBuilder;
    protected List<Weapon> allWeapons;

    public CreateCharacterPanel(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
        gameCharacterBuilder = new GameCharacter.Builder();
        allWeapons = WeaponFactory.getAllWeapons();
    }

    @Override
    public void onInput(int keyEvent) {
        switch (step) {
        case 0:
            if (keyEvent < 'A' || keyEvent > 'z') {
                return;
            }
            gameCharacterBuilder.displayChar((char)keyEvent);
            step = 1;
            break;
        case 1:
            // TODO
            tempMsg = null;
            if (keyEvent >= '1' && keyEvent <='9') {
                int index = keyEvent - '1';
                if (allWeapons.size() > index) {
                    gameCharacterBuilder.addWeapon(allWeapons.remove(index));
                    if (allWeapons.size() == 1) {
                        startGame();
                    }
                } else {
                    tempMsg = NO_WEAPON_EXISTS;
                }
            } else if (keyEvent == KeyEvent.VK_ENTER) {
                GameCharacter gameCharacter = gameCharacterBuilder.build();
                if (gameCharacter.getWeapons() == null) {
                    tempMsg = ATLEAST_ONE_WEAPON_MESSAGE;
                } else {
                    startGame();
                }
            }
        }
    }

    

    @Override
    public void drawView(Display display) {
        int x = this.startX;
        int y = this.startY;
        if (step == 0) {
            for (char c: NAME_MSG.toCharArray()) {
                display.draw(x, y, c, null, null);
                x++;
                if (x >= startX + width) {
                    y++;
                }
            }
        } else if (step == 1) {
            for (char c: String.format((CHOOSE_WEAPON_MESSAGE + (tempMsg != null ? tempMsg:"")), allWeapons.size() - 1).toCharArray()) {
                try {
                display.draw(x, y, c, null, null);
                } catch (Exception e) {
                    System.err.println("Error in " + x + ", " + y);
                    System.exit(1);
                }
                x++;
                if (x >= startX + width) {
                    y++;
                    x = this.startX;
                }
            }
            y++;
            x = this.startX;
            
            for (int index = 0; index < allWeapons.size(); index++) {
                String msg = "Press " + (index + 1) + " for   " + allWeapons.get(index).getName();
                for (char c : msg.toCharArray()) {
                    display.draw(x++, y, c, null, null);
                }
                x = this.startX;
                y++;
            }
        }
    }

    
    private void startGame() {
        GameCharacter gameCharacter = gameCharacterBuilder.build();
        Game.getInstance().assignGameCharacter(gameCharacter);
    }
}
