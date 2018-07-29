/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

//test

package model;

/**
 * A coin represents the object our Pacman has to eat in order to be able to hunt the ghosts.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Coin extends StaticTarget implements Scorable {

    public static final double PACMAN_AINT_EATER = -1;

    private static double activeSeconds = PACMAN_AINT_EATER;

    public static void resetActiveSeconds() {
        Coin.activeSeconds = PACMAN_AINT_EATER;
    }

    public static double getActiveSeconds() {
        return activeSeconds;
    }

    public static void reduceActiveSeconds(double value) {
        double result = activeSeconds - value;
        if (result <= 0) {
            result = PACMAN_AINT_EATER;
        }
        activeSeconds = result;
    }

    public Coin(Position pos) {
        super(pos, State.AVAILABLE);
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
        } else if (state == this.state) {
            throw new IllegalArgumentException("State must differ from previous one");
        }

        if (state == State.EATEN) {

            this.state = state;

            int nbrCoin = Game.getInstance().nbrOfActiveCoin();

            setVisible(false);
            if (nbrCoin < 2)
                Game.getInstance().frightenedGhost(7.);
            else
                Game.getInstance().frightenedGhost(5.);


        } else if (state == State.AVAILABLE) {
            this.state = state;
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
        if (this.state == State.AVAILABLE)
            this.changeState(State.EATEN);
    }

    @Override
    public String toString() {
        return "Coin [" + position + ", " + state + ", visible: " + visible + "]";
    }

}
