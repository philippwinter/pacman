/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Map {

    private static Map instance;

    private PositionContainer positionContainer;

    private final int width;

    private final int height;

    public static Map getInstance() {
        if (Map.instance == null) {
            Map.instance = new Map();
        }

        return Map.instance;
    }

    private Map() {
        this(25, 20);
    }

    private Map(int width, int height) {
        this.width = width;
        this.height = height;

        this.positionContainer = new PositionContainer(width, height);

        // Create all position instances for this map
        for(int actX = 0; actX < width; actX++){
            for(int actY = 0; actY < height; actY++){
                this.positionContainer.add(new Position(actX, actY));
            }
        }

    }

    public int getAmountOfNotBlockedPlaces() {
        // TODO Implement method

        return 25;
    }

    public int getAmountOfPoints() {
        // TODO Implement method

        return 4;
    }

    public PositionContainer getPositionContainer() {
        return this.positionContainer;
    }

    public static Position getPositionByDirectionIfMoveableTo(Position prevPos, Direction movingTo){
        Position p = null;
        try{
            if(movingTo == Direction.NORTH){
                p = Map.getInstance().getPositionContainer().get(prevPos.getX(), prevPos.getY() + 1);
            }else if(movingTo == Direction.EAST){
                p = Map.getInstance().getPositionContainer().get(prevPos.getX() + 1, prevPos.getY());
            }else if(movingTo == Direction.WEST){
                p = Map.getInstance().getPositionContainer().get(prevPos.getX() - 1, prevPos.getY());
            }else if(movingTo == Direction.SOUTH){
                p =  Map.getInstance().getPositionContainer().get(prevPos.getX(), prevPos.getY() - 1);
            }
            if(p != null && p.isMoveableTo()){
                return p;
            }else{
                return null;
            }
        }catch(IllegalArgumentException ex){
           // Just return null to signalize, that the point doesn't exist
           return null;
        }


    }
}
