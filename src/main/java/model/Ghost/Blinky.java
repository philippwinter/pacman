package model.Ghost;

import model.Position;

public class Blinky extends Ghost {

    public Blinky(Position pos) {
        super(pos, Colour.RED, "Blinky");
        tragetPosition = new Position(20,0);
    }
}
