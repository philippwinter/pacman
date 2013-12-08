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
public class Pacman extends DynamicTarget {

    private String name;

    private Highscore highscore;

    @Override
    public void collide(MapObject obj) {
        // TODO Implement method

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void computeHighscore() {
        if (this.highscore == null) {
            this.highscore = new Highscore(this);
        }
    }

    public Highscore getHighscore() {
        return highscore;
    }

    @Override
    public void changeState(DynamicTargetState state) {
        // TODO Auto-generated method stub

    }

}
