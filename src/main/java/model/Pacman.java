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

    public Pacman() {
        this.highscore = new Highscore(this);
    }

    /**
     * Let the pacman collide with another object on the map.
     *
     * @param obj The object to be eaten.
     */
    @Override
    public void collide(MapObject obj) {
        if(obj instanceof Coin || obj instanceof Point) {
            this.eat((Target) obj);
        }else if(obj instanceof  Ghost){
            Ghost g = (Ghost) obj;
            if(g.getState() == DynamicTargetState.HUNTED){
                this.eat(g);
            }
        }

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
        if(target instanceof Ghost){
            Ghost g = (Ghost) target;
            g.setWaitingSeconds(4);
        }
    }

    @Override
    public void changeState(DynamicTargetState state) {
        // TODO Auto-generated method stub

    }

}
