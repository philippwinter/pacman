/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
@SuppressWarnings("unused")
public abstract class MapObject {

    private Position position;

    public abstract void collide(MapObject obj);

    public boolean isOnPosition(Position pos) {
        // TODO Implement method

        return false;
    }

    public Position getPosition() {
        return position;
    }

    protected void setPosition(Position pos){
        if(this.position != null){
            this.position.remove(this);
        }
        this.position = pos;
        this.position.add(this);
    }

}
