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

    public final int multiplier = 10;


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

            for (Position pos : Map.getInstance().getPositionContainer()) {
                int i = 0;
                final int paintX = pos.getX() * multiplier + leftSpace;
                final int paintY = pos.getY() * multiplier + topSpace ;
                for (MapObject mO : pos.getOnPosition()) {
                    if(mO.isVisible()) {
                        i++;
                        BufferedImage img = imgOrganizer.get(mO);
                        g.drawImage(
                                img,
                                null,
                                paintX,
                                paintY
                        );
                    }
                }

                if (i == 0) {
                    BufferedImage img = imgOrganizer.get(null);
                    g.drawImage(
                            img,
                            null,
                            paintX,
                            paintY
                    );
                }
            }

            int i = 0;

            // Clear the space below the game, in order to paint new statistics there
            g.clearRect(
                    leftSpace,
                    (Map.getInstance().height * multiplier) + topSpace,
                    pnl.getWidth(),
                    pnl.getHeight());

            for(Pacman p : Game.getInstance().getPacmanContainer()) {
                i++;
                g.drawString(
                        "Highscore of " + p.getName() + ":\t" + p.getHighscore().getScore(),
                        10 + (i * 10) + leftSpace,
                        (Map.getInstance().height * multiplier) + (i*10) + topSpace
                );
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            this.readyForReRendering = false;
        }

    }

    public void markReady() {
        if(readyForReRendering) {
            System.out.println("Rendering must be completed before rendering again");
        }
        this.readyForReRendering = true;
        render();
    }
}
