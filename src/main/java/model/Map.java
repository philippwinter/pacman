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

        return null;
    }

    private Map() {
        this(300, 300);
    }

    private Map(int width, int height) {
        this.width = width;
        this.height = height;

        // TODO Implement method

    }

    public int getAmountOfNotBlockedPlaces() {
        // TODO Implement method

        return 0;
    }

    public int getAmountOfPoints() {
        return getAmountOfNotBlockedPlaces() - Game.getInstance().getCoinContainer().getMax();
    }
}
