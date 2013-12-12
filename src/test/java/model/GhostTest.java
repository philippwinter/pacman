/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import controller.MainController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GhostTest
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class GhostTest {

    private Ghost instance;
    private Position pos;

    @Before
    public void setUp() {
        MainController.reset();
        this.pos = Map.getInstance().getPositionContainer().get(0, 0);
        this.instance = new Ghost(pos, Colour.WHITE);
    }

    @After
    public void tearDown() {
        MainController.reset();
    }

    @Test
    public void testGetColour() {
        assertNotNull(instance.getColour());
    }

    @Test
    public void testSetColour() {
        instance.setColour(Colour.GREEN);
        assertSame(Colour.GREEN, instance.getColour());
    }

    @Test
    public void testSetAndGetName() {
        instance.setName("DudeThisNameIsAwful");
        assertEquals("DudeThisNameIsAwful", instance.getName());
    }

    @Test
    public void testEat() {
        Pacman p = new Pacman(pos);

        assertSame(DynamicTargetState.HUNTER, instance.getState());
        assertSame(DynamicTargetState.HUNTED, p.getState());
        instance.eat(p);
        assertSame(DynamicTargetState.HUNTER, instance.getState());
        assertSame(DynamicTargetState.MUNCHED, p.getState());
    }

    @Test
    public void testChangeState() {

    }

    @Test
    public void testSetWaitingSeconds() {

    }

    @Test
    public void testGetWaitingSeconds() {

    }

    @Test
    public void testGetMovedInLastTurn() {

    }

    @Test
    public void testSetMovedInLastTurn() {

    }

    @Test
    public void testGetScore() {

    }

    @Test
    public void testGotEaten() {

    }

    @Test
    public void testReduceWaitingSeconds() {

    }
}
