/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.event;

import model.*;

import java.util.ArrayList;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class PacmanEventHandler extends EventHandler {

    private PacmanContainer pacmanContainer;

    public void perform() {
        ArrayList<Pacman> pacmans = Game.getInstance().getPacmanContainer().getAll();

        for (Pacman p : pacmans) {
            this.handlePacman(p);
        }
    }

    private void handlePacman(Pacman pac) {
        MapObjectContainer mapObjectsOnPos = pac.getPosition().getOnPosition();

        for (MapObject mO : mapObjectsOnPos.getAll()) {
            // Implement method
        }
    }

}
