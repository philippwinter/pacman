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
public class PointContainer implements Container<Point> {

    private ArrayList<Point> points;

    public final int max;

    public PointContainer(int max) {
        this.max = max;
        this.points = new ArrayList<>(max);
    }

    public void add(Point point) throws ObjectAlreadyInListException {
        if (!this.points.contains(point)) {
            this.points.add(point);
        } else {
            throw new ObjectAlreadyInListException(points.getClass().getCanonicalName());
        }
    }

    public Point get(int i) {
        return this.points.get(i);
    }

    public Point get(Position pos) {
        for (Point p : this.points) {
            if (p.getPosition().equals(pos)) {
                return p;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Point> getAll() {
        return (ArrayList<Point>) this.points.clone();
    }

    public Iterator<Point> iterator() {
        return points.iterator();
    }
}
