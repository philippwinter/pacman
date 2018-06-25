package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Map;
import model.Position;

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
        if (time <= 0) ghost.behavior = new Chase(ghost);
    }

    @Override
    public void performCollisions(){
        ghost.behavior = new Waitting(ghost, 3);
    }

    @Override
    public Position nextPosition() {
        Position newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), ghost.getHeadingTo());

        if (newPosition == null || (Map.freeNeighbourFields(ghost.getPosition()) > 1 && Math.round(Math.random()) == 1)) {
            Map.Direction guessedDirection = Map.Direction.guessDirection(ghost.getPosition());
            ghost.setHeadingTo(guessedDirection);
            newPosition = Map.getPositionByDirectionIfMovableTo(ghost.getPosition(), guessedDirection);
            ghost.move(newPosition);
        }

        return newPosition;
    }
}
