/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import controller.MainController;

/**
 * A coin represents the object our Pacman has to eat in order to be able to hunt the ghosts.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Coin extends StaticTarget implements Scorable {

    private static double activeSeconds = 0;

    public static final double PACMAN_AINT_EATER = -1000;

    public static void resetActiveSeconds() {
        Coin.activeSeconds = 0;
    }

    public static double getActiveSeconds () {
        return activeSeconds;
    }

    public static void setActiveSeconds(double value) {
        double result = activeSeconds - value;
        if (result <= 0) {
            result = PACMAN_AINT_EATER;
        }
        activeSeconds = result;
    }

    public Coin(Position pos) {
        this.state = State.AVAILABLE;
        this.setPosition(pos);
    }

    /**
     * Change the state and perform necessary actions in order to do this, f.e. increasing the highscore.
     *
     * @param state The new state.
     */
    @Override
    public void changeState(State state) {
        if (state == null) {
            throw new IllegalArgumentException("A null state is not allowed.");
        }

        if (state == State.EATEN) {
            setVisible(false);
            Coin.activeSeconds += 4;
        } else if (state == State.AVAILABLE) {
            setVisible(true);
        }

        this.state = state;
    }

    /**
     * Return the score this objects gives to the player who is eating it.
     *
     * @return The score.
     */
    @Override
    public int getScore() {
        return 50;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Coin) {
                // Check if the state and position of both Coins are equal
                Coin c = ((Coin) o);

                return this.getPosition().equals(c.getPosition())
                        && this.getState().equals(c.getState());
            }
        }

        return false;
    }

    public void gotEaten() {
       this.changeState(State.EATEN);

       System.out.println(this + " got eaten");
    }

    @Override
    public String toString() {
        return "Coin [" + position + ", " + state + ", visible: " + visible + "]";
    }

}
