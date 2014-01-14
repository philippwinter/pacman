/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.event;

import controller.MainController;
import model.Game;
import model.Map;

/**
 * RendererProcess
 *
 * @author Philipp Winter
 */
public class RendererProcess implements Process {

    @Override
    public long getTiming() {
        long refreshRate = (long) Game.getInstance().getRefreshRate();
        if (refreshRate < 6) {
            return 1000/refreshRate;
        } else {
            return 1000/6;
        }
    }

    @Override
    public long getStartupDelay() {
        return 15;
    }

    @Override
    public void onLoad() {
        Map.getInstance().markAllForRendering();
    }

    public void run() {
        MainController.getInstance().getGui().getRenderer().markReady();
    }
}
