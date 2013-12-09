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
     * Returns the singleton instance.
     *
     * @return The game singleton.
     */
    public static Game getInstance() {
        if (!Game.isInitialized()) {
            throw new ObjectNotInitializedException(Game.class.getCanonicalName());
        } else {
            return Game.instance;
        }
    }

    /**
     * Reset the game, for instance necessary when the user wants to start a new try.
     * Must be called after {@link #initializeGame()}, otherwise an IllegalStateException is thrown.
     *
     * @exception java.lang.IllegalStateException When the method is called before {@link #initializeGame()}.
     */
    public static void resetGame() {
        if(Game.isInitialized()){
            Game.instance = new Game();
            Game.initializeGameInternal();
            Game.initialized = true;
            initializeGameInternal();
        }else{
            throw new IllegalStateException("The game has to be initialized in order to reset it.");
        }
    }

    /**
     * The internal initialization class, performing tasks used in {@link #initializeGame()} and {@link #resetGame()}.
     */
    private static void initializeGameInternal() {
        Game.instance.eventHandlerManager.register(new PacmanEventHandler());
        Game.instance.eventHandlerManager.register(new GhostEventHandler());
        Game.instance.eventHandlerManager.register(new CoinEventHandler());
    }

    /**
     * Initializes the game.
     * Must be called before retrieving the instance with {@link #getInstance()}.
     *
     * @exception java.lang.IllegalStateException When the game has already been initialized.
     */
    public static void initializeGame() {
        if(!Game.isInitialized()){
            Game.instance = new Game();
            Game.initialized = true;
            initializeGameInternal();
        }else{
            throw new IllegalStateException("The game is already initialized.");
        }
    }

    /**
     * Is the Game already initialized?
     *
     * @return Returns {@code true} if, and only if, the {@link #initializeGame()} method has been called, otherwise {@code false}.
     */
    public static boolean isInitialized() {
        return Game.initialized;
    }

    /**
     * Constructs a new Game object.
     */
    private Game() {
        this.map = Map.getInstance();

        this.ghostContainer = new GhostContainer();
        this.coinContainer = new CoinContainer();
        this.pointContainer = new PointContainer(
                // TODO Make dynamic
                6
        );
        this.pacmanContainer = new PacmanContainer();
        this.eventHandlerManager = new EventHandlerManager();
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
}
