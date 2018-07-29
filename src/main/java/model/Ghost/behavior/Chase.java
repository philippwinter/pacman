package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Map;
import model.Position;
import model.pacman.Pacman;

import java.util.List;

public class Chase implements Behavior {

    private final Ghost ghost;

    public Chase(Ghost ghost){
        this.ghost = ghost;
    }

    public void handle(){
    }

    @Override
    public void performCollisions(Pacman pacman) {
        Game.getInstance().onPacmanGotEaten();
    }

    private Map.Direction nextDirection(){

        List<Map.Direction> directions = ghost.movablesDirections();

        Position ghostPosition = ghost.getPosition();
        Position pacmanPosition = Game.getInstance().getPacmanContainer().get(0).getPosition();


        double min = Double.MAX_VALUE;
        Map.Direction direction = directions.get(0);

        if (directions.size() > 1) {


            for (Map.Direction tmp : directions) {

                Position pos = Map.getPositionByDirectionIfMovableTo(ghostPosition, tmp);
                double dist = pacmanPosition.calcDistance(pos);


                if (dist <= min) {

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

        return newPosition;
    }

    @Override
    public double getSpeed() {
        return ghost.getBasicSpeed();
    }


}
