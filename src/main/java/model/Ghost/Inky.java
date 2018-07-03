package model.Ghost;

import model.Position;

public class Inky extends Ghost {
    public Inky(Position pos) {
        super(pos, Colour.BLUE);
        name = "Inky";
        tragetPosition = new Position(19,10);
    }
}
