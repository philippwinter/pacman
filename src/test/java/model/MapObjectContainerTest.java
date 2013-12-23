/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * MapObjectContainerTest
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class MapObjectContainerTest {

    private MapObjectContainer instance;
    private Position pos;
    private Position otherPos;
    private Pacman pac;
    private Ghost ghost;

    @Before
    public void setUp() {
        this.pos = Map.getInstance().getPositionContainer().get(1,1);
        this.otherPos = Map.getPositionByDirectionIfMoveableTo(pos, Direction.WEST);
        this.pac = new Pacman(pos, "Mr. Pacman");
        this.ghost = new Ghost(pos, "Blinky", Colour.PINK);
        this.instance = pos.getOnPosition();
    }


    @Test
    public void testGet() {
        assertNotNull(instance.get(0));
    }

    @Test
    public void testGetAll() {
        assertNotNull(instance.getAll());
    }

    @Test
    public void testRemove() {
        pac.move(otherPos);
        assertFalse(instance.contains(pac));
    }

    @Test
    public void testAdd() {
        new Pacman(pos, "Mrs. Pacman");
    }

    @Test
    public void testIterator() {
        assertNotNull(instance.iterator());
    }

    @Test
    public void testContains() {
        assertTrue(instance.contains(pac));
        assertTrue(instance.contains(ghost));
        assertFalse(instance.contains(new Ghost(otherPos, "Geeky", Colour.WHITE)));
    }
}
