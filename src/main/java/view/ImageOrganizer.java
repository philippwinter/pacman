/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package view;

import model.*;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ImageOrganizer
 *
 * @author Philipp Winter
 */
public class ImageOrganizer {

    private static ImageOrganizer instance;

    private final HashMap<String, BufferedImage> images;

    public static ImageOrganizer getInstance() {
        if (instance == null) {
            instance = new ImageOrganizer();
        }
        return instance;
    }

    public BufferedImage get(MapObject mO) {
        String key = null;
        if(mO != null){
            key = mO.getClass().getCanonicalName();
        } else {
            key = null;
        }

        if (mO instanceof Pacman) {
            key += ">" + ((Pacman) mO).getSex();
        } else if (mO instanceof Ghost) {
            key += ">" + ((Ghost) mO).getColour();
        }

        if (images.containsKey(key)) {
            return images.get(key);
        } else {
            System.out.println(images.keySet());
            throw new IllegalArgumentException("There is no image for the key \"" + key + "\"!");
        }
    }

    private ImageOrganizer() {
        images = new HashMap<>(30);

        Class<?> c = this.getClass();

        ArrayList<String[]> data = new ArrayList<>();
        data.add(
                new String[]{"/graphics/primitive/black.png", Wall.class.getCanonicalName()}
        );
        data.add(
                new String[]{"/graphics/primitive/yellow.png", Pacman.class.getCanonicalName() + ">MALE"}
        );
        data.add(
                new String[]{"/graphics/primitive/white.png", Pacman.class.getCanonicalName() + ">FEMALE"}
        );
        data.add(
                new String[]{"/graphics/primitive/red.png", Ghost.class.getCanonicalName() + ">RED"}
        );
        data.add(
                new String[]{"/graphics/primitive/orange.png", Ghost.class.getCanonicalName() + ">ORANGE"}
        );
        data.add(
                new String[]{"/graphics/primitive/blue.png", Ghost.class.getCanonicalName() + ">BLUE"}
        );
        data.add(
                new String[]{"/graphics/primitive/pink.png", Ghost.class.getCanonicalName() + ">PINK"}
        );
        data.add(
                new String[]{"/graphics/primitive/grey.png", Coin.class.getCanonicalName()}
        );
        data.add(
                new String[]{"/graphics/primitive/green.png", Point.class.getCanonicalName()}
        );
        data.add(
                new String[]{"/graphics/primitive/white.png", null}
        );
        data.add(
                new String[]{"/graphics/primitive/white.png", Placeholder.class.getCanonicalName()}
        );

        try {
            for (String[] d : data) {
                //Load image
                BufferedImage before = ImageIO.read(c.getResource(d[0]));
                //Scale it
                int w = before.getWidth();
                int h = before.getHeight();
                BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                AffineTransform at = new AffineTransform();
                at.scale(1, 1);
                AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                after = scaleOp.filter(before, after);
                //Put it in the list
                images.put(d[1], after);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
