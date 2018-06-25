package model.Ghost.behavior;

import model.Ghost.Ghost;
import model.Position;

public interface Behavior {

    void handle();

    void performCollisions();

    Position nextPosition();

    double getSpeed();

}
