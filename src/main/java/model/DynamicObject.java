/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.Ghost.Ghost;
import model.Map.Direction;
import model.pacman.Pacman;

/**
 * The parent class of all MapObjects that can move by themselves.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 * @see Ghost
 * @see Pacman
 */
@SuppressWarnings("unused")
public abstract class DynamicObject extends MapObject {

    /**
     * The direction the object is heading to, e.g. moving to.
     */
    private Direction headingTo = Direction.NORTH;

    /**
     * Move the object to the new position.
     *
     * @param pos The new position of this object.
     */
    public void move(Position pos) {
        boolean placeholderOnPosition = false;
        for(MapObject m : pos.getOnPosition()){
            if(m instanceof Placeholder){
                placeholderOnPosition = true;
            }
        }
        if(!placeholderOnPosition || this.isHeadingTo(Map.Direction.NORTH)){
            this.setPosition(pos);
        }
    }


    /**
     * Return the direction this object is heading to.
     *
     * @return The direction.
     */
    public Direction getHeadingTo() {
        return headingTo;
    }

    /**
     * Check, if this object is heading to <i>direction</i>.
     * Similar to {@code obj.getHeadingTo() == direction}.
     *
     * @param direction The direction to be checked against.
     *
     * @return <code>True</code> if the position is equal, <code>false</code> if not.
     */
    public boolean isHeadingTo(Direction direction) {
        return this.headingTo == direction;
    }

    /**
     * Change the direction this object is heading to.
     *
     * @param direction The new direction.
     */
    public void setHeadingTo(Direction direction) {
        this.headingTo = direction;
    }


    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof DynamicObject) {
                return this.getHeadingTo().equals(((DynamicObject) o).getHeadingTo())
                        && this.getPosition().equals(((DynamicObject) o).getPosition());
            }
        }
        return false;
    }
}
