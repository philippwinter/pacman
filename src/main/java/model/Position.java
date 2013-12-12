/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.exception.ObjectAlreadyInListException;

/**
 * The position class represents a point on the map. IT SHOULD NOT BE CONSTRUCTED OUTSIDE THE {@link Map} CLASS.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Position {

    private int x;

    private int y;

    private MapObjectContainer onPosition;

    public Position(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.onPosition = new MapObjectContainer();
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public MapObjectContainer getOnPosition() {
        return onPosition;
    }

    @SuppressWarnings("unused")
    public void add(MapObject mapObject) {
        try {
            this.onPosition.add(mapObject);
        } catch (ObjectAlreadyInListException ex) {
            return;
        }
    }

    @SuppressWarnings("unused")
    public void remove(MapObject mapObject) {
        this.onPosition.remove(mapObject);
    }

    public boolean isMoveableTo() {
        for (MapObject mO : this.onPosition) {
            if (mO instanceof Wall) {
                return false;
            }
        }
        return true;
    }

    public double calcDistance(Position pos) {
        // A little bit of math, using Pythagoras' Theorem
        return Math.sqrt(
                Math.pow(this.getX() - pos.getX(), 2) +
                        Math.pow(this.getY() - pos.getY(), 2)
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            return ((Position) obj).getX() == this.getX() && ((Position) obj).getY() == this.getY();
        }

        return false;
    }

}
