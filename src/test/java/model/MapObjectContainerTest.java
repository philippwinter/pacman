/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.Ghost.Blinky;
import model.Ghost.Ghost;
import model.Ghost.Pinky;
import model.Map.Direction;
import model.pacman.Pacman;
import org.junit.Before;
import org.junit.Test;

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
    private Position otherPos;
    private Pacman pac;
    private Ghost ghost;

    @Before
    public void setUp() {
        Position pos = Map.getInstance().getPositionContainer().get(1, 1);
        this.otherPos = Map.getPositionByDirectionIfMovableTo(pos, Direction.EAST);
        this.pac = new Pacman(Pacman.Sex.MALE);
        this.ghost = new Pinky(pos);
        this.instance = pos.getOnPosition();
        pac.move(otherPos);

    }


    @Test
    public void testGet() {
        assertNotNull(instance);
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
        new Pacman(Pacman.Sex.MALE);
    }

    @Test
    public void testIterator() {
        assertNotNull(instance.iterator());
    }

    @Test
    public void testContains() {
        assertTrue(otherPos.getOnPosition().contains(pac));
        assertTrue(instance.contains(ghost));
        assertFalse(instance.contains(new Blinky(otherPos)));
    }
}
