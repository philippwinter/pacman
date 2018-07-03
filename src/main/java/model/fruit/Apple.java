package model.fruit;

import model.Position;
import model.StaticTarget;

public class Apple extends StaticTarget {

    public Apple(Position position) {
        super(position);
    }

    @Override
    public int getScore() {
        return 50;
    }
}
