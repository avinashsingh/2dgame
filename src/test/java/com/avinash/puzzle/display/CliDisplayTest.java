package com.avinash.puzzle.display;

import static org.junit.Assert.*;

import org.junit.Test;

import com.avinash.puzzle.display.Display.Color;


public class CliDisplayTest {

    @Test
    public void testDraw() {
        CliDisplay display = new CliDisplay(100, 10);
        display.draw(0, 0, 'A', null, null);
        display.draw(1, 0, 'V', null, null);
        display.draw(2, 0, 'I', null, null);
        
        assertEquals("The characters should go in array", display.pixelCharacters[0][0], 'A');
        assertEquals("The characters should go in array", display.pixelCharacters[1][0], 'V');
        assertEquals("The characters should go in array", display.pixelCharacters[2][0], 'I');
        
    }

    @Test
    public void testClear() {
        CliDisplay display = new CliDisplay(100, 10);
        display.draw(0, 0, 'S', Color.BLUE, Color.YELLOW);
        display.draw(1, 0, 'I', Color.GREEN, Color.YELLOW);
        display.draw(2, 0, 'N', Color.RED, Color.YELLOW);
        display.draw(0, 1, 'S', Color.YELLOW, Color.BLUE);
        display.draw(1, 1, 'I', Color.RED, Color.BLUE);
        display.draw(2, 1, 'N', Color.GREEN, Color.BLUE);
        display.clear();
        assertEquals("The characters should be removed from display array", display.pixelCharacters[0][0], ' ');
        assertEquals("The characters should be removed from display array", display.pixelCharacters[1][0], ' ');
        assertEquals("The characters should be removed from display array", display.pixelCharacters[2][0], ' ');
        assertEquals("The characters should be removed from display array", display.pixelCharacters[0][1], ' ');
        assertEquals("The characters should be removed from display array", display.pixelCharacters[1][1], ' ');
        assertEquals("The characters should be removed from display array", display.pixelCharacters[2][1], ' ');
    }

    @Test
    public void testComplete() {
        CliDisplay display = new CliDisplay(100, 10);
        display.draw(0, 0, 'S', Color.BLUE, Color.YELLOW);
        display.draw(1, 0, 'I', Color.GREEN, Color.YELLOW);
        display.draw(2, 0, 'N', Color.RED, Color.YELLOW);
        display.draw(0, 1, 'S', Color.YELLOW, Color.BLUE);
        display.draw(1, 1, 'I', Color.RED, Color.BLUE);
        display.draw(2, 1, 'N', Color.GREEN, Color.BLUE);
        display.complete();
        display.complete();
    }

}
