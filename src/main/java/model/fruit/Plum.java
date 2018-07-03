package model.fruit;

import model.Position;
import model.StaticTarget;

public class Plum extends StaticTarget {

    public Plum(Position position) {
        super(position);
    }

    @Override
    public int getScore() {
        return 50;
    }
}
