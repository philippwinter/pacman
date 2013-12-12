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
public abstract class StaticTarget extends Target {

    protected StaticTargetState state = StaticTargetState.AVAILABLE;

    public StaticTarget(Position pos) {
        super(pos);
    }

    public StaticTargetState getState() {
        return state;
    }

    public abstract void changeState(StaticTargetState state);

}
