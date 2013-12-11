/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

/**
 * A wall is limiting the area an {@link Target} can move to.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
@SuppressWarnings("unused")
public class Wall extends MapObject {

    private WallType type;

    private Position position;

    public Wall(Position position, WallType type){
        this.type = type;
        this.position = position;
    }

    public WallType getType(){
        return type;
    }

    @Override
    public void collide(MapObject obj) {
        throw new IllegalArgumentException("You cannot collide with a wall.");
    }

    public Position getPosition(){
        return position;
    }

}
