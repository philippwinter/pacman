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

    public Pacman(Position pos, String name) {
        this.highscore = new Highscore(this);
        this.state = DynamicTargetState.HUNTED;
        this.setName(name);
        this.setPosition(pos);
    }

    @Override
    public void gotEaten() {
        // TODO Implement method
        this.changeState(DynamicTargetState.MUNCHED);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Highscore getHighscore() {
        return highscore;
    }

    /**
     * Let the object eat a subclass of Target.
     *
     * @param target The object to be eaten.
     */
    @Override
    public void eat(Target target) {
        if (target instanceof Ghost) {
            Ghost g = (Ghost) target;
            g.setWaitingSeconds(4);
            this.highscore.addToScore(g.getScore());
        } else if (target instanceof Coin) {
            // TODO something
        }

        target.gotEaten();
    }

    @Override
    public void changeState(DynamicTargetState state) {
        // TODO Maybe insert some logic
        this.state = state;
    }

    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Pacman) {
                return this.getHighscore().equals(((Pacman) o).getHighscore())
                        && this.getPosition().equals(((Pacman) o).getPosition())
                        && this.getState().equals(((Pacman) o).getState())
                        && this.getHeadingTo().equals(((Pacman) o).getHeadingTo())
                        && this.getName().equals(((Pacman) o).getName());
            }
        }
        return false;
    }

}
