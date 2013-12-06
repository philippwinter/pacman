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
public class Ghost extends DynamicTarget {

    private Colour colour;

    private String name;

    private GhostState state;

    @Override
    public void collide(MapObject obj) {
        // TODO Implement method

    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeState(GhostState state) {
        // Perform operations respective to new state

        this.state = state;
    }

    @Override
    public void changeState(StaticTargetState state) {
        // TODO Auto-generated method stub

    }

}
