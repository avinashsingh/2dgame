/**
 * 
 */
package com.avinash.puzzle.views;

import com.avinash.puzzle.Commands;
import com.avinash.puzzle.ExperienceChangeListener;
import com.avinash.puzzle.Game;
import com.avinash.puzzle.character.GameCharacter;
import com.avinash.puzzle.character.tools.Weapon;
import com.avinash.puzzle.character.tools.WeaponFactory;
import com.avinash.puzzle.character.tools.WeaponSuperiority;
import com.avinash.puzzle.data.IGameGround;
import com.avinash.puzzle.display.Display;
import com.avinash.puzzle.display.Display.Color;

/**
 * @author avinashsingh
 *
 */
public class GamePanel extends Panel {

    protected IGameGround gameGround;
    protected GameCharacter player;
    private Panel scorePanel;
    protected MessagePanel messagePanel;
    private ExperienceChangeListener experienceChangeListener;

    public GamePanel(int startX, int startY, int width, int height, IGameGround gameGround, GameCharacter player, Panel scorePanel, ExperienceChangeListener experienceChangeListener) {
        super(startX, startY, width, height);
        this.gameGround = gameGround;
        this.player = player;
        this.experienceChangeListener = experienceChangeListener;
        this.scorePanel = scorePanel;
        this.messagePanel = new MessagePanel(startX + width/2 - 50, startY + height/2 - 3, 100, 6, () -> resumeGame());
    }

    @Override
    public void onInput(int keyEvent) {
        if (messagePanel.getMessage() != null) {
            messagePanel.onInput(keyEvent);
            return;
        }
        int dx = 0, dy = 0;
        switch (keyEvent) {
        case Commands.UP_ARROW:
            dy = -1;
            break;
        case Commands.DOWN_ARROW:
            dy = 1;
            break;
        case Commands.LEFT_ARROW:
            dx = -1;
            break;
        case Commands.RIGHT_ARROW:
            dx = 1;
            break;
        case Commands.SAVE_BTN:
            messagePanel.setMessage("Game Saved!!");
            Game.getInstance().saveGame();
            break;
        case Commands.QUIT_BTN:
            Game.getInstance().exitGame();
            break;
        }
        
        if (dx==0 && dy==0) {
            return;
        }
        
        movePlayer (dx, dy);
    }

    
    

    @Override
    public void drawView(Display display) {
        int x = this.startX;
        int y = this.startY;
        
        for (Character[] row: gameGround.getGroundMap()) {
            for (Character cell: row) {
                display.draw(x, y, cell == null ? ' ' : cell, null, null);
                y++;
            }
            x++;
            y = this.startY;
        }
        
        for(GameCharacter gameCharacter: gameGround.getAllCharacters()) {
            display.draw(gameCharacter.getX(), gameCharacter.getY(), gameCharacter.getC(), gameCharacter.getColor(), null);
        }
        
        display.draw(player.getX(), player.getY(), player.getC(), Color.GREEN, Color.WHITE);
        
        scorePanel.draw(display);
        
        if (messagePanel.getMessage() != null) {
            messagePanel.draw(display);
        }
        
    }

    
    
    private void movePlayer(int dx, int dy) {
        boolean moveAllowed = gameGround.isMoveAllowed(player.getX(), player.getY() - this.startY, dx, dy);
        
        if (moveAllowed) {
            gameGround.move(player, dx, dy);
        
            GameCharacter enemyCharacter = gameGround.otherCharacterAt(player.getX() - this.startX, player.getY() - this.startY);
            if (enemyCharacter != null) {
                gameGround.removeCharacter(enemyCharacter, player.getX() - this.startX, player.getY() - this.startY);
                fight(enemyCharacter);
            }
        }
    }

    protected void fight(GameCharacter enemyCharacter) {
        boolean won = false;
        
        // if any weapon with player is better than any weapon with enemy, player wins
        for (Weapon playerWeapon : player.getWeapons()) {
            for (Weapon enemyWeapon: enemyCharacter.getWeapons()) {
                Weapon superior = WeaponSuperiority.getInstance().findSuperior(enemyWeapon, playerWeapon);
                if (superior == playerWeapon) {
                    won = true;
                    break;
                }
            }
        }
        
        if (won) {
            userWon();
        } else {
            userLost();
        }
    }
    
    protected void resumeGame () {
        messagePanel.setMessage(null);
        experienceChangeListener.onChange(player.getExperience());
    }
    
    protected void userWon () {
        messagePanel.setMessage("Your weapon is stronger than Enemy. You Won!!");
        int gainInExperience = WeaponFactory.getAllWeapons().size() - player.getWeapons().size();
        player.setExperience(player.getExperience() + gainInExperience);
    }
    
    protected void userLost () {
        messagePanel.setMessage("Despite all your effort, you lost!!");
        int lossInExperience = player.getWeapons().size();
        player.setExperience(player.getExperience() - lossInExperience);
        
    }
}
