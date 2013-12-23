/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import controller.MainController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * MapTest
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class MapTest {

    private Map instance;

    @Before
    public void setUp() {
        MainController.reset();

        this.instance = Map.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(Map.getInstance());
    }

    @Test
    public void testReset() {
        Map oldO = Map.getInstance();
        Map.reset();
        Map newO = Map.getInstance();
        assertNotSame(oldO, newO);
    }

    @Test
    public void testGetAmountOfNotBlockedPlaces() {
        // TODO Implement test
    }

    @Test
    public void testGetAmountOfPoints() {
        // TODO Implement test
    }

    @Test
    public void testGetPositionContainer() {
        assertNotNull(instance.getPositionContainer());
    }

    @Test
    public void testGetPositionByDirectionIfMoveableTo() {
        assertNull(Map.getPositionByDirectionIfMoveableTo(Map.getInstance().getPositionContainer().get(0, 0), Direction.WEST));
        assertNotNull(Map.getPositionByDirectionIfMoveableTo(Map.getInstance().getPositionContainer().get(1, 1), Direction.WEST));
    }
}
