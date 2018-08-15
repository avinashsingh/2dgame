/**
 * 
 */
package com.avinash.puzzle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.avinash.puzzle.character.GameCharacter;
import com.avinash.puzzle.character.tools.WeaponFactory;
import com.avinash.puzzle.data.GameGround;
import com.avinash.puzzle.display.CliDisplay;
import com.avinash.puzzle.display.Display;
import com.avinash.puzzle.display.Display.Color;
import com.avinash.puzzle.pojo.MenuItem;
import com.avinash.puzzle.views.CreateCharacterPanel;
import com.avinash.puzzle.views.GamePanel;
import com.avinash.puzzle.views.InstructionsPanel;
import com.avinash.puzzle.views.MenuPanel;
import com.avinash.puzzle.views.Panel;
import com.avinash.puzzle.views.ScorePanel;

/**
 * This class manages the control handover from one panel to another
 * @author avinashsingh
 *
 */
public class Game implements ExperienceChangeListener {
    private static final Game GAME = new Game();
    private static final int Y_OFFSET = 1;
    private static final int DISPLAY_WIDTH = 150;
    private static final int DISPLAY_HEIGHT = 20;
    
    public static Game getInstance () {
        return GAME;
    }
    
    private Game () {}
    
    private boolean running;
    
    private Display display;
    private Panel instructionsPanel;
    private Panel gameStartMenuPanel;
    private Panel createCharacterPanel;
    private Panel gamePanel;
    private Panel scorePanel;

    
    private Panel runningPanel;
    
    private List<ExperienceChangeListener> experienceChangeListeners = new ArrayList<>();
    
    private GameCharacter player;
    
    private GameGround gameGround;
    
    
    public void init () {
        if (running) {
            return;
        }
        try {
            // forward all inputs, without buffering
            Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty -icanon min 1 < /dev/tty"});
            // stop displaying input text
            Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty -echo < /dev/tty"});
        } catch (IOException e) {
        }
        display = new CliDisplay(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        instructionsPanel = new InstructionsPanel(0, 0, DISPLAY_WIDTH, 2, "Use "+Commands.UP_ARROW+" for UP, "+Commands.DOWN_ARROW+" for DOWN, "+Commands.LEFT_ARROW+" for LEFT, "+Commands.RIGHT_ARROW+" for RIGHT");
        prepareGameStartMenuPanel();
        
        setPanel(gameStartMenuPanel);
        running = true;
    }


    public void start () {
        while(running) {
            processKey(runningPanel);
            instructionsPanel.draw(display);
            runningPanel.draw(display);
            Utils.sleep(50L);
            display.complete();
        }
    }
    
    
    
    public void setPanel (Panel panel) {
        this.runningPanel = panel;
    }
    
    public void addExperienceChangeListener (ExperienceChangeListener experienceChangeListener) {
        this.experienceChangeListeners.add(experienceChangeListener);
    }
    
    
    

    public void assignGameCharacter(GameCharacter player) {
        this.player = player;
        setPanel(gamePanel);
        startGame();
    }

    @Override
    public void onChange(int experience) {
        if (experienceChangeListeners != null) {
            for (ExperienceChangeListener changeListener: experienceChangeListeners) {
                changeListener.onChange(experience);
            }
        }
        
        if (experience < 0) {
            exitGame();
        }
    }

    public void saveGame() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./game.game"));
            out.writeObject(gameGround);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }
    
    public void restoreGame() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("./game.game"));
            gameGround = (GameGround) in.readObject();
            this.player = gameGround.getPlayer();
            in.close();
            startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exitGame () {
        running = false;
    }
    
    
    /**
     * Start Game here
     * @param args
     */
    public static void main(String[] args) {
        Game.getInstance().init();
        Game.getInstance().start();
    }
    
    
    
    
    
    
    
    private void processKey(Panel panel) {
        int key = Utils.readKey();
        if (key > 0)
            panel.onInput(key);
    }

    
    private void prepareGameStartMenuPanel() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem newGameMenuItem = new MenuItem("New Game", () -> buildCharacter());
        menuItems.add(newGameMenuItem);
        MenuItem loadSavedGameMenuItem = new MenuItem("Load Saved Game", () -> restoreGame());
        menuItems.add(loadSavedGameMenuItem);
        MenuItem quitMenuItem = new MenuItem("Quit", () -> exitGame());
        menuItems.add(quitMenuItem);
        gameStartMenuPanel = new MenuPanel(DISPLAY_WIDTH/2 - 50, DISPLAY_HEIGHT/2 - 2, 100, 10, menuItems);
    }
    
    private void buildCharacter () {
        createCharacterPanel = new CreateCharacterPanel(0, 2, DISPLAY_WIDTH, DISPLAY_HEIGHT - 2);
        setPanel(createCharacterPanel);
    }
    
    private void startGame () {
        if (gameGround == null) {
            int complexity = (DISPLAY_HEIGHT - 2) * DISPLAY_WIDTH;
            gameGround = new GameGround(DISPLAY_WIDTH, DISPLAY_HEIGHT - 2*Y_OFFSET, (int)(complexity * 0.04));
            // set player position within range
            
            player.setY(Y_OFFSET);
            gameGround.setPlayer(player);
            
            
            // adding random characters
            addEnemies();
            
        }
        
        ScorePanel scorePanel = new ScorePanel(1, DISPLAY_HEIGHT - 1, DISPLAY_WIDTH - 1, 1);
        this.scorePanel = scorePanel;
        scorePanel.onChange(player.getExperience());
        addExperienceChangeListener(scorePanel);
        gamePanel = new GamePanel(0, 1, DISPLAY_WIDTH, DISPLAY_HEIGHT - 2, gameGround, player, scorePanel, this);
        
        
        setPanel(gamePanel);
        
        
    }

    private void addEnemies() {
        for (int enemyCharactersCount = 0; enemyCharactersCount < 20; enemyCharactersCount++) {
            GameCharacter enemyCharacter = new GameCharacter.Builder()
                    .addWeapon(WeaponFactory.getRandomWeapon())
                    .color(Color.RED)
                    .displayChar('E')
                    .build();
            
            int[] randomLocation = gameGround.randomLocation();
            enemyCharacter.setX(randomLocation[0]);
            enemyCharacter.setY(randomLocation[1] + Y_OFFSET);
            
            while(!gameGround.addCharacter(enemyCharacter, enemyCharacter.getX(), enemyCharacter.getY() - Y_OFFSET)) {
                randomLocation = gameGround.randomLocation();
                enemyCharacter.setX(randomLocation[0]);
                enemyCharacter.setY(randomLocation[1] + Y_OFFSET);
            }
        }
    }
}
