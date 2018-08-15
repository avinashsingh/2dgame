package com.avinash.puzzle.data;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.avinash.puzzle.character.GameCharacter;

public class GameGroundTest {
    
    GameGround gameGround;
    
    @Before
    public void init() {
        gameGround = new GameGround(100, 20, 10);
    }

    @Test
    public void testGameGroundInit() {
        assertNotNull("GameGround Map should be available", gameGround.map);
        long numberOfBricks = Arrays.stream(gameGround.map).flatMap(Arrays::stream).filter((entry) -> entry != null).count();
        assertTrue("Number of hurdles on map must be greater than 0", numberOfBricks > 0);
    }
    
    @Test
    public void testAddCharacterShouldFailIfHurdleIsPresent() {
        gameGround.map[2][2] = '|';
        boolean characterAdded = gameGround.addCharacter(new GameCharacter.Builder().build(), 2, 2);
        assertFalse("If a hurdle is present character should not be added", characterAdded);
        
        gameGround.map[5][9] = '|';
        characterAdded = gameGround.addCharacter(new GameCharacter.Builder().build(), 5, 9);
        assertFalse("If a hurdle is present character should not be added", characterAdded);
    }
    
    @Test
    public void testAddCharacterShouldFailIfAnotherCharacterIsAlreadyPresent() {
        gameGround.map[2][2] = null;
        boolean characterAdded = gameGround.addCharacter(new GameCharacter.Builder().build(), 2, 2);
        assertTrue("If a hurdle is not present character should be added", characterAdded);
        
        characterAdded = gameGround.addCharacter(new GameCharacter.Builder().build(), 2, 2);
        assertFalse("If an existing character is present, new character should not be added at the same location", characterAdded);
    }
    
    @Test
    public void testAddCharacterShouldBeSuccessIfNoHurdlePresent() {
        gameGround.map[2][2] = null;
        boolean characterAdded = gameGround.addCharacter(new GameCharacter.Builder().build(), 2, 2);
        assertTrue("If a hurdle is not present character should be added", characterAdded);
    }

    @Test
    public void testRemoveCharacterShouldRemoveCharacterFromGround() {
        gameGround.map[2][2] = null;
        GameCharacter gameCharacter = new GameCharacter.Builder().build();
        gameGround.addCharacter(gameCharacter, 2, 2);
        
        gameGround.removeCharacter(gameCharacter, 2, 2);
        
        assertEquals("Game character should be removed from the location", gameGround.coordinatesWiseGameCharacter.size(), 0);
    }

    @Test
    public void testShouldNotAllowMoveIfHurdlePresent() {
        gameGround.map[2][2] = '-';
        boolean moveAllowed = gameGround.isMoveAllowed(2, 1, 0, 1);
        
        assertFalse("Move should not be allowed if hurdle is present", moveAllowed);
    }
    
    @Test
    public void testShouldAllowMoveIfHurdleIsNotPresent() {
        gameGround.map[2][2] = null;
        boolean moveAllowed = gameGround.isMoveAllowed(2, 1, 0, 1);
        
        assertTrue("Move should be allowed if hurdle is not present", moveAllowed);
    }

    @Test
    public void testMove() {
        GameCharacter gameCharacter = new GameCharacter.Builder().build();
        gameGround.move(gameCharacter, 0, 1);
        
        assertEquals("GameCharacter should move", gameCharacter.getY(), 1);
        assertEquals("GameCharacter should move", gameCharacter.getX(), 0);
    }

    
    @Test
    public void testOtherCharacterAt() {
        GameCharacter gameCharacter = new GameCharacter.Builder().build();
        gameGround.map[2][2] = null;
        gameGround.addCharacter(gameCharacter, 2, 2);
        
        GameCharacter otherCharacter = gameGround.otherCharacterAt(2, 2);
        
        assertNotNull("Other Character should be present at given coordinates", otherCharacter);
    }

    @Test
    public void testRandomLocation() {
        int[] randomLocation = gameGround.randomLocation();
        assertEquals("Random Location should be returned", 2, randomLocation.length);
    }

    @Test
    public void testGetAllCharacters() {
        gameGround.map[2][2] = null;
        gameGround.map[3][2] = null;
        
        GameCharacter gameCharacter1 = new GameCharacter.Builder().build();
        gameGround.addCharacter(gameCharacter1, 2, 2);
        GameCharacter gameCharacter2 = new GameCharacter.Builder().build();
        gameGround.addCharacter(gameCharacter2, 3, 2);
        
        assertNotNull("Added characters should be present", gameGround.getAllCharacters());
        assertEquals("All added valid characters must be present", 2, gameGround.getAllCharacters().size());
    }

}
