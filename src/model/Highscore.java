/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.exception.IntegerNotPositiveException;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Highscore {

    private long score = 0;

    private Pacman applicableObject;

    public Highscore(Pacman pacMan) {
        this.applicableObject = (pacMan);
    }

    public long getScore() {
        return score;
    }

    public void addPoints(int i) {
        if (i > 0) {
            this.score += i;
        } else {
            throw new IntegerNotPositiveException(i);
        }
    }

    public Pacman getApplicableObject() {
        return applicableObject;
    }

}
