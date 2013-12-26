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
import java.util.Random;

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
        try{
            this.handlePacmans();
            this.handleGhosts();
            this.handleCoins();
            MainController.getInstance().getGui().render();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                g.changeState(DynamicTarget.State.HUNTER);
            }

            for (Pacman p : Game.getInstance().getPacmanContainer()) {
                p.changeState(DynamicTarget.State.HUNTED);
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
        if(newPosition == null || Map.freeNeighbourFields(g.getPosition()) > 1){
            Direction[] directions = Direction.values();
            Position guessedPosition = null;
            Direction guessedDirection = null;

            shuffle(directions);

            for (Direction direction : directions) {
                guessedPosition = Map.getPositionByDirectionIfMovableTo(g.getPosition(), direction);
                if (guessedPosition != null) {
                    guessedDirection = direction;
                    break;
                }
            }
            if(guessedPosition == null){
                throw new RuntimeException("Couldn't find any free position :(");
            } else {
                newPosition = guessedPosition;
                g.setHeadingTo(guessedDirection);
            }
        }


        if (g.getState() == DynamicTarget.State.HUNTER) {
            g.move(newPosition);
        } else if (g.getState() == DynamicTarget.State.MUNCHED) {
            // Move to base
        } else if (g.getState() == DynamicTarget.State.WAITING) {
            if (g.getWaitingSeconds() > 0) {
                g.reduceWaitingSeconds(1000 / Game.getInstance().getRefreshRate());
            } else if (g.getWaitingSeconds() == 0) {
                // If time is up, releash the kraken
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

    private <E> void shuffle(E[] values) {
        int index;
        Random random = new Random();
        for(int i = values.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if(index != i){
                E temp = values[index];
                values[index] = values[i];
                values[i] = temp;
            }
        }
    }

}
