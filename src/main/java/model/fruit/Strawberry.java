package model.fruit;

import model.Position;
import model.StaticTarget;

public class Strawberry extends StaticTarget {

    public Strawberry(Position position) {
        super(position);
    }

    @Override
    public void changeState(State state) {

    }

    @Override
    public int getScore() {
        return 0;
    }
}
