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
public class Point extends StaticTarget implements Scorable {

    public Point(Position pos) {
        super(pos);
        this.state = State.AVAILABLE;
    }

    @Override
    public int getScore() {
        return 10;
    }

    @Override
    public void gotEaten() {
        if (this.state == State.AVAILABLE)
            this.changeState(State.EATEN);
        position.remove(this);

    }

    public String toString() {
        return "Point [" + position + ", " + state + ", visible: " + visible + "]";
    }
}
