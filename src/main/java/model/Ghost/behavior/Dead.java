package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Map;
import model.Position;
import model.pacman.Pacman;

import java.util.List;

public class Dead implements Behavior{

    private final Ghost ghost;

    public Dead(Ghost ghost, double time){

        this.ghost = ghost;

    }

    @Override
    public void handle() {
        if (ghost.getStartPosition() == ghost.getPosition())
        ghost.replace();
    }

    @Override
    public void performCollisions(Pacman pacman) {
    }


    private Map.Direction nextDirection(){

        List<Map.Direction> directions = ghost.movablesDirections();

        Position ghostPosition = ghost.getPosition();
        Position traget = ghost.getTragetPosition();

        double min = Double.MAX_VALUE;
        Map.Direction direction = directions.get(0);


        if (directions.size() > 0) {


            for (Map.Direction tmp : directions) {

                Position pos = Map.getPositionByDirectionIfMovableTo(ghostPosition, tmp);
                double dist = ghost.getStartPosition().calcDistance(pos);


                if (dist < min) {

                    min = dist;
                    direction = tmp;


                }

            }
        }

        return direction;

    }


    @Override
    public Position nextPosition() {

        Position newPosition ;

        Map.Direction guessedDirection = nextDirection();
        ghost.setHeadingTo(guessedDirection);
        newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), guessedDirection);

        if (newPosition.isPlaceHolder())
            return ghost.getStartPosition();

        return newPosition;

    }

    @Override
    public double getSpeed() {
        return (ghost.getBasicSpeed() * 20);
    }
}
