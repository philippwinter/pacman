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
public class EventHandler implements Runnable {

    private double activePointSeconds = 0;

    private PointContainer points;

    private CoinContainer coins;

    private GhostContainer ghosts;

    private PacmanContainer pacmans;

    private Map map;

    public void onLoad() {
        this.points = Game.getInstance().getPointContainer();
        this.coins = Game.getInstance().getCoinContainer();
        this.ghosts = Game.getInstance().getGhostContainer();
        this.pacmans = Game.getInstance().getPacmanContainer();
        this.map = Game.getInstance().getMap();
    }

    public void run() {
        this.handlePacmans();
        this.handleGhosts();
        this.handleCoins();
    }

    private void handlePacmans() {
        ArrayList<Pacman> pacmans = Game.getInstance().getPacmanContainer().getAll();

        for (Pacman p : pacmans) {
            this.handlePacman(p);
        }
    }

    public void handleCoins() {
        if (this.activePointSeconds > 0) {
            this.activePointSeconds -= Game.getInstance().getRefreshRate();
        }

        if (this.activePointSeconds <= 0) {
            for (Ghost g : Game.getInstance().getGhostContainer()) {
                g.changeState(DynamicTargetState.HUNTER);
            }

            for (Pacman p : Game.getInstance().getPacmanContainer()) {
                p.changeState(DynamicTargetState.HUNTED);
            }
        }

        for (Coin c : this.coins) {
            this.handleCoin(c);
        }
    }

    private void handleGhosts() {
        for (Ghost g : this.ghosts) {
            this.handleGhost(g);
        }
    }

    private void handleCoin(Coin c) {
        // TODO Implement
    }

    private void handlePacman(Pacman pac) {
        MapObjectContainer mapObjectsOnPos = pac.getPosition().getOnPosition();

        for (MapObject mO : mapObjectsOnPos.getAll()) {
            if (mO instanceof Coin || mO instanceof Point) {
                pac.eat((Target) mO);
            } else if (mO instanceof Ghost) {
                Ghost g = (Ghost) mO;
                if (g.getState() == DynamicTargetState.HUNTED) {
                    pac.eat(g);
                } else {
                    g.eat(pac);
                }
            }
        }
    }

    private void handleGhost(Ghost g) {
        Position newPosition = Map.getPositionByDirectionIfMoveableTo(g.getPosition(), g.getHeadingTo());

        if (newPosition == null) {
            Direction wantedDirection = g.getHeadingTo();
            Direction realizedDirection = null;

            for (Direction d : Direction.values()) {
                if (d == g.getHeadingTo()) {
                    continue;
                }
                Position temp = Map.getPositionByDirectionIfMoveableTo(g.getPosition(), g.getHeadingTo());
                if (temp != null) {
                    newPosition = temp;
                    realizedDirection = d;
                }
            }

            if (wantedDirection.equals(realizedDirection)) {
                throw new RuntimeException("Cannot move to any point, something went wrong.");
            }
        }

        if (g.getState() == DynamicTargetState.HUNTER) {
            g.move(newPosition);
        } else if (g.getState() == DynamicTargetState.MUNCHED) {
            // Move to base
        } else if (g.getState() == DynamicTargetState.WAITING) {
            if (g.getWaitingSeconds() > 0) {
                g.reduceWaitingSeconds(1000 / Game.getInstance().getRefreshRate());
            } else if (g.getWaitingSeconds() == 0) {
                // If time is up, releash the kraken
                g.changeState(DynamicTargetState.HUNTER);
            }
        } else if (g.getState() == DynamicTargetState.HUNTED) {
            if (g.getMovedInLastTurn()) {
                g.setMovedInLastTurn(false);
            } else {
                g.move(newPosition);
                g.setMovedInLastTurn(true);
            }
        }
    }

}
