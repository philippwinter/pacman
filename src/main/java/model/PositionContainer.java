/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.exception.ObjectAlreadyInListException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class PositionContainer implements Container<Position> {

    private ArrayList<Position> positions;

    public PositionContainer() {
        this.positions = new ArrayList<>();
    }

    public Position get(int i) {
        return this.positions.get(i);
    }

    public void add(Position el) {
        if (!this.positions.contains(el)) {
            this.positions.add(el);
        } else {
            throw new ObjectAlreadyInListException(el.getClass().getCanonicalName());
        }

    }

    @SuppressWarnings("unchecked")
    public ArrayList<Position> getAll() {
        return (ArrayList<Position>) this.positions.clone();
    }

    public Iterator<Position> iterator() {
        return positions.iterator();
    }

}
