/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.Ghost;

import model.*;
import model.Ghost.behavior.*;
import model.pacman.Pacman;

import java.util.List;

/**
 * Ghosts are the little beasts {@link Pacman} can hunt after eating a {@link Coin}.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public abstract class Ghost extends DynamicObject implements Scorable, Target {

    /**
     * The current colour of this ghost.
     */
    private Colour colour;

    /**
     * The name of the ghost.
     */
    String name;

    private double waitingSeconds = -1;

    private boolean movedInLastTurn = false;

    public Position getStartPosition() {
        return startPosition;
    }

    private final Position startPosition;
    
    private Behavior previousBehavior;

    public Behavior behavior;

    private double time;

    private final double basicSpeed = 4;

    Position tragetPosition;

    Ghost(Position pos, Colour colour, String name) {
        this.name = name;
        this.colour = colour;
        startPosition = pos;
        this.setPosition(pos);
        this.behavior = new Scatter(this);
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


    private double getWaitingSeconds() {
        return waitingSeconds;
    }

    public boolean getMovedInLastTurn() {
        return movedInLastTurn;
    }

    public double getBasicSpeed() {
        return basicSpeed;
    }

    @Override
    public int getScore() {
        return 200;
    }

    @Override
    public void gotEaten() {
        behavior = new Dead(this, 5 );
    }

    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Ghost) {
                Ghost g = (Ghost) o;
                return this.getWaitingSeconds() == g.getWaitingSeconds()
                        && this.getPosition().equals(g.getPosition())
                        && this.getMovedInLastTurn() == (g.getMovedInLastTurn())
                        && this.getColour().equals(g.getColour())
                        && this.getName().equals(g.getName())
                        && this.getHeadingTo().equals(g.getHeadingTo());
            }
        }
        return false;
    }

    public void handleGhost(double delta) {

        behavior.handle();

        time += delta;

        if (behavior instanceof Dead){

            System.out.println("----------");
            System.out.println(time);
            System.out.println(delta);
            System.out.println("----------");

        }

        if (time * behavior.getSpeed() >= 1) {
            this.movedInLastTurn = ! this.getMovedInLastTurn();
            move(behavior.nextPosition());
            time -= 1 / basicSpeed;
            if (time < 0) time = 0;
        }
    }
    
    public void resetBehavior(){

        setHeadingTo(getHeadingTo().reverse());
        behavior = previousBehavior;
    
    }

    public void changeBehavior(){

        setHeadingTo(getHeadingTo().reverse());

        if (behavior instanceof Scatter){
            behavior = new Chase(this);
        } else if (behavior instanceof Chase){
            behavior = new Scatter(this);
        }

    }

    public void frightened(double time){

        setHeadingTo(getHeadingTo().reverse());

        if (!(behavior instanceof Dead)){

            if (!(behavior instanceof Frightened)) previousBehavior = behavior;

            behavior = new Frightened(this, time);
        }
    }

    public void replace(){
        this.move(startPosition);
        behavior = new Scatter(this);
        this.time = 0;
    }

    public enum Colour {

        RED, PINK, BLUE, ORANGE,

    }

    public String toString() {
        return "Ghost [" + position + ", " + colour + ", visible: " + visible + "]";
    }


    public void performCollision(Pacman pacman){
        behavior.performCollisions(pacman);
    }

    public List<Map.Direction> movablesDirections(){

        List<Map.Direction> directions = Map.getInstance().movablesDirections(getPosition());
        directions.remove(getHeadingTo().reverse());

        Position southPosition = Map.getPositionByDirectionIfMovableTo(position, Map.Direction.SOUTH);

        if (southPosition != null && southPosition.isPlaceHolder())
            directions.remove(Map.Direction.SOUTH);

        return directions;

    }

    public Position getTragetPosition(){

        return tragetPosition;

    }

}
