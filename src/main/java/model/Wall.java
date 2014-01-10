/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

/**
 * A wall is limiting the area an {@link Target} can move to.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
@SuppressWarnings("unused")
public class Wall extends MapObject {

    private Type type;

    public Wall(Position position, Type type) {
        this.type = type;
        this.setPosition(position);
    }

    public Type getType() {
        return type;
    }

    public enum Type {

        /**
         * &#9474;
         */
        STRAIGHT_LEFT,

        /**
         * &#9150;
         */
        LEFT_TOP,

        /**
         * &#9151;
         */
        LEFT_BOT,

        /**
         * &#9474;
         */
        STRAIGHT_RIGHT,

        /**
         * &#9163;
         */
        RIGHT_TOP,

        /**
         * &#9164;
         */
        RIGHT_BOT,

        /**
         * &#9473;
         */
        LAYING_TOP,

        /**
         * &#9473;
         */
        LAYING_BOT,

        SQUARE

    }

    public String toString() {
        return "Wall [" + position + ", " + type + "]";
    }

}
