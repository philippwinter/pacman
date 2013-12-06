/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.event.CoinEventHandler;
import model.event.EventHandlerManager;
import model.event.GhostEventHandler;
import model.event.PacmanEventHandler;
import model.exception.ObjectNotInitializedException;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Game {

    private static Game instance;

    private static boolean initialized;

    private GhostContainer ghostContainer;

    private CoinContainer coinContainer;

    private PointContainer pointsContainer;

    private PacmanContainer pacmanContainer;

    /**
     * The event handler reacts on events happening in the game.
     */
    private EventHandlerManager eventHandlerManager;

    private Map map;

    private double refreshRate = 0.25;

    public static Game getInstance() {
        if (!Game.isInitialized()) {
            throw new ObjectNotInitializedException(Game.class.getCanonicalName());
        } else {
            return Game.instance;
        }
    }

    public static void initializeGame() {
        Game.instance = new Game();

        Game.instance.eventHandlerManager.register(new PacmanEventHandler());
        Game.instance.eventHandlerManager.register(new GhostEventHandler());
        Game.instance.eventHandlerManager.register(new CoinEventHandler());

        // Mark Game as initialized
        Game.initialized = true;
    }

    public static boolean isInitialized() {
        return Game.initialized;
    }

    private Game() {
        this.map = Map.getInstance();

        this.ghostContainer = new GhostContainer();
        this.coinContainer = new CoinContainer();
        this.pointsContainer = new PointContainer(this.map.getAmountOfPoints());
        this.pacmanContainer = new PacmanContainer();
        this.eventHandlerManager = new EventHandlerManager();
    }

    public GhostContainer getGhostContainer() {
        return ghostContainer;
    }

    public CoinContainer getCoinContainer() {
        return coinContainer;
    }

    public PointContainer getPointsContainer() {
        return pointsContainer;
    }

    public PacmanContainer getPacmanContainer() {
        return pacmanContainer;
    }

    public Map getMap() {
        return map;
    }

    public void changeRefreshRate(Level l) {
        // f(x) = (x^5)^(1/7) or "The refresh rate per second is the 7th root of the level raised to 5"
        this.refreshRate = Math.pow(Math.pow(l.getLevel(), 5), 1 / 7);
    }

    public double getRefreshRate() {
        return this.refreshRate;
    }

    public void start() {
        this.eventHandlerManager.startExecution();
    }

    public void pause() {
        this.eventHandlerManager.pauseExecution();
    }
}
