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
public class Coin extends StaticTarget implements Munchable {

    /**
     * The collide method gets called when two MapObjects are on the same position on the map.
     *
     * @param obj The map object this object is colliding with.
     */
    @Override
    public void collide(MapObject obj) {

        if (obj instanceof Pacman) {
            if (this.getState() == StaticTargetState.AVAILABLE) {
                // Change all states of Ghosts to "Hunted"

                for (Ghost g : Game.getInstance().getGhostContainer()) {
                    // Perform changes

                    g.changeState(DynamicTargetState.HUNTED);


                }

                // Add points to Pacmans score
            }
        }
    }

    /**
     * Change the state and perform necessary actions in order to do this, f.e. increasing the highscore.
     *
     * @param state The new state.
     */
    @Override
    public void changeState(StaticTargetState state) {
        // TODO Auto-generated method stub

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

}
