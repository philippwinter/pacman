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

    protected Position position;

    public boolean isOnPosition(Position pos) {
        return this.getPosition().equals(pos);
    }

    public Position getPosition() {
        return position;
    }

    protected void setPosition(Position pos) {
        if (this.position != null) {
            this.position.remove(this);
        }
        this.position = pos;
        this.position.add(this);
    }

    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof MapObject) {
                return this.getPosition().equals(((MapObject) o).getPosition());
            }
        }
        return false;
    }


}
