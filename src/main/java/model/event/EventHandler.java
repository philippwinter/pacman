/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.event;

import controller.MainController;
import model.*;
import model.Map.Direction;

import java.util.ArrayList;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class EventHandler implements Runnable {

    private PointContainer points;

    private CoinContainer coins;

    private GhostContainer ghosts;

    private PacmanContainer pacmans;

    private Map map;

    private boolean coinEaten = false;

    public void onLoad() {
        this.points = Game.getInstance().getPointContainer();
        this.coins = Game.getInstance().getCoinContainer();
        this.ghosts = Game.getInstance().getGhostContainer();
        this.pacmans = Game.getInstance().getPacmanContainer();
        this.map = Game.getInstance().getMap();
    }

    public void run() {
        try {
            this.handlePacmans();
            this.handleGhosts();
            this.handleCoins();
            assert MainController.getInstance() != null;
            assert MainController.getInstance().getGui() != null;
            assert MainController.getInstance().getGui().getRenderer() != null;
            MainController.getInstance().getGui().getRenderer().markReady();
        } catch (Throwable t) {
            MainController.uncaughtExceptionHandler.uncaught(t);
        }
    }

    private void handlePacmans() {
        ArrayList<Pacman> pacmans = Game.getInstance().getPacmanContainer().getAll();

        for (Pacman p : pacmans) {
            this.handlePacman(p);
        }
    }

    public void handleCoins() {
        if (Coin.getActiveSeconds() > 0) {
            Coin.setActiveSeconds(Coin.getActiveSeconds() - (Game.getInstance().getRefreshRate() / 1000));
        }

        if (coinEaten && Coin.getActiveSeconds() == Coin.PACMAN_AINT_EATER) {
            for (Ghost g : Game.getInstance().getGhostContainer()) {
                g.changeState(DynamicTarget.State.HUNTER);
            }

            for (Pacman p : Game.getInstance().getPacmanContainer()) {
                p.changeState(DynamicTarget.State.HUNTED);
            }
            coinEaten = false;
        }
    }

    private void handleGhosts() {
        for (Ghost g : this.ghosts) {
            this.handleGhost(g);
        }
    }

    private void handlePacman(Pacman pac) {
        MapObjectContainer mapObjectsOnPos = pac.getPosition().getOnPosition();

        if (pac.getState() != DynamicTarget.State.MUNCHED && pac.getState() != DynamicTarget.State.WAITING) {
            Position newPosition = Map.getPositionByDirectionIfMovableTo(pac.getPosition(), pac.getHeadingTo());

            if (newPosition != null) {
                pac.move(newPosition);
                //System.out.println(pac + " moves to " + newPosition);
            } else {
                System.out.println("Couldn't move from " + pac.getPosition() + " in direction " + pac.getHeadingTo());
            }
        }

        for (MapObject mO : mapObjectsOnPos.getAll()) {
            if (mO instanceof StaticTarget) {
                StaticTarget t = (StaticTarget) mO;
                // An already eaten thing hasn't to be eaten again
                if (t.getState() != StaticTarget.State.EATEN) {
                    if (t instanceof Coin) {
                        coinEaten = true;
                    }
                    pac.eat(t);
                }
            } else if (mO instanceof Ghost) {
                Ghost g = (Ghost) mO;
                if (g.getState() == DynamicTarget.State.HUNTED) {
                    pac.eat(g);
                } else {
                    g.eat(pac);
                }
            }
        }
    }

    private void handleGhost(Ghost g) {
        Position newPosition = Map.getPositionByDirectionIfMovableTo(g.getPosition(), g.getHeadingTo());

        // If the Ghost stands in front of a wall OR it could take another way
        if (newPosition == null || (Map.freeNeighbourFields(g.getPosition()) > 1 && Math.round(Math.random()) == 1)) {
            Direction guessedDirection = Map.Direction.guessDirection(g);
            g.setHeadingTo(guessedDirection);
            newPosition = Map.getPositionByDirectionIfMovableTo(g.getPosition(), guessedDirection);
        }


        if (g.getState() == DynamicTarget.State.HUNTER) {
            g.move(newPosition);
        } else if (g.getState() == DynamicTarget.State.MUNCHED) {
            g.changeState(DynamicTarget.State.WAITING);
        } else if (g.getState() == DynamicTarget.State.WAITING) {
            if (g.getWaitingSeconds() > 0) {
                g.reduceWaitingSeconds(1000 / Game.getInstance().getRefreshRate());
            } else if (g.getWaitingSeconds() == 0) {
                // If time is up, release the ghost
                g.changeState(DynamicTarget.State.HUNTER);
            }
        } else if (g.getState() == DynamicTarget.State.HUNTED) {
            if (g.getMovedInLastTurn()) {
                g.setMovedInLastTurn(false);
            } else {
                g.move(newPosition);
                g.setMovedInLastTurn(true);
            }
        }
    }

}
