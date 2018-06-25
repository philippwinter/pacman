package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Position;

public class Waitting implements Behavior{

    private Ghost ghost;

    double waitingSeconds;

    public Waitting(Ghost ghost, double time){

        this.ghost = ghost;
        waitingSeconds = time;

    }

    @Override
    public void handle() {

        ghost.changeState(Ghost.State.WAITING);

        if (ghost.getState() == Ghost.State.WAITING) {
            waitingSeconds -= 1 / Game.getInstance().getRefreshRate();
            if (waitingSeconds <= 0) {
                ghost.replace();
            }
        }
    }

    @Override
    public void performCollisions() {
        return;
    }

    @Override
    public Position nextPosition() {
        return ghost.getPosition();
    }
}
