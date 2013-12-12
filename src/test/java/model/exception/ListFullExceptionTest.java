/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.exception;

import model.Pacman;
import model.PacmanContainer;
import model.Position;
import org.junit.Test;

/**
 * ListFullExceptionTest
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
@SuppressWarnings("unused")
public class ListFullExceptionTest {

    @Test(expected = model.exception.ListFullException.class)
    public void testConstruct() {
        PacmanContainer container = new PacmanContainer();

        // Add a new Object to the container until it overflows.
        for (int i = 0; i < container.max + 1; i++) {
            container.add(new Pacman(new Position(0, 0)));
        }

        container = null;
    }
}
