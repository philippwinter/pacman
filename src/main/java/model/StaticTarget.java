/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.pacman.Pacman;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public abstract class StaticTarget extends MapObject implements Target {

    State state;

    public State getState() {
        return state;
    }


    protected StaticTarget(Position position, State state){
        this.state = state;
        setPosition(position);
    }

    public void changeState(State s) {
        if (s == null) {
            throw new IllegalArgumentException("A null state is not allowed.");
        } else if (state == s) {
            throw new IllegalArgumentException("The new state must differ from the old one.");
        }

        if (s == State.EATEN) {
            setVisible(false);
        } else if (s == State.AVAILABLE) {
            setVisible(true);
        }

        this.state = s;
    }


    @Override
    protected void setPosition(Position pos) {
        this.position = pos; // Set the position now to prevent the equals() method of the respective object to cause a NullPointerException
        if (pos.getOnPosition().contains(this)) {
            throw new IllegalArgumentException("There cannot be more than one StaticTarget on a position!" +
                                                "other targets: " + pos.getOnPosition());
        } else {
            super.setPosition(pos);
        }
    }

    public void deSpawn() {
        this.position.getOnPosition().remove(this);
    }

    public enum State {
        EATEN, AVAILABLE
    }


    public void gotEaten(){ changeState(State.EATEN);}

    @Override
    public void performCollision(Pacman pacman) {

        if (this.state == State.AVAILABLE)
            pacman.eat(this);
    }
}
