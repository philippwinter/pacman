package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Map;
import model.Position;
import model.pacman.Pacman;

import java.util.List;

public class Dead implements Behavior{

    private final Ghost ghost;
    private Position traget;

    public Dead(Ghost ghost, double time){

        this.ghost = ghost;
        traget = new Position(ghost.getStartPosition().getX(),1);

    }

    @Override
    public void handle() {
        if (ghost.getPosition().equals(traget))
        ghost.replace();
    }

    @Override
    public void performCollisions(Pacman pacman) {
    }


    private Map.Direction nextDirection(){


        List<Map.Direction> directions = ghost.movablesDirections();


        double min = Double.MAX_VALUE;
        Map.Direction direction = directions.get(0);


        if (directions.size() > 0) {


            for (Map.Direction tmp : directions) {

                Position pos = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), tmp);
                double dist = traget.calcDistance(pos);


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

        if (ghost.getPosition().equals(traget))
            return traget;

        Position newPosition ;

        Map.Direction guessedDirection = nextDirection();
        ghost.setHeadingTo(guessedDirection);
        newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), guessedDirection);

        return newPosition;

    }

    @Override
    public double getSpeed() {
        return (ghost.getBasicSpeed() * 4);
    }
}
