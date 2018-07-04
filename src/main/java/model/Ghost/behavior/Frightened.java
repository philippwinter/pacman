package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Map;
import model.Position;
import model.pacman.Pacman;

import java.util.List;

import static java.util.Collections.shuffle;


public class Frightened implements Behavior {

    private Ghost ghost;
    private int waitingSeconds = 1;
    private double time;


    public Frightened(Ghost ghost, double time){
        this.ghost = ghost;
        this.time = time;
    }

    public void handle() {
        time -= 1 / Game.getInstance().getRefreshRate();
        if (time <= 0) ghost.resetBehavior();
    }

    @Override
    public void performCollisions(Pacman pacman){
        pacman.eat(ghost);
    }

    public Map.Direction nextDirection(){

        List<Map.Direction> directions = ghost.movablesDirections();
        shuffle(directions);

        return directions.get(0);

    }

    @Override
    public Position nextPosition() {

            Map.Direction guessedDirection = nextDirection();

            ghost.setHeadingTo(guessedDirection);
            Position newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), guessedDirection);
            ghost.move(newPosition);

        return newPosition;
    }

    @Override
    public double getSpeed() {
        return ghost.getBasicSpeed() / 2;
    }
}
