/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import controller.MainController;
import model.event.Process;
import model.event.RendererProcess;
import model.event.Timer;
import model.event.WorkerProcess;
import view.MainGui;

/**
 * The Game class is kind of a <i>master</i>-class, organizing all other business logic objects.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class Game implements Process{

    static {
        Game.reset();
    }

    public static final double BASIC_REFRESH_RATE = 4.;

    public final static Settings settings = Settings.getInstance();

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
    private Timer eventHandlerManager;

    /**
     * The map is like a two dimensional array of positions, containing all map objects
     */
    private Map map;

    /**
     * The amount of time, our UI will be repainted.
     * Also how often the user is able to interact with it's character, e.g. by pressing a key.
     */
    private double refreshRate = BASIC_REFRESH_RATE;

    /**
     * The level of the game.
     */
    private Level level;

    private boolean isOver = false;

    private int playerLifes = 3;

    /**
     * Constructs a new Game object.
     */
    private Game() {

    }

    /**
     * Reset the game, for instance necessary when the user wants to start a new try.
     */
    public synchronized static void reset() {
        Game.initialized = true;
        Game.instance = new Game();
        // Initialization work must be done in a new method in order to retrieve the game object during the following work
        Game.instance.initializeInternal();
    }

    /**
     * The internal initialization method.
     */
    private synchronized void initializeInternal() {
        Map.reset();
        Coin.resetActiveSeconds();
        Level.reset();

        this.map = Map.getInstance();

        this.ghostContainer = new GhostContainer();
        this.coinContainer = new CoinContainer();
        this.pointContainer = new PointContainer();
        this.pacmanContainer = new PacmanContainer();
        this.level = Level.getInstance();

        this.eventHandlerManager = new Timer();
        this.eventHandlerManager.register(this);
        this.eventHandlerManager.register(new RendererProcess());

    }

    /**
     * Is the Game already initialized?
     */
    public static boolean isInitialized() {
        return Game.initialized;
    }

    public int getPlayerLifes() {
        return playerLifes;
    }

    public void reducePLayerLifes() {
        this.playerLifes -= 1;
    }

    public Level getLevel() {
        return level;
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
        this.refreshRate = Math.pow(Math.pow(l.getLevel(), 5), 1 / 7) + BASIC_REFRESH_RATE;
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
     * Starts the game, in detail it causes all {@link model.event.WorkerProcess}'s to start working.
     *
     * @see model.event.Timer#startExecution()
     */
    public void start() {
        if(pointContainer.size() == 0){
            this.map.placeObjects();
        }
        this.eventHandlerManager.startExecution();
    }

    /**
     * Pauses the game, by stopping/pausing all {@link model.event.WorkerProcess}'s.
     *
     * @see model.event.Timer#pauseExecution()
     */
    public void pause() {
        this.eventHandlerManager.pauseExecution();
    }

    /**
     * Compares two objects for equality.
     *
     * @param o The other object.
     *
     * @return Whether both objects are equal.
     */
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Game) {
                // As it is a singleton, checking for reference equality is enough
                return this == o;
            }
        }
        return false;
    }

    public void gameOver() {
        this.isOver = true;
        Game.getInstance().getEventHandlerManager().pauseExecution();
        MainController.getInstance().getGui().onGameOver();
        MainController.getInstance().getGui().getRenderer().markReady();
    }

    public Timer getEventHandlerManager() {
        return eventHandlerManager;
    }

    /**
     * Returns the singleton instance.
     *
     * @return The game singleton.
     */
    public static Game getInstance() {
        return Game.instance;
    }

    public boolean isGameOver() {
        return this.isOver;
    }

    public void onPacmanGotEaten() {
        Map.getInstance().onPacmanGotEaten();
    }

    public void increasePlayerLifes() {
        this.playerLifes++;
    }

    public boolean check() {
        boolean performFurtherActions;

        // Check whether level is completed
        int pointsEaten = 0;

        for (Point p : pointContainer) {
            if (p.getState() == StaticTarget.State.EATEN) {
                pointsEaten++;
            }
        }

        int size = getPointContainer().size();

        performFurtherActions = (pointsEaten != size) && (!isGameOver());

        if (pointsEaten == size) {
            Level.getInstance().nextLevel();
        }

        return performFurtherActions;
    }

    public void performCollisions() {
        for (Pacman p : getPacmanContainer()) {
            performCollision(p);
        }
    }

    private boolean checkCoinSeconds = false;

    private void performCollision(Pacman pac) {
        MapObjectContainer mapObjectsOnPos = pac.getPosition().getOnPosition();

        for (MapObject mO : mapObjectsOnPos.getAll()) {
            if (mO instanceof StaticTarget) {
                StaticTarget t = (StaticTarget) mO;
                // An already eaten thing hasn't to be eaten again
                if (t.getState() != StaticTarget.State.EATEN) {
                    if (t instanceof Coin) {
                        checkCoinSeconds = true;
                    }
                    pac.eat(t);
                }
            } else if (mO instanceof Ghost) {
                Ghost g = (Ghost) mO;
                if (g.getState() == DynamicTarget.State.HUNTED) {
                    pac.eat(g);
                } else if (g.getState() == DynamicTarget.State.HUNTER) {
                    g.eat(pac);
                }
            }
        }
    }


    public void handleCoins() {
        double activeSeconds = Coin.getActiveSeconds();

        if (activeSeconds != Coin.PACMAN_AINT_EATER) {
            Coin.reduceActiveSeconds(1 / Game.getInstance().getRefreshRate());
        }

        if (checkCoinSeconds && Coin.getActiveSeconds() == Coin.PACMAN_AINT_EATER) {
            for (Ghost g : Game.getInstance().getGhostContainer()) {
                if (g.getState() == DynamicTarget.State.HUNTED) {
                    g.changeState(DynamicTarget.State.HUNTER);
                }
            }

            for (Pacman p : Game.getInstance().getPacmanContainer()) {
                p.changeState(DynamicTarget.State.HUNTED);
            }

            checkCoinSeconds = false;
        }
    }

    public void markDynamicObjectsForRendering() {
        for(Pacman p : getInstance().getPacmanContainer()){
            Map.positionsToRender.add(p.getPosition());
        }
        for(Ghost g : getInstance().getGhostContainer()){
            Map.positionsToRender.add(g.getPosition());
        }
    }

    @Override
    public void onLoad() {
    }

    @Override
    public long getTiming() {
        return (long) (1000 / refreshRate);
    }

    @Override
    public long getStartupDelay() {
        return 0;
    }

    @Override
    public void run() {
        try {
            if (this.check()) {
                this.markDynamicObjectsForRendering();
                this.handleCoins();
                this.performCollisions();
                this.getPacmanContainer().handlePacmans();
                this.performCollisions(); // Must be done two times to prevent two objects moving through each other
                this.getGhostContainer().handleGhosts();

                this.markDynamicObjectsForRendering();
            }
        } catch (Throwable t) {
            MainController.uncaughtExceptionHandler.uncaught(t);
        }
    }

    public enum Mode {
        SINGLEPLAYER, MULTIPLAYER
    }
}
