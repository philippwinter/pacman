package model.Ghost;

import model.Position;

public class Clyde extends Ghost {
    public Clyde(Position pos) {

        super(pos, Colour.ORANGE);
        name = "Clyde";
        tragetPosition = new Position(0,10);
    }
}
