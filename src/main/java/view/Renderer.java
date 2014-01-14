/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package view;

import controller.MainController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Renderer
 *
 * @author Philipp Winter
 */
public class Renderer {

    private boolean readyForReRendering = false;

    private final MainController controller;
    private final MainGui gui;

    public final int mapHeight;
    public final int mapWidth;

    public final int topSpace;
    public final int leftSpace;

    public final int multiplier = 20;


    private final ImageOrganizer imgOrganizer;

    public Renderer(MainGui gui) {
        this.controller = MainController.getInstance();
        this.imgOrganizer = ImageOrganizer.getInstance();
        this.gui = gui;

        this.mapHeight = (Map.getInstance().height * multiplier);
        this.mapWidth = (Map.getInstance().width * multiplier);

        this.topSpace = (mapHeight / 2);
        this.leftSpace = (mapWidth / 2);
    }

    public void render() {
        if (!controller.isGameActive() || !readyForReRendering) {
            System.out.println("Returned without rendering: " + System.currentTimeMillis());
            return;
        }

        try {
            gui.requestFocusInWindow();

            JPanel pnl = gui.getPnlGame();
            Graphics2D g = (Graphics2D) pnl.getGraphics();

            for (Position pos : Map.positionsToRender) {
                final int paintX = pos.getX() * multiplier + leftSpace;
                final int paintY = pos.getY() * multiplier + topSpace;

                g.clearRect(
                        paintX,
                        paintY,
                        multiplier,
                        multiplier
                );

                Vector<MapObject> mapObjects = pos.getOnPosition().getAll();
                for (MapObject mO : mapObjects) {
                    if (mO.isVisible()) {
                        BufferedImage img = imgOrganizer.get(mO);
                        g.drawImage(
                                img,
                                null,
                                paintX,
                                paintY
                        );
                    }
                }
            }

            // Clear the space below the game, in order to paint new statistics there
            g.clearRect(
                    leftSpace,
                    (Map.getInstance().height * multiplier) + topSpace,
                    pnl.getWidth(),
                    pnl.getHeight());

            int i = 0;

            for (Pacman p : Game.getInstance().getPacmanContainer()) {
                drawString(g, "Highscore of " + p.getName() + ":\t" + p.getHighscore().getScore(), ++i);
            }

            drawString(g, "Player Lifes: " + Game.getInstance().getPlayerLifes(), ++i);
            drawString(g, "Level: " + Game.getInstance().getLevel().getLevel(), ++i);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            this.readyForReRendering = false;
            Map.positionsToRender.removeAll();
        }

    }

    private void drawString(Graphics2D target, String s, int offset) {
        target.drawString(
                s,
                15 + leftSpace,
                (Map.getInstance().height * multiplier) + (offset * multiplier) + topSpace
        );
    }

    public void markReady() {
        if (readyForReRendering) {
            System.out.println("Rendering must be completed before rendering again");
        }
        this.readyForReRendering = true;
        render();
    }
}
