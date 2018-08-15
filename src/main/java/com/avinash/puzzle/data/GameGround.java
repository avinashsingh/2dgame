/**
 * 
 */
package com.avinash.puzzle.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.avinash.puzzle.Utils;
import com.avinash.puzzle.character.GameCharacter;

/**
 * @author avinashsingh
 *
 */
public class GameGround implements IGameGround, Serializable {

    private static final long serialVersionUID = -1080812667776470479L;
    
    protected final int width;
    protected final int height;
    protected final int hurdles;
    protected Character[][] map;
    protected Map<Integer, GameCharacter> coordinatesWiseGameCharacter = new HashMap<>();
    protected GameCharacter player;

    
    

    public GameGround(int width, int height, int hurdles) {
        this.width = width;
        this.height = height;
        this.hurdles = hurdles;
        
        assert(hurdles > 0);
        assert(hurdles < width*height -1);
        
        this.map = new Character[width][height];
        
        initHurdles ();
    }

    protected void initHurdles() {
        int hurdles = this.hurdles;
        while(hurdles-- > 0) {
            int ptX = (int)(width * Math.random());
            int ptY = (int)(height * Math.random());
            
            drawHurdle(ptX, ptY, Utils.random(Math.min(this.width - ptX, 10)), Utils.random(Math.min(this.height - ptY, 4)));
        }
    }
    
    private void drawHurdle(int ptX, int ptY, int xwidth, int yheight) {
        double random = Math.random();
        if (random > 0.75) {
            while(xwidth > 0) {
                this.map[ptX][ptY] = '-';
                xwidth--;
                ptX++;
            }
            while(yheight > 0) {
                this.map[ptX][ptY] = '|';
                yheight--;
                ptY++;
            }
        } else if (random > 0.5) {
            while(yheight > 0) {
                this.map[ptX][ptY] = '|';
                yheight--;
                ptY++;
            }
            while(xwidth > 0) {
                this.map[ptX][ptY] = '-';
                xwidth--;
                ptX++;
            }
            
        } else if (random > 0.25) {
            int originalxwidth = xwidth;
            while(xwidth > 0) {
                this.map[ptX][ptY] = '-';
                xwidth--;
                ptX++;
            }
            ptX -= originalxwidth;
            while(yheight > 0) {
                this.map[ptX][ptY] = '|';
                yheight--;
                ptY++;
            }
        } else {
            int originalyheight = yheight;
            while(yheight > 0) {
                this.map[ptX][ptY] = '|';
                yheight--;
                ptY++;
            }
            ptY -= originalyheight;
            while(xwidth > 0) {
                this.map[ptX][ptY] = '-';
                xwidth--;
                ptX++;
            }
        }
    }

    @Override
    public boolean addCharacter (GameCharacter gameCharacter, int x, int y) {
        if (map[x][y] != null) {
            // hurdle found
            return false;
        }
        int pixelIndex = y*this.width + x;
        if (coordinatesWiseGameCharacter.containsKey(pixelIndex)) {
            return false;
        }
        
        coordinatesWiseGameCharacter.put(pixelIndex, gameCharacter);
        return true;
    }
    
    
    @Override
    public void removeCharacter (GameCharacter gameCharacter, int x, int y) {
        int pixelIndex = y*this.width + x;
        coordinatesWiseGameCharacter.remove(pixelIndex);
    }

    
    
    
    @Override
    public boolean isMoveAllowed(int fromX, int fromY, int dx, int dy) {
        int newX = fromX + dx;
        int newY = fromY + dy;
        if (newX >= width || newX < 0 || newY >= height || newY < 0) {
            return false;
        }
        return map[newX][newY] == null; // move allowed even if another fighter is there
    }

    
    
    
    @Override
    public void move(GameCharacter gameCharacter, int dx, int dy) {
        gameCharacter.setX(gameCharacter.getX() + dx);
        gameCharacter.setY(gameCharacter.getY() + dy);
    }

    
    
    @Override
    public GameCharacter otherCharacterAt(int x, int y) {
        if (x > width || x < 0 || y > height || y < 0) {
            return null;
        }
        return coordinatesWiseGameCharacter.get(y * this.width + x);
    }

    
    @Override
    public int[] randomLocation() {
        return new int[]{Utils.random(width), Utils.random(height)};
    }

    
    @Override
    public Collection<GameCharacter> getAllCharacters() {
        return coordinatesWiseGameCharacter.values();
    }

    
    @Override
    public Character[][] getGroundMap() {
        return map;
    }

    public GameCharacter getPlayer() {
        return player;
    }

    public void setPlayer(GameCharacter player) {
        this.player = player;
    }
}
