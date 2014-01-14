/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model;

import java.util.Random;

/**
 * Helper
 *
 * @author Philipp Winter
 */
public class Helper {

    public static <E> void shuffle(E[] values) {
        int index;
        Random random = new Random();
        for (int i = values.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                E temp = values[index];
                values[index] = values[i];
                values[i] = temp;
            }
        }
    }
}
