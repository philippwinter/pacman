package model.Ghost.behavior;

import jdk.nashorn.internal.ir.IfNode;
import model.Game;
import model.Ghost.Clyde;
import model.Ghost.Ghost;
import model.Map;
import model.Position;
import model.pacman.Pacman;

import java.util.List;

public class Scatter implements Behavior {


    private Ghost ghost;

    public Scatter(Ghost ghost){
        this.ghost = ghost;
    }

    public void handle(){
    }

    @Override
    public void performCollisions(Pacman pacman) { Game.getInstance().onPacmanGotEaten();
    }

    public Map.Direction nextDirection(){

        List<Map.Direction> directions = ghost.movablesDirections();

        Position ghostPosition = ghost.getPosition();
        Position traget = ghost.getTragetPosition();


        double min = Double.MAX_VALUE;
        Map.Direction direction = directions.get(0);


        if (directions.size() > 0) {


            for (Map.Direction tmp : directions) {

                Position pos = Map.getPositionByDirectionIfMovableTo(ghostPosition, tmp);
                double dist = traget.calcDistance(pos);


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
        if (ghost.getPosition().isPlaceHolder()) guessedDirection = Map.Direction.NORTH;

        ghost.setHeadingTo(guessedDirection);
        newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), guessedDirection);

        return newPosition;
    }

    @Override
    public double getSpeed() {
        return ghost.getBasicSpeed();
    }

}
