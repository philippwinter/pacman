package model.fruit;

import model.Position;
import model.StaticTarget;

public class Strawberry extends StaticTarget {

    public Strawberry(Position position) {
        super(position, State.AVAILABLE);
    }

    @Override
    public int getScore() {
        return 50;
    }
}
