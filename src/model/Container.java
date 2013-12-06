/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import model.exception.ListException;

import java.util.ArrayList;

/**
 * The basic Container interface.
 *
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public interface Container<E> extends Iterable<E> {

    /**
     * The per index getter.
     *
     * @param i The index of the element.
     * @return Element on index i.
     */
    public E get(int i);

    /**
     * Adds an element to the container.
     *
     * @param el The element to add.
     * @throws ListException                                Parent class of all following exceptions.
     * @throws model.exception.ObjectAlreadyInListException When the object <i>el</i> is already in the list.
     * @throws model.exception.ListFullException            When the list is already filled with the maximum amount of elements.
     */
    public void add(E el) throws ListException;

    /**
     * Returns an ArrayList instance with all coins.
     *
     * @return A clone of the internal used ArrayList, so it can be mutated securely.
     * @see java.util.ArrayList#clone()
     */
    public ArrayList<E> getAll();

}
