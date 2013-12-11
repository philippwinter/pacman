/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.event;

import model.*;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class CoinEventHandler extends EventHandler {

    private double activePointSeconds = 0;

    private CoinContainer coinContainer;

    public CoinEventHandler() {
        this.coinContainer = Game.getInstance().getCoinContainer();
    }

    public double getActivePointSeconds() {
        return activePointSeconds;
    }

    public void gotEaten(Coin c) {
        this.activePointSeconds += Level.getInstance().getSecondsPerCoin();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        if(this.activePointSeconds > 0){
            this.activePointSeconds -= Game.getInstance().getRefreshRate();
        }

        if(this.activePointSeconds <= 0) {
            for(Ghost g : Game.getInstance().getGhostContainer()){
                g.changeState(DynamicTargetState.HUNTER);
            }

            for(Pacman p : Game.getInstance().getPacmanContainer()){
                p.changeState(DynamicTargetState.HUNTED);
            }
        }

    }
}
