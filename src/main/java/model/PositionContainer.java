/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class PositionContainer implements Container<Position> {

    private HashMap<String, Position> positions;

    private int width;

    private int height;

    public PositionContainer(int width, int height) {
        this.positions = new HashMap<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Retrieves a value per index.
     * <p/>
     * Imagine the following map:
     * <pre><blockquote>
     * y
     *   +-----+
     * 2 |klmno|
     * 1 |fghij|
     * 0 |abcde|
     *   +-----+
     *    01234  x
     * </blockquote></pre>
     * If you want to retrieve the "a", you would have to specify the coordinates to do so (here x=0, y=0)
     * but in some cases it might be useful to retrieve a value per index.
     * Our values can be imagined as such a map, with their respective x and y value.
     * <p/>
     * Ex.: 0 => "a", [...], 6 => "f", [...], 12 => "k"
     */
    public Position get(int index) {
        String key = "";

        // TODO Implement algorithm

        return this.positions.get(key);
    }

    /**
     * Retrieve a position by it's x and y coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return The position object.
     */
    public Position get(int x, int y) {
        Position val = positions.get(generateKey(x, y));
        if (val == null) {
            throw new IllegalArgumentException("The point " + x + "|" + y + " doesn't exist.");
        }
        return val;
    }

    /**
     * Adds a position to the container. DO NOT USE THIS OUTSIDE {@link Map}.
     *
     * @param el The element to add.
     */
    public void add(Position el) {
        this.positions.put(generateKey(el), el);
    }

    /**
     * Generate a key based on the supplied values.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return The position object.
     */
    private String generateKey(int x, int y) {
        return x + "#" + y;
    }

    public String generateKey(Position pos) {
        return generateKey(pos.getX(), pos.getY());
    }

    public ArrayList<Position> getAll() {
        return new ArrayList<>(this.positions.values());
    }

    /**
     * Removes an element from the container.
     *
     * @param el The element to remove.
     */
    @Override
    public void remove(Position el) {
        this.positions.remove(generateKey(el.getX(), el.getY()));
    }

    public Iterator<Position> iterator() {
        return positions.values().iterator();
    }

    public boolean contains(Position p){
        return this.positions.containsKey(this.generateKey(p));
    }

}
