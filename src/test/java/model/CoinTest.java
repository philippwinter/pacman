/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CoinTest
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class CoinTest {

    private Coin instance;
    private Position pos;

    @BeforeClass
    public static void beforeClass() {
        if(!Game.isInitialized()){
            Game.initializeGame();
        }else{
            Game.resetGame();
        }
    }

    @Before
    public void beforeTest() {
        instance = new Coin();
        pos = Map.getInstance().getPositionContainer().get(1, 5);
        instance.setPosition(pos);
    }

    @After
    public void afterTest() {
        instance = null;
        Game.resetGame();
    }

    @Test
    public void testCollide() {
        assertTrue(instance.getState() == StaticTargetState.AVAILABLE);

        Pacman p = new Pacman();
        p.setPosition(pos);
        instance.collide(p);

        assertTrue(instance.getState() == StaticTargetState.EATEN);
    }

    @Test
    public void testChangeState() {
        // TODO Implement test
    }

    @Test
    public void testGetScore() {
        // TODO Implement test
    }
}
