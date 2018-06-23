package model.Ghost;

import model.Map;
import model.Position;

public class GhostFactory {

    public static Ghost createGhost(Ghost.Colour colour){

        Map map = Map.getInstance();
        Position position;

        switch (colour){
            case RED:
                position = map.getPositionContainer().get(11, 3);
                return new Blinky(position,colour);
            case PINK:
                position = map.getPositionContainer().get(10, 3);
                return new Pinky(position,colour);
            case ORANGE:
                position = map.getPositionContainer().get(9, 3);
                return new Clyde(position,colour);
            case BLUE:
                position = map.getPositionContainer().get(8, 3);
                return  new Inky(position, colour);
            default: return null; // TODO: ADD exception;
        }
    }
}
