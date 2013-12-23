/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.event.EventHandler;
import model.event.EventHandlerManager;

/**
 * The Game class is kind of a <i>master</i>-class, organizing all other business logic objects.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Game {

    /**
     * The singleton instance.
     */
    private static Game instance;

    /**
     * Whether the game was initialized already.
     */
    private static boolean initialized;

    /**
     * A container of all ghosts.
     */
    private GhostContainer ghostContainer;

    /**
     * A container of all coins.
     */
    private CoinContainer coinContainer;

    /**
     * A container of all points.
     */
    private PointContainer pointContainer;

    /**
     * A container of all pacmans.
     */
    private PacmanContainer pacmanContainer;

    /**
     * The event handler reacts on events happening in the game.
     */
    private EventHandlerManager eventHandlerManager;

    /**
     * The map is like a two dimensional array of positions, containing all map objects
     */
    private Map map;

    /**
     * The amount of time, our UI will be repainted.
     * Also how often the user is able to interact with it's character, e.g. by pressing a key.
     */
    private double refreshRate = 1;

    /**
     * The level of the game.
     */
    private Level level;

    /**
     * Returns the singleton instance.
     *
     * @return The game singleton.
     */
    public static Game getInstance() {
        if (!Game.isInitialized()) {
            Game.initialize();
        }
        return Game.instance;
    }

    /**
     * Reset the game, for instance necessary when the user wants to start a new try.
     */
    public static void reset() {
        Game.initialized = true;
        Game.instance = new Game();
    }

    /**
     * The internal initialization class, performing tasks used in {@link #initialize()} and {@link #reset()}.
     */
    private void initializeInternal() {
        Map.reset();
        Point.resetActivePointSeconds();
        Level.reset();

        this.map = Map.getInstance();

        this.ghostContainer = new GhostContainer();
        this.coinContainer = new CoinContainer();
        this.pointContainer = new PointContainer(
                // TODO Make dynamic
                6
        );
        this.pacmanContainer = new PacmanContainer();
        this.level = Level.getInstance();

        this.eventHandlerManager = new EventHandlerManager();
        this.eventHandlerManager.register(new EventHandler());
    }

    public Level getLevel() {
        return level;
    }

    /**
     * Initializes the game.
     * Must be called before retrieving the instance with {@link #getInstance()}.
     *
     * @throws java.lang.IllegalStateException When the game has already been initialized.
     */
    public static void initialize() {
        if (!Game.isInitialized()) {
            Game.initialized = true;
            Game.instance = new Game();
        } else {
            throw new IllegalStateException("The game is already initialized.");
        }
    }

    /**
     * Is the Game already initialized?
     *
     * @return Returns {@code true} if, and only if, the {@link #initialize()} method has been called, otherwise {@code false}.
     */
    public static boolean isInitialized() {
        return Game.initialized;
    }

    /**
     * Constructs a new Game object.
     */
    private Game() {
        this.initializeInternal();
    }

    /**
     * Gets the ghost container.
     *
     * @return The container used to manage all instance of {@link Ghost}'s in the object tree.
     */
    public GhostContainer getGhostContainer() {
        return ghostContainer;
    }

    /**
     * Gets the coin container.
     *
     * @return The container used to manage all instance of {@link Coin}'s in the object tree.
     */
    public CoinContainer getCoinContainer() {
        return coinContainer;
    }

    /**
     * Gets the point container.
     *
     * @return The container used to manage all instance of {@link Point}'s in the object tree.
     */
    public PointContainer getPointContainer() {
        return pointContainer;
    }

    /**
     * Gets the pacman container.
     *
     * @return The container used to manage all instance of {@link Pacman}'s in the object tree.
     */
    public PacmanContainer getPacmanContainer() {
        return pacmanContainer;
    }

    /**
     * Gets the map of the game.
     *
     * @return The map.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Changes the refresh rate depending on the level.
     * Can be expressed by the equation <code>RefreshRate(level) = (level^5)^(1/7)</code>.
     *
     * @param l The level which is used as a parameter in the mathematical equation to generate a new refresh rate.
     */
    public void changeRefreshRate(Level l) {
        // f(x) = (x^5)^(1/7) or "The refresh rate per second is the 7th root of the level raised to 5"
        this.refreshRate = Math.pow(Math.pow(l.getLevel(), 5), 1 / 7);
    }

    /**
     * Gets the refresh rate.
     *
     * @return The refresh rate.
     */
    public double getRefreshRate() {
        return this.refreshRate;
    }

    public EventHandlerManager getEventHandlerManager() {
        return eventHandlerManager;
    }

    /**
     * Starts the game, in detail it causes all {@link model.event.EventHandler}'s to start working.
     *
     * @see model.event.EventHandlerManager#startExecution()
     */
    public void start() {
        this.eventHandlerManager.startExecution();
    }

    /**
     * Pauses the game, by stopping/pausing all {@link model.event.EventHandler}'s.
     *
     * @see model.event.EventHandlerManager#pauseExecution()
     */
    public void pause() {
        this.eventHandlerManager.pauseExecution();
    }

    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Game) {
                // As it is a singleton, checking for reference equality is enough
                return this == o;
            }
        }
        return false;
    }
}
