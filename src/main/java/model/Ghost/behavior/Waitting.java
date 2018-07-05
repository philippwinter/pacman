package model.Ghost.behavior;

import model.Game;
import model.Ghost.Ghost;
import model.Position;
import model.pacman.Pacman;

public class Waitting implements Behavior{

    private final Ghost ghost;

    private double waitingSeconds;

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
    public void performCollisions(Pacman pacman) {
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
