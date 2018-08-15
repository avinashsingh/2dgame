package com.avinash.puzzle.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.avinash.puzzle.Game;

@RunWith(MockitoJUnitRunner.class)
public class CreateCharacterPanelTest {

    private CreateCharacterPanel createCharacterPanel;
    
    @Before
    public void init () {
        createCharacterPanel = new CreateCharacterPanel(0, 0, 100, 20);
    }
    
    @Spy()
    Game game;
    
    @Test
    public void testCharacterCreationConstructor() {
        assertNotNull("GameCharacterBuilder should be available", createCharacterPanel.gameCharacterBuilder);
    }
    
    @Test
    public void testInvalidCharAssignmentToGameCharacterShouldFail () {
        createCharacterPanel.onInput(20);
        createCharacterPanel.onInput(22);
        createCharacterPanel.onInput(24);
        assertEquals("Invalid character assignment should fail", 'P', createCharacterPanel.gameCharacterBuilder.build().getC());
        assertEquals("Control should remain at step 0", 0, createCharacterPanel.step);
    }
    
    @Test
    public void testValidCharAssignmentToGameCharacterShouldProceed () {
        createCharacterPanel.onInput('F');
        assertEquals("Invalid character assignment should fail", 'F', createCharacterPanel.gameCharacterBuilder.build().getC());
        assertEquals("Control should move to step 1", 1, createCharacterPanel.step);
    }
    
    @Test
    public void testValidWeaponSelection () {
        createCharacterPanel.onInput('F');
        int originalWeaponsSize = createCharacterPanel.allWeapons.size();
        createCharacterPanel.onInput('1');
        
        
        assertEquals("Correct weapon index should add weapon for character", 1, createCharacterPanel.gameCharacterBuilder.build().getWeapons().size());
        assertEquals("Weapons count should decrease", originalWeaponsSize - 1, createCharacterPanel.allWeapons.size());
    }

    @Test
    public void testInValidWeaponSelectionShoudlFail () {
        createCharacterPanel.onInput('F');
        createCharacterPanel.onInput(createCharacterPanel.allWeapons.size() + 1);
        
        assertNull("Incorrect weapon index should not add weapon for character", createCharacterPanel.gameCharacterBuilder.build().getWeapons());
    }

}
