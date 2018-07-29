/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.pacman;

import model.*;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Pacman extends DynamicObject {

    private String name;

    private final Score score;

    private Sex sex;

    private Position  startPosition;

    private double time;


    public Pacman( Sex sex) {
        this.score = new Score();
        Position pos;
        switch (sex) {
            case MALE:
                this.setName("Mr. Pacman");
                pos = Map.getInstance().getPositionContainer().get(13,8);
                break;
            case FEMALE:
                pos = Map.getInstance().getPositionContainer().get(6,8);
                this.setName("Mrs. Pacman");
                break;
            default:
                throw new IllegalArgumentException("Something went wrong, there cannot be a sexless Pacman");
        }
        this.sex = sex;
        this.startPosition = pos;
        this.setPosition(pos);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Score getScore() {
        return score;
    }

    /**
     * Let the object eat a subclass of Target.
     *
     * @param target The object to be eaten.
     */
    public void eat(Target target) {

        target.gotEaten();
        this.score.addToScore((target));

    }

    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Pacman) {
                return this.getScore().equals(((Pacman) o).getScore())
                        && this.getPosition().equals(((Pacman) o).getPosition())
                        && this.getHeadingTo().equals(((Pacman) o).getHeadingTo())
                        && this.getName().equals(((Pacman) o).getName());
            }
        }
        return false;
    }

    public Sex getSex() {
        return sex;
    }

    public void handlePacman(double delta) {

        time += delta;

        double basicSpeed = 4;
        if (time * basicSpeed >= 1){

            Position newPosition = Map.getPositionByDirectionIfMovableTo(getPosition(), getHeadingTo());

            if (newPosition != null) {
                move(newPosition);
            }

            Map.positionsToRender.add(getPosition());

            time -= 1/ basicSpeed;

        }
    }

    public void replace(){

        this.move(startPosition);

    }

    public enum Sex {
        MALE, FEMALE
    }

    public String toString() {
        return "Pacman [" + position + ", "  + ", " + sex + ", " + score + ", visible: " + visible + "]";
    }

}
