/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.event;

import model.Coin;
import model.Game;
import model.Level;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class CoinEventHandler extends EventHandler {

    private double activePointSeconds = 0;

    @Override
    public void perform() {
        // TODO Auto-generated method stub

        this.activePointSeconds -= Game.getInstance().getRefreshRate();

    }

    public double getActivePointSeconds() {
        return activePointSeconds;
    }

    public void gotEaten(Coin c) {
        this.activePointSeconds += Level.getInstance().getSecondsPerCoin();
    }

}
