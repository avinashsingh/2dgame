package com.avinash.puzzle.character.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeaponFactoryTest {

    @Test
    public void testGetAllWeapons() {
        assertEquals("Weapons factory should return 3 weapons", 3, WeaponFactory.getAllWeapons().size());
    }

    @Test
    public void testGetRandomWeapon() {
        assertNotNull("Random wepon returned should never be null", WeaponFactory.getRandomWeapon());
    }

}
