package model.fruit;

import model.Position;
import model.StaticTarget;

public class Perry extends StaticTarget {

    public Perry(Position position) {
        super(position);
    }

    @Override
    public int getScore() {
        return 50;
    }
}
