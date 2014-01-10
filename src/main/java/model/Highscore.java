/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

/**
 * The highscore is the collection of numbers, the user will be proud of.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
@SuppressWarnings("unused")
public class Highscore {

    /**
     * The score, starting at zero.
     */
    private long score = 0;

    /**
     * The Pacman the highscore is belonging to.
     */
    private Pacman applicableObject;

    /**
     * Constructs a new Highscore for a specific pacman.
     *
     * @param pacMan The pacman, this highscore belongs to.
     */
    public Highscore(Pacman pacMan) {
        this.applicableObject = (pacMan);
    }

    /**
     * Gets the score.
     *
     * @return The current score.
     */
    public long getScore() {
        return score;
    }

    /**
     * Adds an amount of points to the highscore.
     *
     * @param i A positive integer.
     *
     * @throws java.lang.IllegalArgumentException When <i>i</i> is not positive.
     */
    private void addToScore(int i) {
        if (i > 0) {
            this.score += i;
        } else {
            throw new IllegalArgumentException("The amount of points must be positive.");
        }
    }

    public void addToScore(Scorable s) {
        this.addToScore(s.getScore());
    }

    /**
     * Gets the pacman, this Highscore is belonging to.
     *
     * @return The highscore's owner.
     */
    public Pacman getApplicableObject() {
        return applicableObject;
    }

    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Highscore) {
                return this.getScore() == ((Highscore) o).getScore()
                        // Just compare the two pacmans per reference to prevent infinite recursion
                        && this.getApplicableObject() == ((Highscore) o).getApplicableObject();
            }
        }
        return false;
    }

}
