/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

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
public abstract class DynamicTarget extends Target {

    /**
     * The direction the object is heading to, e.g. moving to.
     */
    private Direction headingTo;

    /**
     * Move the object to the new position.
     *
     * @param pos The new position of this object.
     */
    public void move(Position pos) {
        if(this.getPosition().equals(pos)){
            throw new IllegalArgumentException("Cannot move an object to its current place.");
        }else{
            if(this.getPosition().calcDistance(pos) > 1){
                throw new IllegalArgumentException("Cannot move an object further than 1 unit at once.");
            }else{
                this.setPosition(pos);
                // TODO Probably do other work here, like removing this from the old positionContainer and adding to the new
            }
        }

    }

    /**
     * Let the object eat a subclass of Target.
     *
     * @param target The object to be eaten.
     */
    public void eat(Target target) {
        // TODO: Implement method

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

    /**
     * Change the state and perform necessary actions in order to do this.
     *
     * @param state The new state.
     */
    public abstract void changeState(DynamicTargetState state);
}
