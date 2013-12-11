/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.event.EventHandlerManager;

/**
 * Ghosts are the little beasts {@link Pacman} can hunt after eating a {@link Coin}.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Ghost extends DynamicTarget {

    /**
     * The current colour of this ghost.
     */
    private Colour colour;

    /**
     * The name of the ghost.
     */
    private String name;

    private int waitingSeconds = -1;

    private double speed = 1;

    private boolean movedInLastTurn = false;

    /**
     * The state of the ghost.
     */
    private DynamicTargetState state;

    /**
     * React on a collision of another map object and this ghost.
     *
     * @param obj The object the ghost is colliding with.
     */
    @Override
    public void collide(MapObject obj) {
        if(obj instanceof Point || obj instanceof Coin){
            // Just do nothing
            return;
        }else if(obj instanceof Pacman){
            if( ((Pacman) obj).getState() == DynamicTargetState.HUNTED){
                this.eat((Pacman) obj);
            }
        }

    }

    /**
     * Gets the current colour of the ghost.
     *
     * @return The colour.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Sets the colour of the ghost.
     *
     * @param colour The new colour.
     */
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    /**
     * Gets the name of the ghost.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name of the ghost.
     *
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Let the object eat a subclass of Target.
     *
     * @param target The object to be eaten.
     */
    protected void eat(Target target) {
        if(target instanceof Pacman){
            ((Pacman) target).changeState(DynamicTargetState.MUNCHED);
        }
    }

    /**
     * Changes the state of the ghost and performs needed operations.
     *
     * @param state The new state.
     */
    public void changeState(DynamicTargetState state) {
        if(state == DynamicTargetState.HUNTED){
            this.speed *= 0.5;
        }else if (state == DynamicTargetState.HUNTER){
            this.speed *= 2;
        }
        this.state = state;
    }

    public void setWaitingSeconds(int waitingSeconds) {
        this.waitingSeconds = waitingSeconds;
    }

    public int getWaitingSeconds() {
        return waitingSeconds;
    }

    public boolean getMovedInLastTurn() {
        return movedInLastTurn;
    }

    public void setMovedInLastTurn(boolean b){
        this.movedInLastTurn = b;
    }
}
