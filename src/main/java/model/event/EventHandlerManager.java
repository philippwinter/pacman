/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.event;

import java.util.ArrayList;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class EventHandlerManager {

    private ArrayList<EventHandler> eventObjects;

    public void execute() {
        try {
            for (EventHandler e : this.eventObjects) {
                e.perform();
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

    }

    public boolean register(EventHandler eH) {
        try {
            this.eventObjects.add(eH);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public void startExecution() {
        // TODO Implement method
    }

    public void pauseExecution() {
        // TODO Implement Method
    }
}
