/**
 * 
 */
package com.avinash.puzzle.data;

import java.util.Collection;

import com.avinash.puzzle.character.GameCharacter;

/**
 * @author avinashsingh
 *
 */
public interface IGameGround {

    public boolean addCharacter (GameCharacter gameCharacter, int x, int y);
    
    public void removeCharacter(GameCharacter gameCharacter, int x, int y);
    
    public int[] randomLocation ();
    
    public boolean isMoveAllowed(int fromX, int fromY, int dx, int dy);
    
    public GameCharacter otherCharacterAt(int x, int y);
    
    public Collection<GameCharacter> getAllCharacters ();
    
    public Character[][] getGroundMap ();
    
    public void move(GameCharacter character, int dx, int dy);

}
