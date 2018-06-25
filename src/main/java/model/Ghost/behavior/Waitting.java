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

        waitingSeconds -= 1 / Game.getInstance().getRefreshRate();
        if (waitingSeconds <= 0) ghost.replace();
    }

    @Override
    public void performCollisions() {
        return;
    }

    @Override
    public Position nextPosition() {
        return ghost.getPosition();
    }

    @Override
    public double getSpeed() {
        return 0;
    }
}
