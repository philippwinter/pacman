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

    public Pacman(Position pos) {
        super(pos);
        this.highscore = new Highscore(this);
        this.state = DynamicTargetState.HUNTED;
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
            this.highscore.addPoints(g.getScore());
        } else if (target instanceof Coin) {

        }

        target.gotEaten();
    }

    @Override
    public void changeState(DynamicTargetState state) {
        // TODO Maybe insert some logic
        this.state = state;
    }

}
