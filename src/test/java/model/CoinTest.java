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

    @Before
    public void setUp() {
        MainController.reset();

        pos = Map.getInstance().getPositionContainer().get(1, 5);

        instance = new Coin(pos);
    }

    @After
    public void afterTest() {
        instance = null;
        MainController.reset();
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
