/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.exception.ListFullException;
import model.exception.ObjectAlreadyInListException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class GhostContainer implements Container<Ghost> {

    private ArrayList<Ghost> ghosts;

    public final int max = 4;

    public GhostContainer() {
        this.ghosts = new ArrayList<>(max);
    }

    public void add(Ghost ghost) throws ListFullException, ObjectAlreadyInListException {
        if (!this.ghosts.contains(ghost)) {
            if (this.ghosts.size() < this.max) {
                this.ghosts.add(ghost);
            } else {
                throw new ListFullException(ghost.getClass().getCanonicalName());
            }
        } else {
            throw new ObjectAlreadyInListException(ghost.getClass().getCanonicalName());
        }
    }

    public Ghost get(int i) {
        return this.ghosts.get(i);
    }

    public Ghost get(Position pos) {
        for (Ghost g : this.ghosts) {
            if (g.isOnPosition(pos)) {
                return g;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Ghost> getAll() {
        return (ArrayList<Ghost>) this.ghosts.clone();
    }

    public int getMax() {
        return max;
    }

    public Iterator<Ghost> iterator() {
        return ghosts.iterator();
    }

}
