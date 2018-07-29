package model.Ghost;

import model.Position;

public class Pinky extends Ghost {


    public Pinky(Position pos) {
        super(pos, Colour.PINK, "Pinky");
        tragetPosition = new Position(0,0);
    }
}
