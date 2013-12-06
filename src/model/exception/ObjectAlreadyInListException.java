/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.exception;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class ObjectAlreadyInListException extends ListException {

    private static final long serialVersionUID = -2037496278253932058L;

    public ObjectAlreadyInListException(String objectClass) {
        super("The Object of class " + objectClass + " is already in the list");
    }

}
