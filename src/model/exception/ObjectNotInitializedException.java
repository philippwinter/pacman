/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.exception;

public class ObjectNotInitializedException extends RuntimeException {

    private static final long serialVersionUID = 6953213349896267794L;

    public ObjectNotInitializedException(String className) {
        super("The object of class " + className + " is not initialized yet and therefore access to it is permitted.");
    }

}
