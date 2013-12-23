/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

/**
 * A coin represents the object our Pacman has to eat in order to be able to hunt the ghosts.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Coin extends StaticTarget implements Scorable {

    public Coin(Position pos) {
        this.state = StaticTargetState.AVAILABLE;
        this.setPosition(pos);
    }
    /**
     * Change the state and perform necessary actions in order to do this, f.e. increasing the highscore.
     *
     * @param state The new state.
     */
    @Override
    public void changeState(StaticTargetState state) {
        if(state == null){
            throw new IllegalArgumentException("A null state is not allowed.");
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
        // TODO Implement method
    }

}
