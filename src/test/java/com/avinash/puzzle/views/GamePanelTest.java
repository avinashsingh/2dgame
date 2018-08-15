package com.avinash.puzzle.views;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;

import com.avinash.puzzle.ExperienceChangeListener;
import com.avinash.puzzle.character.GameCharacter;
import com.avinash.puzzle.character.tools.WeaponFactory;
import com.avinash.puzzle.data.IGameGround;

@RunWith(MockitoJUnitRunner.class)
public class GamePanelTest {
    
    private GamePanel gamePanel;
    @Mock
    private IGameGround gameGround;
    @Mock
    private GameCharacter player;
    @Mock
    private ExperienceChangeListener experienceChangeListener;
    @Mock
    private ScorePanel scorePanel;
    
    @Before
    public void beforeTest () {
        gamePanel = new GamePanel(0, 0, 100, 20, gameGround, player, scorePanel, experienceChangeListener);
    }

    @Test
    public void testFight() {
        when(player.getWeapons()).thenReturn(WeaponFactory.getAllWeapons());
        gamePanel.fight(new GameCharacter.Builder().addWeapon(WeaponFactory.getRandomWeapon()).build());
        Mockito.verify(player).setExperience(0);
    }
    
    @Test
    public void testUserLost () {
        when(player.getWeapons()).thenReturn(WeaponFactory.getAllWeapons());
        gamePanel.userLost();
        Mockito.verify(player).setExperience(WeaponFactory.getAllWeapons().size()*-1);
    }
    
    
    @Test
    public void testResumeGame () {
        gamePanel.resumeGame();
        Mockito.verify(experienceChangeListener).onChange(0);
    }

    @Test
    public void testDirectionKeyEventShouldMovePlayer () {
        gameGround = Mockito.spy(IGameGround.class);
        gamePanel.gameGround = gameGround;
        gamePanel.onInput('w');
        Mockito.verify(gameGround, new Times(1)).isMoveAllowed(0, 0, 0, -1);
    }
}
