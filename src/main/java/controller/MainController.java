/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package controller;

import model.Game;
import view.MainGui;

import javax.swing.*;

/**
 * The main controller that controls the Game and the main view.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class MainController extends Thread {

    private static MainController instance;

    /**
     * The game instance.
     */
    private Game game;

    /**
     * The main window.
     */
    private MainGui gui;

    private boolean gameActive = false;

    /**
     * The main method, the entry point of our user to the game.
     *
     * @param args The command line arguments given to the program.
     */
    public static void main(String[] args) {
        MainController.reset();
    }

    public static void reset() {
        MainController.instance = new MainController();
        MainController.instance.prepare();

    }

    public MainGui getGui() {
        return gui;
    }

    public static MainController getInstance() {
        return instance;
    }

    private MainController() {

    }

    private void prepare() {
        Game.reset();
        game = Game.getInstance();
        gui = new MainGui();
        SwingUtilities.invokeLater(new Thread(gui));
    }

    /**
     * Start the game.
     */
    public void startGame() {
        this.gameActive = true;
        this.gui.startGame();
        this.game.start();
    }

    public void pauseGame() {
        this.gameActive = false;
        this.gui.pauseGame();
        this.game.pause();
    }

    public boolean isGameActive() {
        return gameActive;
    }
}
