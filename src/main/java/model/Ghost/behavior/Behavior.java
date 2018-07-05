package model.Ghost.behavior;

import model.Position;
import model.pacman.Pacman;

public interface Behavior {

    void handle();

    void performCollisions(Pacman pacman);

    Position nextPosition();

    double getSpeed();

}
