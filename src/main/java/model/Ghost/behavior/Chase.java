package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Map;
import model.Position;

public class Chase implements Behavior {

    private Ghost ghost;

    public Chase(Ghost ghost){
        this.ghost = ghost;
    }

    public void handle(){
    }

    @Override
    public void performCollisions() {
        Game.getInstance().onPacmanGotEaten();
    }

    @Override
    public Position nextPosition() {
        Position newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), ghost.getHeadingTo());

        // If the Ghost stands in front of a wall OR it could take another way
        if (newPosition == null || (Map.freeNeighbourFields(ghost.getPosition()) > 1 && Math.round(Math.random()) == 1)) {
            Map.Direction guessedDirection = Map.Direction.guessDirection(ghost.getPosition());
            ghost.setHeadingTo(guessedDirection);
            newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), guessedDirection);
        }

        return newPosition;
    }

    @Override
    public double getSpeed() {
        return ghost.getBasicSpeed();
    }


}
