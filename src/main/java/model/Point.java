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

    private static int activePointSeconds = 0;

    public static void resetActivePointSeconds() {
        Point.activePointSeconds = 0;
    }

    public Point(Position pos) {
        this.setPosition(pos);
    }

    @Override
    public void changeState(StaticTargetState s) {
        if (s == StaticTargetState.EATEN) {
            this.gotEaten();
        }

        this.state = s;
    }

    @Override
    public int getScore() {
        return 10;
    }

    @Override
    public void gotEaten() {
        Point.activePointSeconds += 4;
    }
}
